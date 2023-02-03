package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginDisconnect;

public class LoginDisconnectListener implements DispatcherListener<PacketLoginDisconnect> {

    @Override
    public void handle(PacketLoginDisconnect packetLoginDisconnect, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.disconnectLogin(packetLoginDisconnect);
    }

    @Override
    public Class<PacketLoginDisconnect> getGenericClazz() {
        return PacketLoginDisconnect.class;
    }
}
