package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;
import net.md_5.bungee.api.chat.BaseComponent;

public class PacketPlayChat implements Packet {

    private BaseComponent[] component;
    private byte type;

    private PacketPlayChat() {
    }

    public PacketPlayChat(BaseComponent[] component, byte type) {
        this.component = component;
        this.type = type;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.component = msg.readChatComponent();
        this.type = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeChatComponent(this.component);
        msg.writeByte(this.type);
    }
}
