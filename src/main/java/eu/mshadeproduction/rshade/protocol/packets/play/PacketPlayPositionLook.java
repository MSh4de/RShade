package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayPositionLook extends PacketPlayFlying {

    private PacketPlayPositionLook() {
        this.moving = true;
        this.rotating = true;
    }

    public PacketPlayPositionLook(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.moving = true;
        this.rotating = true;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.x = msg.readDouble();
        this.y = msg.readDouble();
        this.z = msg.readDouble();
        this.yaw = msg.readFloat();
        this.pitch = msg.readFloat();
        super.decode(msg);
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeDouble(this.x);
        msg.writeDouble(this.y);
        msg.writeDouble(this.z);
        msg.writeFloat(this.yaw);
        msg.writeFloat(this.pitch);
        super.encode(msg);
    }
}
