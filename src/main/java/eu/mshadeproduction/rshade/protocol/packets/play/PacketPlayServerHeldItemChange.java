package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayServerHeldItemChange implements Packet {

    private int slotId;

    private PacketPlayServerHeldItemChange() {
    }

    public PacketPlayServerHeldItemChange(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.slotId = msg.readShort();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeShort(this.slotId);
    }

    public int getSlotId() {
        return slotId;
    }
}
