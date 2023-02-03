package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlaySpawnObject implements Packet {

    private int entityId;
    private int x;
    private int y;
    private int z;
    private int speedX;
    private int speedY;
    private int speedZ;
    private int pitch;
    private int yaw;
    private int type;
    private int field_149020_k;

    private PacketPlaySpawnObject() {
    }

    public PacketPlaySpawnObject(int entityId, int x, int y, int z, int speedX, int speedY, int speedZ, int pitch, int yaw, int type) {
        this(entityId, x, y, z, speedX, speedY, speedZ, pitch, yaw, type, 0);
    }

    public PacketPlaySpawnObject(int entityId, int x, int y, int z, int speedX, int speedY, int speedZ, int pitch, int yaw, int type, int field_149020_k) {
        this.entityId = entityId;
        this.x = (int) Math.floor(x * 32);
        this.y = (int) Math.floor(y * 32);
        this.z = (int) Math.floor(z * 32);
        this.pitch = (int) Math.floor(pitch * 256.0F / 360.0F);
        this.yaw = (int) Math.floor(yaw * 256.0F / 360.0F);
        this.type = type;
        this.field_149020_k = field_149020_k;

        if (field_149020_k > 0) {
            double d0 = 0.0D;
            double d1 = 0.0D;
            double d2 = 0.0D;
            double d3 = 3.9D;

            if (d0 < -d3) {
                d0 = -d3;
            }

            if (d1 < -d3) {
                d1 = -d3;
            }

            if (d2 < -d3) {
                d2 = -d3;
            }

            if (d0 > d3) {
                d0 = d3;
            }

            if (d1 > d3) {
                d1 = d3;
            }

            if (d2 > d3) {
                d2 = d3;
            }

            this.speedX = (int) (d0 * 8000.0D);
            this.speedY = (int) (d1 * 8000.0D);
            this.speedZ = (int) (d2 * 8000.0D);
        }

    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarIntFromBuffer();
        this.type = msg.readByte();
        this.x = msg.readInt();
        this.y = msg.readInt();
        this.z = msg.readInt();
        this.pitch = msg.readByte();
        this.yaw = msg.readByte();
        this.field_149020_k = msg.readInt();

        if (this.field_149020_k > 0) {
            this.speedX = msg.readShort();
            this.speedY = msg.readShort();
            this.speedZ = msg.readShort();
        }
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeByte(this.type);
        msg.writeInt(this.x);
        msg.writeInt(this.y);
        msg.writeInt(this.z);
        msg.writeByte(this.pitch);
        msg.writeByte(this.yaw);
        msg.writeInt(this.field_149020_k);

        if (this.field_149020_k > 0) {
            msg.writeShort(this.speedX);
            msg.writeShort(this.speedY);
            msg.writeShort(this.speedZ);
        }
    }
}
