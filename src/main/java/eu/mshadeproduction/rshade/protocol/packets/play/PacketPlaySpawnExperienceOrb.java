package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlaySpawnExperienceOrb implements Packet {

    private int entityID;
    private int posX;
    private int posY;
    private int posZ;
    private int xpValue;

    public PacketPlaySpawnExperienceOrb() {
    }

    public PacketPlaySpawnExperienceOrb(int entityID, int posX, int posY, int posZ, int xpValue) {
        this.entityID = entityID;
        this.posX = (int) Math.floor(posX * 32);
        this.posY = (int) Math.floor(posY * 32);
        this.posZ = (int) Math.floor(posZ * 32);
        this.xpValue = xpValue;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.entityID = msg.readVarInt();
        this.posX = msg.readInt();
        this.posY = msg.readInt();
        this.posZ = msg.readInt();
        this.xpValue = msg.readShort();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeVarInt(this.entityID);
        msg.writeInt(this.posX);
        msg.writeInt(this.posY);
        msg.writeInt(this.posZ);
        msg.writeShort(this.xpValue);
    }
}
