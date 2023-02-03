package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusPing;

public class StatusPingListener implements DispatcherListener<PacketStatusPing> {

    @Override
    public void handle(PacketStatusPing packetStatusPing, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.sendPacketAndClose(packetStatusPing);
    }

    @Override
    public Class<PacketStatusPing> getGenericClazz() {
        return PacketStatusPing.class;
    }
}
