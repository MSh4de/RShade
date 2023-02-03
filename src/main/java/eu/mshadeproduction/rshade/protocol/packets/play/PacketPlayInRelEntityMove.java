package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;

public class PacketPlayInRelEntityMove extends PacketPlayInEntity {

    private PacketPlayInRelEntityMove() {
        this.something = true;
    }

    public PacketPlayInRelEntityMove(int entityId, byte x, byte y, byte z, boolean onGround) {
        super(entityId);
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.onGround = onGround;
    }

    @Override
    public void decode(ByteMessage msg) {
        super.decode(msg);
        this.posX = msg.readByte();
        this.posY = msg.readByte();
        this.posZ = msg.readByte();
        this.onGround = msg.readBoolean();
    }
}
