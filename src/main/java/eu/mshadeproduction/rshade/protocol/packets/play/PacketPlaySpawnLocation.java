package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import eu.mshadeproduction.rshade.world.BlockLocation;

public class PacketPlaySpawnLocation implements Packet {

    private BlockLocation blockLocation;

    private PacketPlaySpawnLocation() {
    }

    public PacketPlaySpawnLocation(BlockLocation blockLocation) {
        this.blockLocation = blockLocation;
    }

    public BlockLocation getBlockLocation() {
        return blockLocation;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeBlockLocation(this.blockLocation);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.blockLocation = msg.readBlockLocation();
    }
}
