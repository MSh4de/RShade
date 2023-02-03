package test.packets.out;


import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 29/08/16 for project Limbo.
 * All rights reserved.
 */
public interface PacketOut {
    void write(PacketSerializer packetSerializer) throws IOException;
}
