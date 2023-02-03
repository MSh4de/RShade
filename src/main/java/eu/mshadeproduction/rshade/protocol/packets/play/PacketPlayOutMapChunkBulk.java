package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;
import eu.mshadeproduction.rshade.world.Chunk;

import java.util.Collection;

public class PacketPlayOutMapChunkBulk implements PacketOut {

    private boolean skyLightSent;
    private Collection<Chunk> chunks;

    private PacketPlayOutMapChunkBulk() {
    }

    public PacketPlayOutMapChunkBulk(boolean skyLightSent, Collection<Chunk> chunks) {
        this.skyLightSent = skyLightSent;
        this.chunks = chunks;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeBoolean(skyLightSent);
        msg.writeVarInt(chunks.size());
        chunks.forEach(chunk -> {
            msg.writeInt(chunk.getX());
            msg.writeInt(chunk.getZ());
            msg.writeShort((short) 65535 & '\uffff');
        });
        chunks.forEach(chunk -> msg.writeBytesArray(chunk.getMap()));

    }
}
