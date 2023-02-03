package test.packets.out;


import test.packets.PacketSerializer;
import test.world.TileEntity;
import test.world.nbt.NBTTag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Rigner on 31/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketPlayOutUpdateBlockEntity110 implements PacketOut {
    private final TileEntity tileEntity;
    private final byte action;

    public PacketPlayOutUpdateBlockEntity110(TileEntity tileEntity, byte action) {
        this.tileEntity = tileEntity;
        this.action = action;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writePosition(this.tileEntity.getBlockPosition());
        packetSerializer.writeByte(this.action);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        NBTTag.writeTag(outputStream, this.tileEntity.getData(), true);
        for (byte b : outputStream.toByteArray())
            packetSerializer.writeByte(b);
    }
}
