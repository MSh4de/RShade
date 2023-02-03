package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayServerKeepAlive implements Packet {

    private int key;

    private PacketPlayServerKeepAlive() {
    }

    public PacketPlayServerKeepAlive(int key) {
        this.key = key;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.key = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(key);
    }

}
