package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.GameProfile;
import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

import java.util.UUID;

public class PacketLoginSuccess implements Packet {

    private GameProfile gameProfile;

    private PacketLoginSuccess() {
    }

    public PacketLoginSuccess(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarString(this.gameProfile.getUuid().toString());
        msg.writeVarString(this.gameProfile.getUsername());
    }

    @Override
    public void decode(ByteMessage msg) {
        String id = msg.readString();
        String username = msg.readString();
        this.gameProfile = new GameProfile(UUID.fromString(id), username);
    }
}