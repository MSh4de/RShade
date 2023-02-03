package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityAttach implements Packet {

    private int leash;
    private int entityId;
    private int vehicleEntityId;

    private PacketPlayEntityAttach() {
    }

    public PacketPlayEntityAttach(int leash, int entityId, int vehicleEntityId) {
        this.leash = leash;
        this.entityId = entityId;
        this.vehicleEntityId = vehicleEntityId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readInt();
        this.vehicleEntityId = msg.readInt();
        this.leash = msg.readByte();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeInt(this.entityId);
        msg.writeInt(this.vehicleEntityId);
        msg.writeByte(this.leash);
    }
}
