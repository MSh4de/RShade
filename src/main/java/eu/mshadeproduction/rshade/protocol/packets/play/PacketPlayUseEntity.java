package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.EntityUseActionType;
import eu.mshadeproduction.rshade.Vec3D;
import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayUseEntity implements Packet {

    private int entityId;
    private EntityUseActionType action;
    private Vec3D vec3D;

    private PacketPlayUseEntity() {
    }

    public PacketPlayUseEntity(int entityId, EntityUseActionType action) {
        this.entityId = entityId;
        this.action = action;
    }

    public PacketPlayUseEntity(int entityId, Vec3D vec3D) {
        this.entityId = entityId;
        this.vec3D = vec3D;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityId = msg.readVarInt();
        this.action = msg.readEnumValue(EntityUseActionType.class);
        if (this.action == EntityUseActionType.INTERACT_AT) {
            this.vec3D = new Vec3D(msg.readFloat(), msg.readFloat(), msg.readFloat());
        }
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityId);
        msg.writeEnumValue(action);
        if (this.action == EntityUseActionType.INTERACT_AT) {
            msg.writeFloat((float) this.vec3D.x);
            msg.writeFloat((float) this.vec3D.y);
            msg.writeFloat((float) this.vec3D.z);
        }
    }
}
