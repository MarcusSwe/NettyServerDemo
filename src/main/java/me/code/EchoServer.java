package me.code;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }


    // två eventlopps - den ena ska hantera alla anslutningar.. den andra ska hantera skicka och få in meddelanden..
    // typ samma sak som icke netty exemplet med trådar.. skapade ju en ny tråd för varje client..

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new EchoChannelInitializer()); // initializer hanterar när man får in anslutning till servern..

            bootstrap.bind(this.port).sync().channel().closeFuture().sync(); //startar själva servern, binder den till en port..
        } catch (Exception ignored) { }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
