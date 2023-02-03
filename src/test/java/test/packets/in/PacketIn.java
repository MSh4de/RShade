package test.packets.in;


import test.NetworkManager;
import test.PlayerConnection;
import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 29/08/16 for project Limbo.
 * All rights reserved.
 */
public interface PacketIn {
    void readPacket(PacketSerializer packetSerializer) throws IOException;

    void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection);
}
