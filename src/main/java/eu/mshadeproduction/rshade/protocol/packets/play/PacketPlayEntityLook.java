package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayEntityLook extends PacketPlayEntity {

    private PacketPlayEntityLook() {
        this.something = true;
    }

    public PacketPlayEntityLook(int entityId, byte yaw, byte pitch, boolean onGround) {
        super(entityId);
        this.yaw = yaw;
        this.pitch = pitch;
        this.something = true;
        this.onGround = onGround;
    }

    @Override
    public void encode(ByteMessage msg) {
        super.encode(msg);
        msg.writeByte(this.yaw);
        msg.writeByte(this.pitch);
        msg.writeBoolean(this.onGround);
    }

    @Override
    public void decode(ByteMessage msg) {
        super.decode(msg);
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.onGround = msg.readBoolean();
    }
}
