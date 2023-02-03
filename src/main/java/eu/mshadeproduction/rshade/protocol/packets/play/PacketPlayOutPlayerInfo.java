package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;

import java.util.UUID;

public class PacketPlayOutPlayerInfo implements PacketOut {

    private int gameMode = 3;
    private String username = "";
    private UUID uuid;

    private PacketPlayOutPlayerInfo() {
    }

    public PacketPlayOutPlayerInfo(int gameMode, String username, UUID uuid) {
        this.gameMode = gameMode;
        this.username = username;
        this.uuid = uuid;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(0);
        msg.writeVarInt(1);
        msg.writeUuid(uuid);
        msg.writeString(username);
        msg.writeVarInt(0);
        msg.writeVarInt(gameMode);
        msg.writeVarInt(60);
        msg.writeBoolean(false);
    }

}