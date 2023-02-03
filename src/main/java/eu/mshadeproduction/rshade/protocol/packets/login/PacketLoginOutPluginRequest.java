package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;
import io.netty.buffer.ByteBuf;

public class PacketLoginOutPluginRequest implements PacketOut {

    private int messageId;
    private String channel;
    private ByteBuf data;

    private PacketLoginOutPluginRequest() {
    }

    public PacketLoginOutPluginRequest(int messageId, String channel, ByteBuf data) {
        this.messageId = messageId;
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(messageId);
        msg.writeString(channel);
        msg.writeBytes(data);
    }

}