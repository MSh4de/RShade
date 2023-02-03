package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayPosition extends PacketPlayFlying {

    private PacketPlayPosition() {
        this.moving = true;
    }

    public PacketPlayPosition(double x, double y, double z, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
        this.moving = true;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.x = msg.readDouble();
        this.y = msg.readDouble();
        this.z = msg.readDouble();
        super.decode(msg);
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeDouble(this.x);
        msg.writeDouble(this.y);
        msg.writeDouble(this.z);
        super.encode(msg);
    }
}
