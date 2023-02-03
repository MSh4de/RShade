package test.packets.in;


import org.json.JSONObject;
import test.NetworkManager;
import test.PlayerConnection;
import test.packets.PacketSerializer;
import test.packets.out.PacketStatusOutResponse;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketStatusInRequest implements PacketIn {
    @Override
    public void readPacket(PacketSerializer packetSerializer) throws IOException {
    }

    @Override
    public void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", new JSONObject()
                .put("name", "MShade")
                .put("protocol", playerConnection.getProtocolId()));
        jsonObject.put("players", new JSONObject()
                .put("max", -1)
                .put("online", 0)
                .put("sample", ""));
        jsonObject.put("description", new JSONObject()
                .put("text", "BIENVENUE"));
        networkManager.sendPacket(playerConnection, new PacketStatusOutResponse(jsonObject.toString()));
    }
}
