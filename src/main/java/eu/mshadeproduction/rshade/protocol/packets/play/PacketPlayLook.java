package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayLook extends PacketPlayFlying {

    private PacketPlayLook() {
        this.rotating = true;
    }

    public PacketPlayLook(float yaw, float pitch, boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.rotating = true;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.yaw = msg.readFloat();
        this.pitch = msg.readFloat();
        super.decode(msg);
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeFloat(this.yaw);
        msg.writeFloat(this.pitch);
        super.encode(msg);
    }
}
