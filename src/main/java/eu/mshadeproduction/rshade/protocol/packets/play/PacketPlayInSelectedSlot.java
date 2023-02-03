package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;

public class PacketPlayInSelectedSlot implements PacketIn {

    private int slot;

    private PacketPlayInSelectedSlot() {
    }

    public PacketPlayInSelectedSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.slot = msg.readVarInt();
    }
}
