package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayFlying implements Packet {

    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    protected boolean moving = false;
    protected boolean rotating = false;

    protected PacketPlayFlying() {
    }

    public PacketPlayFlying(boolean onGround) {
        this.onGround = onGround;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isRotating() {
        return rotating;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.onGround = msg.readUnsignedByte() != 0;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.onGround ? 1 : 0);
    }

    @Override
    public String toString() {
        return "PacketPlayFlying{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                ", onGround=" + onGround +
                ", moving=" + moving +
                ", rotating=" + rotating +
                '}';
    }
}
