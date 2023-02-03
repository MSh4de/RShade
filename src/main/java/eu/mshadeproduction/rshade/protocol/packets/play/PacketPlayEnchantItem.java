package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEnchantItem implements Packet {

    private int windowId;
    private int button;

    private PacketPlayEnchantItem() {
    }

    public PacketPlayEnchantItem(int windowId, int button) {
        this.windowId = windowId;
        this.button = button;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.windowId = msg.readByte();
        this.button = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.windowId);
        msg.writeByte(this.button);
    }
}
