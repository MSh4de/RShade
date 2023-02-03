package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayPosition;
import eu.mshadeproduction.rshade.world.BlockLocation;

public class PlayerPositionListener implements DispatcherListener<PacketPlayPosition> {

    private RShadeServer rShadeServer;

    public PlayerPositionListener(RShadeServer rShadeServer) {
        this.rShadeServer = rShadeServer;
    }

    @Override
    public void handle(PacketPlayPosition packetPlayPosition, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.setBlockLocation(new BlockLocation(((int) packetPlayPosition.getX()), ((int) packetPlayPosition.getY()), ((int) packetPlayPosition.getZ())));
        rShadeServer.getWorldManager().updateView(clientBridge, clientBridge.getBlockLocation());
    }

    @Override
    public Class<PacketPlayPosition> getGenericClazz() {
        return PacketPlayPosition.class;
    }
}
