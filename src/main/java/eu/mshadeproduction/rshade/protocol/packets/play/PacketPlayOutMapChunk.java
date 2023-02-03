package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;
import eu.mshadeproduction.rshade.world.Chunk;

public class PacketPlayOutMapChunk implements PacketOut {

    private Chunk chunk;
    private boolean empty = false;


    private PacketPlayOutMapChunk() {
    }

    public PacketPlayOutMapChunk(Chunk chunk, boolean empty) {
        this.chunk = chunk;
        this.empty = empty;
    }

    public PacketPlayOutMapChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeInt(this.chunk.getX());
        msg.writeInt(this.chunk.getZ());
        msg.writeBoolean(true);
        msg.writeShort((short) 65535);
        if (!empty) msg.writeBytesArray(this.chunk.getMap());
        else msg.writeBytesArray(new byte[]{});
    }
}
