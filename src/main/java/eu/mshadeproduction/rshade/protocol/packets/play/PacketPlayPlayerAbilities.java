package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayPlayerAbilities implements Packet {

    private boolean invulnerable;
    private boolean flying;
    private boolean canFly;
    private boolean canInstantlyBuild;
    private float flySpeed;
    private float walkSpeed;

    private PacketPlayPlayerAbilities() {
    }

    public PacketPlayPlayerAbilities(boolean invulnerable, boolean flying, boolean canFly, boolean canInstantlyBuild, float flySpeed, float walkSpeed) {
        this.invulnerable = invulnerable;
        this.flying = flying;
        this.canFly = canFly;
        this.canInstantlyBuild = canInstantlyBuild;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
    }

    @Override
    public void encode(ByteMessage msg) {

        byte b0 = 0;

        if (this.isInvulnerable()) {
            b0 = (byte) (b0 | 1);
        }

        if (this.isFlying()) {
            b0 = (byte) (b0 | 2);
        }

        if (this.isCanFly()) {
            b0 = (byte) (b0 | 4);
        }

        if (this.isCanInstantlyBuild()) {
            b0 = (byte) (b0 | 8);
        }

        msg.writeByte(b0);
        msg.writeFloat(this.flySpeed);
        msg.writeFloat(this.walkSpeed);
    }

    @Override
    public void decode(ByteMessage msg) {
        byte b0 = msg.readByte();

        this.invulnerable = (b0 & 1) > 0;
        this.flying = (b0 & 2) > 0;
        this.canFly = (b0 & 4) > 0;
        this.canInstantlyBuild = (b0 & 8) > 0;
        this.flySpeed = msg.readFloat();
        this.walkSpeed = msg.readFloat();
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public boolean isCanInstantlyBuild() {
        return canInstantlyBuild;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }
}