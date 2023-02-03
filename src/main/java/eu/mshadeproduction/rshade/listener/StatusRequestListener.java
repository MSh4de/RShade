package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusOutStatusResponse;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusRequest;

public class StatusRequestListener implements DispatcherListener<PacketStatusRequest> {

    @Override
    public void handle(PacketStatusRequest packetStatusRequest, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.sendPacket(new PacketStatusOutStatusResponse(dispatcherContainer.getContainer(RShadeServer.class)));
    }

    @Override
    public Class<PacketStatusRequest> getGenericClazz() {
        return PacketStatusRequest.class;
    }
}
