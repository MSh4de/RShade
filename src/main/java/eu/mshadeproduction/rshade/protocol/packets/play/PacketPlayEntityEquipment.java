package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityEquipment implements Packet {

    private int entityId;
    private int equipmentSlot;
    private short item;

    private PacketPlayEntityEquipment() {
    }

    public PacketPlayEntityEquipment(int entityId, int equipmentSlot, short item) {
        this.entityId = entityId;
        this.equipmentSlot = equipmentSlot;
        this.item = item;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeVarInt(this.equipmentSlot);
        msg.writeShort(this.item);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.equipmentSlot = msg.readVarInt();
        this.item = msg.readShort();
    }
}
