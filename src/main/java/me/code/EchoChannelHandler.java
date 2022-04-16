package me.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {


    //denna metoden kommer automatiskt så fort man får in ett meddelande..från en klient..
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        ByteBuf clone = Unpooled.copiedBuffer(byteBuf);
        ctx.channel().writeAndFlush(clone); // channel själva klienten..
        //när man skickar meddelande ovan kommer den gå igenom ???? output pipeline listan.. efter alla hanterare..
    }

}

