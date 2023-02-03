package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import eu.mshadeproduction.rshade.world.BlockLocation;

public class PacketPlaySpawnEntityPainting implements Packet {

    private int entityId;
    private BlockLocation blockLocation;
    private int direction;
    private String title;

    private PacketPlaySpawnEntityPainting() {
    }

    public PacketPlaySpawnEntityPainting(int entityId, BlockLocation blockLocation, int direction, String title) {
        this.entityId = entityId;
        this.blockLocation = blockLocation;
        this.direction = direction;
        this.title = title;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.title = msg.readString();
        this.blockLocation = msg.readBlockLocation();
        this.direction = msg.readUnsignedByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeString(this.title);
        msg.writeBlockLocation(this.blockLocation);
        msg.writeByte(this.direction);
    }
}
