package me.code;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new EchoChannelHandler()); // lägger till hanteraren till pipelinen, som hanterar alla meddelanden..
    }
}

// när man får en input kommer den kolla i pipelinen efter alla hanterare..
// hade det funnit flera.. hade den bara fortsätt koden ovan och gått till nästa hanterare..