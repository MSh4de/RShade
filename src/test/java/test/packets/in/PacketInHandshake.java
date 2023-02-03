package test.packets.in;


import test.Limbo;
import test.NetworkManager;
import test.PlayerConnection;
import test.packets.AbstractProtocol;
import test.packets.PacketSerializer;
import test.packets.Status;
import test.util.UUIDConverter;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Rigner on 29/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketInHandshake implements PacketIn {
    private int version;
    private String serverAddress;
    private short serverPort;
    private int nextState;

    @Override
    public void readPacket(PacketSerializer packetSerializer) throws IOException {
        this.version = packetSerializer.readVarInt();
        this.serverAddress = packetSerializer.readString();
        this.serverPort = packetSerializer.readShort();
        this.nextState = packetSerializer.readVarInt();
    }

    @Override
    public void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection) {
        AbstractProtocol abstractProtocol = networkManager.getProtocolByVersionId(this.version);
        if (abstractProtocol == null) {
            Limbo.LOGGER.warning("Version " + this.version + " not supported");
            networkManager.disconnect(playerConnection, "Version " + this.version + " not supported");
            return;
        }
        Status status = this.nextState == 1 ? Status.STATUS : this.nextState == 2 ? Status.LOGIN : null;
        if (status == null) {
            Limbo.LOGGER.warning("Received invalid status " + this.nextState);
            networkManager.disconnect(playerConnection, "State " + this.nextState + " incorrect");
            return;
        }
        if (status == Status.LOGIN && networkManager.getLimboConfiguration().getMaxSlots() >= 0 && networkManager.getConnectedPlayers() >= networkManager.getLimboConfiguration().getMaxSlots()) {
            networkManager.disconnect(playerConnection, "Server is full");
            return;
        }
        playerConnection.setProtocol(abstractProtocol);
        playerConnection.setProtocolId(this.version);
        playerConnection.setStatus(status);

        String[] data = this.serverAddress.split("\00");
        InetSocketAddress bungeeAddress = null;
        if (data.length == 3 || data.length == 4) {
            this.serverAddress = data[0];
            bungeeAddress = playerConnection.getInetAddress();
            playerConnection.setInetAddress(new InetSocketAddress(data[1], playerConnection.getInetAddress().getPort()));
            playerConnection.setUuid(UUIDConverter.fromString(data[2]));
        }
        if (status == Status.LOGIN)
            Limbo.LOGGER.info("New connection from " + playerConnection.getInetAddress().getHostString() + (bungeeAddress == null ? "" : " (via BungeeCord " + bungeeAddress.getHostString() + ")"));
    }
}
