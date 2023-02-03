package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayClientKeepAlive implements Packet {

    private int id;

    private PacketPlayClientKeepAlive() {
    }

    public PacketPlayClientKeepAlive(int id) {
        this.id = id;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.id = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(id);
    }

}