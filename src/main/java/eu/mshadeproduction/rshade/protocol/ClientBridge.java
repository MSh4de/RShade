package eu.mshadeproduction.rshade.protocol;

import eu.mshadeproduction.mwork.dispatcher.DispatcherContainer;
import eu.mshadeproduction.mwork.dispatcher.DispatcherContainerBuilder;
import eu.mshadeproduction.rshade.GameProfile;
import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.connection.PacketDecoder;
import eu.mshadeproduction.rshade.protocol.connection.PacketEncoder;
import eu.mshadeproduction.rshade.protocol.packets.login.PacketLoginDisconnect;
import eu.mshadeproduction.rshade.protocol.packets.play.PacketPlayClientKeepAlive;
import eu.mshadeproduction.rshade.protocol.registry.Protocol;
import eu.mshadeproduction.rshade.protocol.registry.Version;
import eu.mshadeproduction.rshade.world.BlockLocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ThreadLocalRandom;

public class ClientBridge extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger("");

    private Protocol protocol;
    private final Channel channel;
    private final RShadeServer server;
    private GameProfile gameProfile;
    private Version clientVersion;
    private SocketAddress address;
    private int distanceView;
    private BlockLocation blockLocation;

    public ClientBridge(RShadeServer server, Channel channel) {
        this.server = server;
        this.channel = channel;
        this.address = channel.remoteAddress();
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(String host) {
        this.address = new InetSocketAddress(host, ((InetSocketAddress) this.address).getPort());
    }

    public ClientBridge setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
        return this;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (protocol.equals(Protocol.PLAY)) {
            server.getConnections().removeConnection(this);
            server.getWorldManager().removeClient(this);
        }
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (channel.isActive()) {
            logger.error("Unhandled exception: ", cause);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        DispatcherContainer build = DispatcherContainerBuilder.builder()
                .setContainer(this)
                .setContainer(server)
                .build();
        server.getPacketInDefaultDispatcherDriver().dispatch((PacketIn) msg, build);
    }


    public void disconnectLogin(PacketLoginDisconnect packetLoginDisconnect) {
        if (isConnected() && protocol == Protocol.LOGIN) {
            sendPacketAndClose(packetLoginDisconnect);
        }
    }

    public void sendPacket(PacketOut packet) {
        if (isConnected())
            channel.writeAndFlush(packet, channel.voidPromise());
    }

    public void sendPacketAndClose(PacketOut packet) {
        if (isConnected())
            channel.writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
    }

    public void sendKeepAlive() {
        if (protocol.equals(Protocol.PLAY)) {
            PacketPlayClientKeepAlive keepAlive = new PacketPlayClientKeepAlive(ThreadLocalRandom.current().nextInt());
            sendPacket(keepAlive);
        }
    }

    public void updateProtocol(Protocol protocol) {
        this.protocol = protocol;
        channel.pipeline().get(PacketDecoder.class).updateState(protocol);
        channel.pipeline().get(PacketEncoder.class).updateState(protocol);
    }

    public void writePacket(PacketOut packet) {
        if (isConnected()) channel.write(packet, channel.voidPromise());
    }

    public void flushPackets() {
        if (isConnected()) channel.flush();
    }

    public boolean isConnected() {
        return channel.isActive();
    }

    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    public ClientBridge setClientVersion(Version clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public int getDistanceView() {
        return distanceView;
    }

    public ClientBridge setDistanceView(int distanceView) {
        this.distanceView = distanceView;
        return this;
    }

    public BlockLocation getBlockLocation() {
        return blockLocation;
    }

    public ClientBridge setBlockLocation(BlockLocation blockLocation) {
        this.blockLocation = blockLocation;
        return this;
    }
}
