package test.packets.out;


import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketStatusOutPong implements PacketOut {
    private final long id;

    public PacketStatusOutPong(long id) {
        this.id = id;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writeLong(this.id);
    }
}
