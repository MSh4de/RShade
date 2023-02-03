package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayCreativeInventoryAction implements Packet {

    private int slotId;
    private int itemId;

    private PacketPlayCreativeInventoryAction() {
    }

    public PacketPlayCreativeInventoryAction(int slotId, int itemId) {
        this.slotId = slotId;
        this.itemId = itemId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.slotId = msg.readShort();
        this.itemId = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeShort(this.slotId);
        msg.writeVarInt(this.itemId);
    }
}
