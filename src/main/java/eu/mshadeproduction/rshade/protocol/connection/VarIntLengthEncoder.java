package eu.mshadeproduction.rshade.protocol.connection;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class VarIntLengthEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf buf, ByteBuf out) {
        ByteMessage msg = new ByteMessage(out);
        msg.writeVarInt(buf.readableBytes());
        msg.writeBytes(buf);
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf msg, boolean preferDirect) {
        int anticipatedRequiredCapacity = 5 + msg.readableBytes();
        return ctx.alloc().heapBuffer(anticipatedRequiredCapacity);
    }
}