package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketLoginStart implements Packet {

    private String username;

    private PacketLoginStart() {
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.username = msg.readString();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeString(this.username);
    }
}