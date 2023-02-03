package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayDestroyEntity implements Packet {

    private int[] entitiesId;

    private PacketPlayDestroyEntity() {
    }

    public PacketPlayDestroyEntity(int[] entitiesId) {
        this.entitiesId = entitiesId;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entitiesId.length);

        for (int i = 0; i < this.entitiesId.length; i++) {
            msg.writeVarInt(this.entitiesId[i]);
        }
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entitiesId = new int[msg.readVarInt()];
        for (int i = 0; i < this.entitiesId.length; i++) {
            this.entitiesId[i] = msg.readVarInt();
        }
    }
}
