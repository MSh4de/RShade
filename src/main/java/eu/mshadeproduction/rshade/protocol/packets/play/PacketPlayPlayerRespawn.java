package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayPlayerRespawn implements Packet {

    private int dimensionId;
    private int difficulty;
    private int gameMode;
    private String worldType;

    private PacketPlayPlayerRespawn() {
    }

    public PacketPlayPlayerRespawn(int dimensionId, int difficulty, int gameMode, String worldType) {
        this.dimensionId = dimensionId;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.worldType = worldType;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.dimensionId = msg.readInt();
        this.difficulty = msg.readUnsignedByte();
        this.gameMode = msg.readUnsignedByte();
        this.worldType = msg.readString();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeInt(this.dimensionId);
        msg.writeByte(this.difficulty);
        msg.writeByte(this.gameMode);
        msg.writeString(this.worldType);
    }
}
