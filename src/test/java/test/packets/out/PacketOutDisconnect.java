package test.packets.out;


import test.packets.PacketSerializer;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketOutDisconnect implements PacketOut {
    private final String json;

    public PacketOutDisconnect(String message) {
        this.json = "{\"text\":\"" + message.replaceAll("\"", "\\\"") + "\"}";
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writeString(this.json);
    }
}
