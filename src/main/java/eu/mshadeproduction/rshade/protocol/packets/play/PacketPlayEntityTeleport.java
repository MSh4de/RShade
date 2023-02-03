package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityTeleport implements Packet {

    private int entityId;
    private int posX;
    private int posY;
    private int posZ;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    private PacketPlayEntityTeleport() {
    }

    public PacketPlayEntityTeleport(int entityId, int posX, int posY, int posZ, byte yaw, byte pitch, boolean onGround) {
        this.entityId = entityId;
        this.posX = (int) Math.floor(posX * 32);
        this.posY = (int) Math.floor(posY * 32);
        this.posZ = (int) Math.floor(posZ * 32);
        this.yaw = (byte) ((int) Math.floor(yaw * 256.0F / 360.0F));
        this.pitch = (byte) ((int) Math.floor(pitch * 256.0F / 360.0F));
        this.onGround = onGround;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.posX = msg.readInt();
        this.posY = msg.readInt();
        this.posZ = msg.readInt();
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.onGround = msg.readBoolean();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeInt(this.posX);
        msg.writeInt(this.posY);
        msg.writeInt(this.posZ);
        msg.writeByte(this.yaw);
        msg.writeByte(this.pitch);
        msg.writeBoolean(this.onGround);
    }
}
