package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;

public class PacketLoginInPluginResponse implements PacketIn {

    private int messageId;
    private boolean successful;
    private ByteMessage data;

    private PacketLoginInPluginResponse() {
    }

    public PacketLoginInPluginResponse(int messageId, boolean successful, ByteMessage data) {
        this.messageId = messageId;
        this.successful = successful;
        this.data = data;
    }

    public ByteMessage getData() {
        return data;
    }

    @Override
    public void decode(ByteMessage msg) {
        messageId = msg.readVarInt();
        successful = msg.readBoolean();

        if (msg.readableBytes() > 0) {
            int i = msg.readableBytes();
            data = new ByteMessage(msg.readBytes(i));
        }
    }

}