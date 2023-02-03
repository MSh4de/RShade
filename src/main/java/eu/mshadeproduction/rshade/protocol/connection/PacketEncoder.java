package eu.mshadeproduction.rshade.protocol.connection;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;
import eu.mshadeproduction.rshade.protocol.registry.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    private static final Logger logger = LoggerFactory.getLogger("");

    private Protocol.PacketRegistry registry;

    public PacketEncoder() {
        updateState(Protocol.HANDSHAKING);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, PacketOut packet, ByteBuf out) throws Exception {
        if (registry == null) return;

        ByteMessage msg = new ByteMessage(out);
        int packetId = registry.getPacketId(packet.getClass());

        if (packetId == -1) {
            logger.warn(String.format("Undefined packet class: %s", packet.getClass().getName()));
            return;
        }

        msg.writeVarInt(packetId);

        try {
            packet.encode(msg);
        } catch (Exception e) {
            logger.warn(String.format("Cannot encode packet %s: %s", packetId, packet.getClass().getName()), e);
        }
    }

    public void updateState(Protocol protocol) {
        this.registry = protocol.clientBound;
    }

}