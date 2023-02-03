package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlaySpawnMob implements Packet {

    private int entityId;
    private int type;
    private int x;
    private int y;
    private int z;
    private int velocityX;
    private int velocityY;
    private int velocityZ;
    private byte yaw;
    private byte pitch;
    private byte headPitch;

    private PacketPlaySpawnMob() {
    }

    public PacketPlaySpawnMob(int entityId, int type, int x, int y, int z, int velocityX, int velocityY, int velocityZ, byte yaw, byte pitch, byte headPitch) {
        this.entityId = entityId;
        this.type = type;
        this.x = (int) Math.floor(x * 32);
        this.y = (int) Math.floor(y * 32);
        this.z = (int) Math.floor(z * 32);
        this.yaw = (byte) ((int) Math.floor(yaw * 256.0F / 360.0F));
        this.pitch = (byte) ((int) Math.floor(pitch * 256.0F / 360.0F));
        this.headPitch = (byte) ((int) Math.floor(headPitch * 256.0F / 360.0F));
        double d0 = 3.9D;
        double d1, d2, d3 = 0.0D;
        d1 = d2 = d3;

        if (d1 < -d0) {
            d1 = -d0;
        }

        if (d2 < -d0) {
            d2 = -d0;
        }

        if (d3 < -d0) {
            d3 = -d0;
        }

        if (d1 > d0) {
            d1 = d0;
        }

        if (d2 > d0) {
            d2 = d0;
        }

        if (d3 > d0) {
            d3 = d0;
        }

        this.velocityX = (int) (d1 * 8000.0D);
        this.velocityY = (int) (d2 * 8000.0D);
        this.velocityZ = (int) (d3 * 8000.0D);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.type = msg.readByte() & 255;
        this.x = msg.readInt();
        this.y = msg.readInt();
        this.z = msg.readInt();
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.headPitch = msg.readByte();
        this.velocityX = msg.readShort();
        this.velocityY = msg.readShort();
        this.velocityZ = msg.readShort();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeByte(this.type & 255);
        msg.writeInt(this.x);
        msg.writeInt(this.y);
        msg.writeInt(this.z);
        msg.writeByte(this.yaw);
        msg.writeByte(this.pitch);
        msg.writeByte(this.headPitch);
        msg.writeShort(this.velocityX);
        msg.writeShort(this.velocityY);
        msg.writeShort(this.velocityZ);
    }
}
