package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayCollectItem implements Packet {

    private int collectedItemEntityId;
    private int entityId;

    private PacketPlayCollectItem() {
    }

    public PacketPlayCollectItem(int collectedItemEntityId, int entityId) {
        this.collectedItemEntityId = collectedItemEntityId;
        this.entityId = entityId;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.collectedItemEntityId);
        msg.writeVarInt(this.entityId);
    }

    @Override
    public void decode(ByteMessage msg) {
        this.collectedItemEntityId = msg.readVarInt();
        this.entityId = msg.readVarInt();
    }
}
