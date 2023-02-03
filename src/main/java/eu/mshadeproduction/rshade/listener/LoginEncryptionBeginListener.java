package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginEncryptionBegin;

public class LoginEncryptionBeginListener implements DispatcherListener<PacketLoginEncryptionBegin> {

    @Override
    public void handle(PacketLoginEncryptionBegin packetLoginEncryptionBegin, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        System.out.println(packetLoginEncryptionBegin.toString());
    }

    @Override
    public Class<PacketLoginEncryptionBegin> getGenericClazz() {
        return PacketLoginEncryptionBegin.class;
    }
}
