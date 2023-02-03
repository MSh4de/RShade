package test.packets.in;


import test.NetworkManager;
import test.PlayerConnection;
import test.packets.PacketSerializer;
import test.packets.out.PacketStatusOutPong;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketStatusInPing implements PacketIn {
    private long id;

    @Override
    public void readPacket(PacketSerializer packetSerializer) throws IOException {
        this.id = packetSerializer.readLong();
    }

    @Override
    public void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection) {
        networkManager.sendPacket(playerConnection, new PacketStatusOutPong(this.id));
    }
}
