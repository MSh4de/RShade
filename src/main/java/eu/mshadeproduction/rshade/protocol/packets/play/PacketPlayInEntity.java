package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;

public class PacketPlayInEntity implements PacketIn {

    protected int entityId;
    protected int posX;
    protected int posY;
    protected int posZ;
    protected int yaw;
    protected int pitch;
    protected boolean onGround;
    // Jsais pas frère
    protected boolean something;

    protected PacketPlayInEntity() {
    }

    public PacketPlayInEntity(int entityId) {
        this.entityId = entityId;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public int getYaw() {
        return yaw;
    }

    public int getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isSomething() {
        return something;
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
    }
}
