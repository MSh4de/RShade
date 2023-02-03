package test.packets.out;


import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketPlayOutKeepAlive47 implements PacketOut {
    private final int id;

    public PacketPlayOutKeepAlive47(int id) {
        this.id = id;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writeVarInt(this.id);
    }
}
