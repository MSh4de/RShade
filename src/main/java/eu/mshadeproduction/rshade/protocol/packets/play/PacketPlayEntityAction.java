package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.EnumEntityActionType;
import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayEntityAction implements Packet {

    private int entityId;
    private EnumEntityActionType actionType;
    private int auxData;

    private PacketPlayEntityAction() {
    }

    public PacketPlayEntityAction(int entityId, EnumEntityActionType actionType) {
        this(entityId, actionType, 0);
    }

    public PacketPlayEntityAction(int entityId, EnumEntityActionType actionType, int auxData) {
        this.entityId = entityId;
        this.actionType = actionType;
        this.auxData = auxData;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.actionType = msg.readEnumValue(EnumEntityActionType.class);
        this.auxData = msg.readVarInt();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeEnumValue(this.actionType);
        msg.writeVarInt(this.auxData);
    }
}
