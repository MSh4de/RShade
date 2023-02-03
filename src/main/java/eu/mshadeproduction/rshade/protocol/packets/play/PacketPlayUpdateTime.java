package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

public class PacketPlayUpdateTime implements Packet {

    private long totalWorldTime;
    private long worldTime;

    private PacketPlayUpdateTime() {
    }

    public PacketPlayUpdateTime(long totalWorldTime, long worldTime, boolean doDaylightCycle) {
        this.totalWorldTime = totalWorldTime;
        this.worldTime = worldTime;
        if (!doDaylightCycle) {
            this.worldTime = -this.worldTime;
            if (this.worldTime == 0L) {
                this.worldTime = -1L;
            }
        }
    }

    public long getTotalWorldTime() {
        return totalWorldTime;
    }

    public long getWorldTime() {
        return worldTime;
    }

    @Override
    public void decode(ByteMessage msg) {
        totalWorldTime = msg.readLong();
        worldTime = msg.readLong();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeLong(totalWorldTime);
        msg.writeLong(worldTime);
    }
}
