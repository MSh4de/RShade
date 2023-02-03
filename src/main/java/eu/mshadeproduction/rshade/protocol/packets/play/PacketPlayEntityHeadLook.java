package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityHeadLook implements Packet {

    private int entityId;
    private byte yaw;

    private PacketPlayEntityHeadLook() {
    }

    public PacketPlayEntityHeadLook(int entityId, byte yaw) {
        this.entityId = entityId;
        this.yaw = yaw;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.yaw = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeByte(this.yaw);
    }
}
