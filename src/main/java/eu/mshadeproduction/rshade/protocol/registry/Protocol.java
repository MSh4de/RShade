package eu.mshadeproduction.rshade.protocol.registry;

import eu.mshadeproduction.mwork.MOptional;
import eu.mshadeproduction.rshade.protocol.packets.PacketHandshake;
import eu.mshadeproduction.rshade.protocol.packets.login.*;
import eu.mshadeproduction.rshade.protocol.packets.play.*;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusOutStatusResponse;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusPing;
import eu.mshadeproduction.rshade.protocol.packets.status.PacketStatusRequest;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public enum Protocol {

    HANDSHAKING(0) {
        {
            serverBound.register(0, PacketHandshake.class);
        }
    },
    STATUS(1) {
        {
            serverBound.register(0, PacketStatusRequest.class);
            serverBound.register(1, PacketStatusPing.class);

            clientBound.register(0, PacketStatusOutStatusResponse.class);
            clientBound.register(1, PacketStatusPing.class);
        }
    },
    LOGIN(2) {
        {
            serverBound.register(0, PacketLoginStart.class);
            serverBound.register(1, PacketLoginEncryptionResponse.class);

            clientBound.register(0, PacketLoginDisconnect.class);
            clientBound.register(1, PacketLoginEncryptionBegin.class);
            clientBound.register(2, PacketLoginSuccess.class);
            clientBound.register(3, PacketLoginSetCompression.class);
        }
    },
    PLAY(3) {
        {
            clientBound.register(0, PacketPlayClientKeepAlive.class);
            clientBound.register(1, PacketPlayJoinGame.class);
            clientBound.register(2, PacketPlayChat.class);
            clientBound.register(3, PacketPlayUpdateTime.class);
            clientBound.register(4, PacketPlayEntityEquipment.class);
            clientBound.register(5, PacketPlaySpawnLocation.class);
            clientBound.register(6, PacketPlayUpdateHealth.class);
            clientBound.register(7, PacketPlayPlayerRespawn.class);
            clientBound.register(8, PacketPlayPlayerPositionAndLook.class);
            clientBound.register(9, PacketPlayClientHeldItemChange.class);
            clientBound.register(10, PacketPlayUseBed.class);
            clientBound.register(11, PacketPlayClientAnimation.class);
            clientBound.register(12, PacketPlaySpawnPlayer.class);
            clientBound.register(13, PacketPlayCollectItem.class);
            clientBound.register(14, PacketPlaySpawnObject.class);
            clientBound.register(15, PacketPlaySpawnMob.class);
            clientBound.register(16, PacketPlaySpawnEntityPainting.class);
            clientBound.register(17, PacketPlaySpawnExperienceOrb.class);
            clientBound.register(19, PacketPlayDestroyEntity.class);
            clientBound.register(20, PacketPlayEntity.class);
            clientBound.register(21, PacketPlayRelEntityMove.class);
            clientBound.register(22, PacketPlayEntityLook.class);
            clientBound.register(23, PacketPlayRelEntityMoveLook.class);
            clientBound.register(24, PacketPlayEntityTeleport.class);
            clientBound.register(25, PacketPlayEntityHeadLook.class);
            clientBound.register(26, PacketPlayEntityStatus.class);
            clientBound.register(27, PacketPlayEntityAttach.class);
            clientBound.register(33, PacketPlayOutMapChunk.class);
            clientBound.register(38, PacketPlayOutMapChunkBulk.class);
            clientBound.register(57, PacketPlayPlayerAbilities.class);


            serverBound.register(0, PacketPlayServerKeepAlive.class);
            serverBound.register(1, PacketPlayChatMessage.class);
            serverBound.register(2, PacketPlayUseEntity.class);
            serverBound.register(3, PacketPlayFlying.class);
            serverBound.register(4, PacketPlayPosition.class);
            serverBound.register(5, PacketPlayLook.class);
            serverBound.register(6, PacketPlayPositionLook.class);
            serverBound.register(7, PacketPlayPlayerDigging.class);
            // PacketPlayPlayerBlockPlacement
            serverBound.register(9, PacketPlayServerHeldItemChange.class);
            serverBound.register(10, PacketPlayServerAnimation.class);
            serverBound.register(11, PacketPlayEntityAction.class);
            serverBound.register(12, PacketPlayPlayerInput.class);
            serverBound.register(13, PacketPlayCloseWindow.class);
            serverBound.register(14, PacketPlayClickWindow.class);
            serverBound.register(15, PacketPlayConfirmTransaction.class);
            serverBound.register(16, PacketPlayCreativeInventoryAction.class);
            serverBound.register(17, PacketPlayEnchantItem.class);
            serverBound.register(19, PacketPlayPlayerAbilities.class);
            serverBound.register(20, PacketPlayInEntity.class);
            serverBound.register(21, PacketPlayInSettings.class);
            serverBound.register(22, PacketPlayInEntityLook.class);
            serverBound.register(23, PacketPlayInRelEntityMoveLook.class);
        }
    };

    private static final Map<Integer, Protocol> STATE_BY_ID = new HashMap<>();

    static {
        for (Protocol registry : values()) {
            STATE_BY_ID.put(registry.stateId, registry);
        }
    }


    private final int stateId;
    public final PacketRegistry serverBound = new PacketRegistry();
    public final PacketRegistry clientBound = new PacketRegistry();

    Protocol(int stateId) {
        this.stateId = stateId;
    }

    public static Protocol getById(int stateId) {
        return STATE_BY_ID.get(stateId);
    }

    public static class PacketRegistry {

        private final Map<Integer, Class<?>> packetsById = new HashMap<>();
        private final Map<Class<?>, Integer> packetIdByClass = new HashMap<>();

        public <T> MOptional<T> getPacket(int packetId, Class<T> aClass) {
            if (packetsById.containsKey(packetId)) {
                Class<?> clazz = packetsById.get(packetId);
                try {
                    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
                    declaredConstructor.setAccessible(true);
                    return MOptional.of(aClass.cast(declaredConstructor.newInstance()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return MOptional.empty();
        }

        public int getPacketId(Class<?> packetClass) {
            return packetIdByClass.getOrDefault(packetClass, -1);
        }

        public void register(int packetId, Class<?> clazz) {
            packetsById.put(packetId, clazz);
            packetIdByClass.put(clazz, packetId);
        }

    }

}