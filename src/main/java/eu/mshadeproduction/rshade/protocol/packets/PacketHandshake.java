package eu.mshadeproduction.rshade.protocol.packets;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;
import eu.mshadeproduction.rshade.protocol.registry.Version;

public class PacketHandshake implements PacketIn {

    private Version version;
    private String host;
    private int port;
    private int nextProtocol;

    private PacketHandshake() {
    }

    public Version getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getNextProtocol() {
        return nextProtocol;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.version = Version.of(msg.readVarInt());
        this.host = msg.readString();
        this.port = msg.readUnsignedShort();
        this.nextProtocol = msg.readVarInt();
    }
}