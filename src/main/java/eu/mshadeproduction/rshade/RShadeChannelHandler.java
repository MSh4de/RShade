package eu.mshadeproduction.rshade;

import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.connection.PacketDecoder;
import eu.mshadeproduction.rshade.protocol.connection.PacketEncoder;
import eu.mshadeproduction.rshade.protocol.connection.VarIntFrameDecoder;
import eu.mshadeproduction.rshade.protocol.connection.VarIntLengthEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class RShadeChannelHandler extends ChannelInitializer<Channel> {

    private final RShadeServer server;

    public RShadeChannelHandler(RShadeServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("timeout", new ReadTimeoutHandler(/*server.getConfig().getReadTimeout()*/0,
                TimeUnit.MILLISECONDS));
        pipeline.addLast("frame_decoder", new VarIntFrameDecoder());
        pipeline.addLast("frame_encoder", new VarIntLengthEncoder());
        pipeline.addLast("decoder", new PacketDecoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("handler", new ClientBridge(server, channel));

    }
}
