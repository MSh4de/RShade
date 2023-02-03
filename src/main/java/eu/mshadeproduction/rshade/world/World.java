package eu.mshadeproduction.rshade.world;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private static Random random = new Random();

    private String name;
    private Map<BlockLocation, Chunk> chunks = new ConcurrentHashMap<>();
    private long seed = random.nextLong();

    public World(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getSeed() {
        return seed;
    }

    public void setBlock(int x, int y, int z, int id, int meta) {
        Chunk chunk = getChunk(x >> 4, z >> 4);
        chunk.setBlock(x & 0xF, y, z & 0xF, id, meta);
    }

    public Chunk getChunk(int x, int z) {
        BlockLocation blockLocation = new BlockLocation(x, 0, z);
        if (!this.chunks.containsKey(blockLocation)) {
            this.chunks.put(blockLocation, new Chunk(blockLocation.getX(), blockLocation.getZ()));
        }
        return this.chunks.get(blockLocation);
    }

    public boolean hasChunk(int x, int z) {
        return this.chunks.containsKey(new BlockLocation(x, 0, z));
    }

    public Collection<Chunk> getChunkList() {
        return this.chunks.values();
    }

    private int inverseShiftBinary(int i) {
        String s = Integer.toBinaryString(i < 0 ? i * (-1) : i);
        boolean signe = i >= 0;
        if (s.length() <= 4) return i;
        int a = Integer.parseInt(s.substring(s.length() - 4), 2);
        return signe ? a : a * (-1);
    }
}
