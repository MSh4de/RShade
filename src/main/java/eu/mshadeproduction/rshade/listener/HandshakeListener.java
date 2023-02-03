package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.PacketHandshake;
import eu.mshadeproduction.rshade.protocol.registry.Protocol;

public class HandshakeListener implements DispatcherListener<PacketHandshake> {

    @Override
    public void handle(PacketHandshake packetHandshake, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.setClientVersion(packetHandshake.getVersion());
        clientBridge.updateProtocol(Protocol.getById(packetHandshake.getNextProtocol()));
    }

    @Override
    public Class<PacketHandshake> getGenericClazz() {
        return PacketHandshake.class;
    }
}
