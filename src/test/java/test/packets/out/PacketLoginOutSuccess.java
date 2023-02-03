package test.packets.out;


import test.packets.PacketSerializer;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Rigner on 29/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketLoginOutSuccess implements PacketOut {
    private final UUID uuid;
    private final String userName;

    public PacketLoginOutSuccess(UUID uuid, String userName) {
        this.uuid = uuid;
        this.userName = userName;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writeString(this.uuid.toString());
        packetSerializer.writeString(this.userName);
    }
}
