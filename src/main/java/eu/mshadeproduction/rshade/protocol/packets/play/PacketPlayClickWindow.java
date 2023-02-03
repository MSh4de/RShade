package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayClickWindow implements Packet {

    private int windowId;
    private int slotId;
    private int usedButton;
    private short actionNumber;
    private int clickedItem;
    private int mode;

    private PacketPlayClickWindow() {
    }

    public PacketPlayClickWindow(int windowId, int slotId, int usedButton, short actionNumber, int clickedItem, int mode) {
        this.windowId = windowId;
        this.slotId = slotId;
        this.usedButton = usedButton;
        this.actionNumber = actionNumber;
        this.clickedItem = clickedItem;
        this.mode = mode;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.windowId = msg.readByte();
        this.slotId = msg.readShort();
        this.usedButton = msg.readByte();
        this.actionNumber = msg.readShort();
        this.mode = msg.readByte();
        this.clickedItem = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeByte(this.windowId);
        msg.writeShort(this.slotId);
        msg.writeByte(this.usedButton);
        msg.writeShort(this.actionNumber);
        msg.writeByte(this.mode);
        msg.writeVarInt(this.clickedItem);
    }
}
