package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayInEntityLook extends PacketPlayInEntity {

    private PacketPlayInEntityLook() {
        this.something = true;
    }

    public PacketPlayInEntityLook(int entityId, byte yaw, byte pitch, boolean onGround) {
        super(entityId);
        this.yaw = yaw;
        this.pitch = pitch;
        this.something = true;
        this.onGround = onGround;
    }

    @Override
    public void decode(ByteMessage msg) {
        super.decode(msg);
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.onGround = msg.readBoolean();
    }
}
