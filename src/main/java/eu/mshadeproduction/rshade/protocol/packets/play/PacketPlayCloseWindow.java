package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayCloseWindow implements Packet {

    private int windowId;

    private PacketPlayCloseWindow() {
    }

    public PacketPlayCloseWindow(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.windowId = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.windowId);
    }
}
