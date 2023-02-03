package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

import java.util.EnumSet;
import java.util.Set;

public class PacketPlayPlayerPositionAndLook implements Packet {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Set<EnumFlags> flags;

    private PacketPlayPlayerPositionAndLook() {
    }

    public PacketPlayPlayerPositionAndLook(double x, double y, double z, float yaw, float pitch, Set<EnumFlags> flags) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.flags = flags;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public Set<EnumFlags> getFlags() {
        return flags;
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeDouble(x);
        msg.writeDouble(y);
        msg.writeDouble(z);
        msg.writeFloat(yaw);
        msg.writeFloat(pitch);
        msg.writeByte(EnumFlags.func_180056_a(this.flags));
    }

    @Override
    public void decode(ByteMessage msg) {
        this.x = msg.readDouble();
        this.y = msg.readDouble();
        this.z = msg.readDouble();
        this.yaw = msg.readFloat();
        this.pitch = msg.readFloat();
        this.flags = EnumFlags.func_180053_a(msg.readUnsignedByte());
    }

    public static enum EnumFlags {
        X(0),
        Y(1),
        Z(2),
        Y_ROT(3),
        X_ROT(4);

        private int field_180058_f;

        private EnumFlags(int p_i45992_3_) {
            this.field_180058_f = p_i45992_3_;
        }

        private int func_180055_a() {
            return 1 << this.field_180058_f;
        }

        private boolean func_180054_b(int p_180054_1_) {
            return (p_180054_1_ & this.func_180055_a()) == this.func_180055_a();
        }

        public static Set<EnumFlags> func_180053_a(int p_180053_0_) {
            Set<PacketPlayPlayerPositionAndLook.EnumFlags> set = EnumSet.<PacketPlayPlayerPositionAndLook.EnumFlags>noneOf(PacketPlayPlayerPositionAndLook.EnumFlags.class);

            for (PacketPlayPlayerPositionAndLook.EnumFlags s08packetplayerposlook$enumflags : values()) {
                if (s08packetplayerposlook$enumflags.func_180054_b(p_180053_0_)) {
                    set.add(s08packetplayerposlook$enumflags);
                }
            }

            return set;
        }

        public static int func_180056_a(Set<PacketPlayPlayerPositionAndLook.EnumFlags> p_180056_0_) {
            int i = 0;

            for (PacketPlayPlayerPositionAndLook.EnumFlags s08packetplayerposlook$enumflags : p_180056_0_) {
                i |= s08packetplayerposlook$enumflags.func_180055_a();
            }

            return i;
        }
    }
}