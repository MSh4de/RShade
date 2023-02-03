package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayChatMessage implements Packet {

    private String message;

    private PacketPlayChatMessage() {
    }

    public PacketPlayChatMessage(String message) {

        if (message.length() > 100)
            message = message.substring(0, 100);

        this.message = message;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.message = msg.readString();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeString(this.message);
    }

}