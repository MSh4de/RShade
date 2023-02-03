package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import eu.mshadeproduction.rshade.world.BlockLocation;

public class PacketPlayUseBed implements Packet {

    private int entityId;
    private BlockLocation blockLocation;

    private PacketPlayUseBed() {
    }

    public PacketPlayUseBed(int entityId, BlockLocation blockLocation) {
        this.entityId = entityId;
        this.blockLocation = blockLocation;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeBlockLocation(this.blockLocation);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.blockLocation = msg.readBlockLocation();
    }
}
