package eu.mshadeproduction.rshade.world;


import java.util.Arrays;
import java.util.Objects;

public class Chunk {

    private int x, z;
    private byte[] blockID = new byte[65536 * 2];
    private byte[] blockLight = new byte[65536 / 2];
    private byte[] skyLight = new byte[65536 / 2];

    public Chunk(int chunkX, int chunkZ) {
        this.x = chunkX;
        this.z = chunkZ;
        Arrays.fill(blockLight, (byte) 15);
    }

    public void setBlock(int x, int y, int z, int id, int meta) {
        int index = x + y * 16 * 16 + z * 16;
        index *= 2;
        this.blockID[index] = ((byte) ((byte) (id << 4) | meta));
        this.blockID[(index + 1)] = ((byte) (id >> 4));
    }

    public byte[] getMap() {
        int byteSize = 196864;
        byte[] bytes = new byte[byteSize];
        int index = 0;
        System.arraycopy(blockID, 0, bytes, index, blockID.length);
        index += blockID.length;
        System.arraycopy(blockLight, 0, bytes, index, blockLight.length);
        index += blockLight.length;
        System.arraycopy(skyLight, 0, bytes, index, skyLight.length);
        for (int i = 0; i < 256; ++i) {
            bytes[index++] = 0;
        }
        return bytes;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chunk)) return false;
        Chunk chunk = (Chunk) o;
        return x == chunk.x && z == chunk.z;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}
