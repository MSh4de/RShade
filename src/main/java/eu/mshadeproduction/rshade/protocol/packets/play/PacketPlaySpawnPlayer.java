package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

import java.util.UUID;

public class PacketPlaySpawnPlayer implements Packet {

    private int entityId;
    private UUID uuid;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private int itemId;
    // private DataWatcher watcher;
    // private List<DataWatcher.WatchableObject> field_148958_j;

    private PacketPlaySpawnPlayer() {
    }

    public PacketPlaySpawnPlayer(int entityId, UUID uuid, int x, int y, int z, byte yaw, byte pitch, int itemId) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.x = (int) Math.floor(x * 32);
        this.y = (int) Math.floor(y * 32);
        this.z = (int) Math.floor(z * 32);
        this.yaw = (byte) ((int) (yaw * 256.0F / 360.0F));
        this.pitch = (byte) ((int) (pitch * 256.0F / 360.0F));
        this.itemId = itemId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarIntFromBuffer();
        this.uuid = msg.readUuid();
        this.x = msg.readInt();
        this.y = msg.readInt();
        this.z = msg.readInt();
        this.yaw = msg.readByte();
        this.pitch = msg.readByte();
        this.itemId = msg.readShort();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeUuid(this.uuid);
        msg.writeInt(this.x);
        msg.writeInt(this.y);
        msg.writeInt(this.z);
        msg.writeByte(this.yaw);
        msg.writeByte(this.pitch);
        msg.writeShort(this.itemId);
    }
}
