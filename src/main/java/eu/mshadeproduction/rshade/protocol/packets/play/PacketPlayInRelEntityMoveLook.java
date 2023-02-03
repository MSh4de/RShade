package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayInRelEntityMoveLook extends PacketPlayInEntity {

    private PacketPlayInRelEntityMoveLook() {
        this.something = true;
    }

    public PacketPlayInRelEntityMoveLook(int entityId, byte x, byte y, byte z, byte yaw, byte pitch, boolean onGround) {
        super(entityId);
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.something = true;
    }

    @Override
    public void decode(ByteMessage msg) {
        super.decode(msg);
        this.posX = msg.readByte();
        this.posY = msg.readByte();
        this.posZ = msg.readByte();
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.onGround = msg.readBoolean();
    }
}
