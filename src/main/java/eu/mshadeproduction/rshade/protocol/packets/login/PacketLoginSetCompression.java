package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketLoginSetCompression implements Packet {

    private int compressionThreshold;

    private PacketLoginSetCompression() {
    }

    public PacketLoginSetCompression(int compressionThreshold) {
        this.compressionThreshold = compressionThreshold;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.compressionThreshold = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.compressionThreshold);
    }
}
