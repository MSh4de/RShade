package eu.mshadeproduction.rshade.protocol.connection;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;
import eu.mshadeproduction.rshade.protocol.registry.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger("");
    private Protocol.PacketRegistry mappings;

    public PacketDecoder() {
        updateState(Protocol.HANDSHAKING);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        if (!ctx.channel().isActive() || mappings == null) return;

        ByteMessage msg = new ByteMessage(buf);
        int packetId = msg.readVarInt();
        mappings.getPacket(packetId, PacketIn.class).ifPresent(packetIn -> {
            try {
                packetIn.decode(msg);
            } catch (Exception e) {
                logger.warn(String.format("Cannot decode packet %s: ", packetId)/*, e*/);
            }

            ctx.fireChannelRead(packetIn);
        }).ifNotPresent(() -> logger.warn(String.format("Undefined incoming packet: %s", packetId)));
    }

    public void updateState(Protocol protocol) {
        this.mappings = protocol.serverBound;
    }
}