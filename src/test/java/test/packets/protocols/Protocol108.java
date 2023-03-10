package test.packets.protocols;

import test.NetworkManager;
import test.PlayerConnection;
import test.packets.Status;
import test.packets.out.PacketPlayOutJoinGame108;

/**
 * Created by Rigner on 31/08/16 for project Limbo.
 * All rights reserved.
 */
public class Protocol108 extends Protocol107 {
    public Protocol108(NetworkManager networkManager) {
        super(networkManager);

        this.registerPacketOut(Status.PLAY, 0x23, PacketPlayOutJoinGame108.class);
    }

    @Override
    public void sendJoinGame(PlayerConnection playerConnection, int entityId, byte gameMode, int dimension, byte difficulty, byte maxPlayers, String levelType, boolean debugInfo) {
        this.networkManager.sendPacket(playerConnection, new PacketPlayOutJoinGame108(entityId, gameMode, dimension, difficulty, maxPlayers, levelType, debugInfo));
    }

    @Override
    public int[] getVersions() {
        return new int[]{108, 109};
    }
}
