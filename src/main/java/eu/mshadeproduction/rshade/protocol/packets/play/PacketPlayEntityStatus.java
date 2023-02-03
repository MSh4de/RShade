package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityStatus implements Packet {

    private int entityId;
    private byte logicOpcode;

    private PacketPlayEntityStatus() {
    }

    public PacketPlayEntityStatus(int entityId, byte logicOpcode) {
        this.entityId = entityId;
        this.logicOpcode = logicOpcode;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readInt();
        this.logicOpcode = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeInt(this.entityId);
        msg.writeByte(this.logicOpcode);
    }
}
