package test.packets.in;


import test.NetworkManager;
import test.PlayerConnection;
import test.packets.PacketSerializer;
import test.packets.Status;
import test.packets.out.PacketLoginOutSuccess;
import test.world.Chunk;

import java.io.IOException;

/**
 * Created by Rigner on 29/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketLoginInStart implements PacketIn {
    private String userName;

    @Override
    public void readPacket(PacketSerializer packetSerializer) throws IOException {
        this.userName = packetSerializer.readString();
    }

    @Override
    public void handlePacket(NetworkManager networkManager, PlayerConnection playerConnection) {
        System.out.println(userName + ": 47");
        playerConnection.setUserName(this.userName);
        networkManager.sendPacket(playerConnection, new PacketLoginOutSuccess(playerConnection.getUuid(), this.userName));
        playerConnection.setStatus(Status.PLAY);
        playerConnection.getProtocol().sendJoinGame(playerConnection, 1, (byte) 1, networkManager.getLimboConfiguration().getDimension(), (byte) 0, (byte) 1, "default", networkManager.getLimboConfiguration().isReducedDebugInfo());
        new Thread(() ->
        {
            try {
                for (Chunk[] tab : networkManager.getWorld().getChunks())
                    for (Chunk chunk : tab)
                        if (chunk != null) {
                            playerConnection.getProtocol().sendChunk(playerConnection, chunk);
                            //chunk.sendTileEntities(playerConnection);
                        }
                Thread.sleep(300);
                System.out.println("HEYYYYYYYYYYYYYYYYYYY");
                playerConnection.getProtocol().sendPosition(playerConnection, networkManager.getLimboConfiguration().getSpawnX(), networkManager.getLimboConfiguration().getSpawnY(), networkManager.getLimboConfiguration().getSpawnZ(), networkManager.getLimboConfiguration().getSpawnYaw(), networkManager.getLimboConfiguration().getSpawnPitch());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
}
