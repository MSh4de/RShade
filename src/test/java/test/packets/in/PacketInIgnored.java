package test.packets.in;


import test.NetworkManager;
import test.PlayerConnection;
import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketInIgnored implements PacketIn {
    @Override
    public void readPacket(PacketSerializer packetSerializer) throws IOException {
    }

    @Override
    public void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection) {
    }
}
