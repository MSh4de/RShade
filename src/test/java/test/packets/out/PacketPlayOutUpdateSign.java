package test.packets.out;


import test.packets.PacketSerializer;
import test.world.SignTileEntity;

import java.io.IOException;

/**
 * Created by Rigner on 30/08/16 for project Limbo.
 * All rights reserved.
 */
public class PacketPlayOutUpdateSign implements PacketOut {
    private final SignTileEntity signTileEntity;

    public PacketPlayOutUpdateSign(SignTileEntity signTileEntity) {
        this.signTileEntity = signTileEntity;
    }

    @Override
    public void write(PacketSerializer packetSerializer) throws IOException {
        packetSerializer.writePosition(this.signTileEntity.getBlockPosition());
        packetSerializer.writeString(this.signTileEntity.getText1());
        packetSerializer.writeString(this.signTileEntity.getText2());
        packetSerializer.writeString(this.signTileEntity.getText3());
        packetSerializer.writeString(this.signTileEntity.getText4());
    }
}
