package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayUpdateHealth implements Packet {

    private float health;
    private int foodLevel;
    private float saturationLevel;

    private PacketPlayUpdateHealth() {
    }

    public PacketPlayUpdateHealth(float health, int foodLevel, float saturationLevel) {
        this.health = health;
        this.foodLevel = foodLevel;
        this.saturationLevel = saturationLevel;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.health = msg.readFloat();
        this.foodLevel = msg.readVarInt();
        this.saturationLevel = msg.readFloat();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeFloat(this.health);
        msg.writeVarInt(this.foodLevel);
        msg.writeFloat(this.saturationLevel);
    }
}
