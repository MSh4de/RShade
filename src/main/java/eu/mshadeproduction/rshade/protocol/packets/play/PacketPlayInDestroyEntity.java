package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;

public class PacketPlayInDestroyEntity implements PacketIn {

    private int[] entitiesId;

    private PacketPlayInDestroyEntity() {
    }

    public PacketPlayInDestroyEntity(int[] entitiesId) {
        this.entitiesId = entitiesId;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entitiesId = new int[msg.readVarInt()];

        for (int i = 0; i < this.entitiesId.length; i++) {
            this.entitiesId[i] = msg.readVarInt();
        }
    }
}
