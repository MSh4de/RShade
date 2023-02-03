package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayConfirmTransaction implements Packet {

    private int windowId;
    private short uid;
    private boolean accepted;

    private PacketPlayConfirmTransaction() {
    }

    public PacketPlayConfirmTransaction(int windowId, short uid, boolean accepted) {
        this.windowId = windowId;
        this.uid = uid;
        this.accepted = accepted;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.windowId = msg.readByte();
        this.uid = msg.readShort();
        this.accepted = msg.readByte() != 0;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.windowId);
        msg.writeShort(this.uid);
        msg.writeByte(this.accepted ? 1 : 0);
    }
}
