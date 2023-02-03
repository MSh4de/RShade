package eu.mshadeproduction.rshade.world;

import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayOutMapChunk;
import eu.mshadeproduction.rshade.world.noise.SimplexOctaveGenerator;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class WorldManager {

    private World world;
    private ConcurrentMap<ClientBridge, Queue<Chunk>> clientView = new ConcurrentHashMap<>();
    private final SimplexOctaveGenerator simplexOctaveGenerator;

    public WorldManager() {
        this.world = new World("TEST");
        this.simplexOctaveGenerator = new SimplexOctaveGenerator(world.getSeed(), 8);
        this.simplexOctaveGenerator.setScale(0.010D);
    }

    public void addClient(ClientBridge clientBridge) {
        this.clientView.put(clientBridge, new ConcurrentLinkedQueue<>());
    }

    public void removeClient(ClientBridge clientBridge) {
        this.clientView.remove(clientBridge);
    }

    public void updateView(ClientBridge clientBridge, BlockLocation blockLocation) {
        Queue<Chunk> chunkView = new ConcurrentLinkedQueue<>();
        int chunkX = blockLocation.getX() >> 4;
        int chunkZ = blockLocation.getZ() >> 4;
        for (int x = chunkX - clientBridge.getDistanceView(); x <= clientBridge.getDistanceView() + chunkX; x++) {
            for (int z = chunkZ - clientBridge.getDistanceView(); z <= clientBridge.getDistanceView() + chunkZ; z++) {
                if (!world.hasChunk(x, z)) {
                    constructChunk(x, z);
                }
                chunkView.add(world.getChunk(x, z));
            }
        }


        clientView.get(clientBridge).forEach(chunk -> {
            if (!hasView(clientBridge, chunk)) {
                unloadChunk(clientBridge, chunk);
                clientView.get(clientBridge).remove(chunk);
            } else {
                chunkView.remove(chunk);
            }
        });

        chunkView.forEach(chunk -> {
            clientBridge.sendPacket(new PacketPlayOutMapChunk(chunk));
            clientView.get(clientBridge).add(chunk);
        });
    }

    public void unloadChunk(ClientBridge clientBridge, Chunk chunk) {
        clientBridge.sendPacket(new PacketPlayOutMapChunk(chunk, true));
    }

    private boolean hasView(ClientBridge clientBridge, Chunk chunk) {
        return this.clientView.get(clientBridge).contains(chunk);
    }

    private void constructChunk(int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                int noise = (int) (simplexOctaveGenerator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.8D, 1D) * 15D + 50D);
                for (int i = 0; i < noise; i++) {
                    world.setBlock(chunkX * 16 + x, i, chunkZ * 16 + z, 2, 0);
                }
            }
    }
}
