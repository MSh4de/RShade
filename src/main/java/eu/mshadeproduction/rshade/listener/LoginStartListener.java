package eu.mshadeproduction.rshade.listener;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherListener;
import eu.mshadeproduction.rshade.GameProfile;
import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginStart;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginSuccess;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayClientHeldItemChange;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayJoinGame;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayPlayerAbilities;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayPlayerPositionAndLook;
import eu.mshadeproduction.rshade.protocol.registry.Protocol;
import eu.mshadeproduction.rshade.world.BlockLocation;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

public class LoginStartListener implements DispatcherListener<PacketLoginStart> {

    private RShadeServer rShadeServer;

    public LoginStartListener(RShadeServer rShadeServer) {
        this.rShadeServer = rShadeServer;
    }

    @Override
    public void handle(PacketLoginStart packetLoginStart, DispatcherContainer dispatcherContainer) {
        ClientBridge clientBridge = dispatcherContainer.getContainer(ClientBridge.class);

        clientBridge.setGameProfile(new GameProfile(UUID.nameUUIDFromBytes((("OfflinePlayer: " + packetLoginStart.getUsername()).getBytes(StandardCharsets.UTF_8))), packetLoginStart.getUsername()));
        clientBridge.sendPacket(new PacketLoginSuccess(clientBridge.getGameProfile()));
        dispatcherContainer.getContainer(RShadeServer.class).getConnections().addConnection(clientBridge);
        clientBridge.updateProtocol(Protocol.PLAY);
        clientBridge.setBlockLocation(new BlockLocation(0, 70, 0));
        clientBridge.sendPacket(new PacketPlayJoinGame(1, 1, 0, 0, 1, "default", false));
        clientBridge.sendPacket(new PacketPlayPlayerPositionAndLook(0, 70, 0, 0, 0, Collections.emptySet()));
        clientBridge.sendPacket(new PacketPlayPlayerAbilities(false, true, true, true, 0.05F, 0.1F));
        clientBridge.sendPacket(new PacketPlayClientHeldItemChange(4));

        rShadeServer.getWorldManager().addClient(clientBridge);

        //this.worldProvider.getWorld().getChunkList().forEach(chunk -> clientBridge.sendPacket(new PacketPlayOutMapChunk(chunk, true)));
        //clientBridge.sendPacket(new PacketPlayOutMapChunkBulk(true, this.worldProvider.getWorld().getChunkList()));
    }

    @Override
    public Class<PacketLoginStart> getGenericClazz() {
        return PacketLoginStart.class;
    }
}
