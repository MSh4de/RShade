package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayInSettings;

public class SettingsListener implements DispatcherListener<PacketPlayInSettings> {

    private RShadeServer rShadeServer;

    public SettingsListener(RShadeServer rShadeServer) {
        this.rShadeServer = rShadeServer;
    }


    @Override
    public void handle(PacketPlayInSettings packetPlayInSettings, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.setDistanceView(packetPlayInSettings.getViewDistance());

        rShadeServer.getWorldManager().updateView(clientBridge, clientBridge.getBlockLocation());
    }

    @Override
    public Class<PacketPlayInSettings> getGenericClazz() {
        return PacketPlayInSettings.class;
    }
}
