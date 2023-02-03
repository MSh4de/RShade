package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayClientHeldItemChange implements Packet {

    private int slotId;

    private PacketPlayClientHeldItemChange() {
    }

    public PacketPlayClientHeldItemChange(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.slotId);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.slotId = msg.readByte();
    }

    public int getSlotId() {
        return slotId;
    }
}
