package eu.mshadeproduction.rshade.protocol.packets.status;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketStatusPing implements Packet {

    private long randomId;

    private PacketStatusPing() {
    }

    public PacketStatusPing(long randomId) {
        this.randomId = randomId;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeLong(randomId);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.randomId = msg.readLong();
    }

}