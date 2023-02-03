package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayPlayerInput implements Packet {

    private float strafeSpeed;
    private float forwardSpeed;
    private boolean jumping;
    private boolean sneaking;

    private PacketPlayPlayerInput() {
    }

    public PacketPlayPlayerInput(float strafeSpeed, float forwardSpeed, boolean jumping, boolean sneaking) {
        this.strafeSpeed = strafeSpeed;
        this.forwardSpeed = forwardSpeed;
        this.jumping = jumping;
        this.sneaking = sneaking;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.strafeSpeed = msg.readFloat();
        this.forwardSpeed = msg.readFloat();
        byte b0 = msg.readByte();
        this.jumping = (b0 & 1) > 0;
        this.sneaking = (b0 & 2) > 0;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeFloat(this.strafeSpeed);
        msg.writeFloat(this.forwardSpeed);
        byte b0 = 0;

        if (this.jumping)
            b0 = (byte) (b0 | 1);

        if (this.sneaking)
            b0 = (byte) (b0 | 2);

        msg.writeByte(b0);
    }
}
