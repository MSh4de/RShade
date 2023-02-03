package test.packets.out;


import test.packets.PacketSerializer;
import test.world.Chunk;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketPlayOutChunkData107 implements PacketOut {
    private final Chunk chunk;

    public PacketPlayOutChunkData107(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writeInt(this.chunk.getX());
        packetSerializer.writeInt(this.chunk.getZ());
        packetSerializer.writeBoolean(true);
        packetSerializer.writeVarInt(25565);
        packetSerializer.writeByteArray(this.chunk.getMapWithPalette());
    }
}
