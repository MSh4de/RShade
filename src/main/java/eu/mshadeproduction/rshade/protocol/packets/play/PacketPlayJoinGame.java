package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayJoinGame implements Packet {

    private int entityId;
    private int gameMode;
    private int dimension;
    private int difficulty;
    private int maxPlayers;
    private String levelType;
    private boolean debugInfo;

    private PacketPlayJoinGame() {
    }

    public PacketPlayJoinGame(int entityId, int gameMode, int dimension, int difficulty, int maxPlayers, String levelType, boolean debugInfo) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.debugInfo = debugInfo;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.gameMode = msg.readByte();
        this.dimension = msg.readByte();
        this.difficulty = msg.readByte();
        this.maxPlayers = msg.readByte();
        this.levelType = msg.readString();
        this.debugInfo = msg.readBoolean();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeInt(this.entityId);
        msg.writeByte(this.gameMode);
        msg.writeByte(this.dimension);
        msg.writeByte(this.difficulty);
        msg.writeByte(this.maxPlayers);
        msg.writeString(this.levelType);
        msg.writeBoolean(this.debugInfo);
    }

}