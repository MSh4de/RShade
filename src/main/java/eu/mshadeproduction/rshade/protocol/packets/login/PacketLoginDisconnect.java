package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import net.md_5.bungee.api.chat.BaseComponent;

public class PacketLoginDisconnect implements Packet {

    private BaseComponent[] reason;

    private PacketLoginDisconnect() {
    }

    public PacketLoginDisconnect(BaseComponent[] reason) {
        this.reason = reason;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeChatComponent(this.reason);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.reason = msg.readChatComponent();
    }
}