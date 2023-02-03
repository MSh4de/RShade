package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.EnumDiggingActionType;
import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import eu.mshadeproduction.rshade.world.BlockLocation;

public class PacketPlayPlayerDigging implements Packet {

    private BlockLocation blockLocation;
    private int facing;
    private EnumDiggingActionType actionType;

    private PacketPlayPlayerDigging() {
    }

    public PacketPlayPlayerDigging(BlockLocation blockLocation, int facing, EnumDiggingActionType actionType) {
        this.blockLocation = blockLocation;
        this.facing = facing;
        this.actionType = actionType;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.actionType = msg.readEnumValue(EnumDiggingActionType.class);
        this.blockLocation = msg.readBlockLocation();
        this.facing = msg.readUnsignedByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeEnumValue(this.actionType);
        msg.writeBlockLocation(this.blockLocation);
        msg.writeByte(this.facing);
    }
}
