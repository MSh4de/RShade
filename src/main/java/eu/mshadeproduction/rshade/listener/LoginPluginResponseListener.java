package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginInPluginResponse;

public class LoginPluginResponseListener implements DispatcherListener<PacketLoginInPluginResponse> {

    @Override
    public void handle(PacketLoginInPluginResponse packetLoginInPluginResponse, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);
        clientBridge.setAddress(packetLoginInPluginResponse.getData().readString());
        System.out.println(clientBridge.getAddress());
    }

    @Override
    public Class<PacketLoginInPluginResponse> getGenericClazz() {
        return PacketLoginInPluginResponse.class;
    }
}
