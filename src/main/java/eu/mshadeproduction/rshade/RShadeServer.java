package eu.mshadeproduction.rshade;

import eu.mshadeproduction.mwork.dispatcher.DefaultDispatcherDriver;
import eu.mshadeproduction.mwork.dispatcher.DispatcherDriver;
import eu.mshadeproduction.rshade.listener.*;
import eu.mshadeproduction.rshade.logger.DefaultLogger;
import eu.mshadeproduction.rshade.protocol.ClientBridge;
import eu.mshadeproduction.rshade.protocol.PacketIn;
import eu.mshadeproduction.rshade.world.WorldManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.impl.StaticLoggerBinder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class RShadeServer {

    private Connections connections = new Connections();
    private EventLoopGroup eventExecutors;
    private WorldManager worldManager;

    private DispatcherDriver<PacketIn> packetInDefaultDispatcherDriver = new DefaultDispatcherDriver<>();

    public RShadeServer() throws IOException {
        this.worldManager = new WorldManager();

        this.eventExecutors = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        new ServerBootstrap()
                .group(eventExecutors)
                .childHandler(new RShadeChannelHandler(this))
                .channel(NioServerSocketChannel.class)
                .localAddress("0.0.0.0", 25565)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .bind();

        this.packetInDefaultDispatcherDriver.register(new HandshakeListener());
        this.packetInDefaultDispatcherDriver.register(new LoginDisconnectListener());
        this.packetInDefaultDispatcherDriver.register(new LoginEncryptionBeginListener());
        this.packetInDefaultDispatcherDriver.register(new LoginPluginResponseListener());
        this.packetInDefaultDispatcherDriver.register(new LoginStartListener(this));
        this.packetInDefaultDispatcherDriver.register(new StatusPingListener());
        this.packetInDefaultDispatcherDriver.register(new StatusRequestListener());
        this.packetInDefaultDispatcherDriver.register(new SettingsListener(this));
        this.packetInDefaultDispatcherDriver.register(new PlayerPositionListener(this));

        eventExecutors.scheduleAtFixedRate(this::broadcastKeepAlive, 0L, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws IOException {

        try {
            StaticLoggerBinder singleton = StaticLoggerBinder.getSingleton();
            Class<? extends StaticLoggerBinder> aClass = singleton.getClass();
            Field loggerFactory = aClass.getDeclaredField("loggerFactory");
            loggerFactory.setAccessible(true);
            loggerFactory.set(singleton, new DefaultLogger());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        System.out.println(

                " /$$$$$$$   /$$$$$$  /$$                       /$$           \n" +
                        "| $$__  $$ /$$__  $$| $$                      | $$           \n" +
                        "| $$  \\ $$| $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$\n" +
                        "| $$$$$$$/|  $$$$$$ | $$__  $$ |____  $$ /$$__  $$ /$$__  $$ \n" +
                        "| $$__  $$ \\____  $$| $$  \\ $$  /$$$$$$$| $$  | $$| $$$$$$$$\n" +
                        "| $$  \\ $$ /$$  \\ $$| $$  | $$ /$$__  $$| $$  | $$| $$_____/\n" +
                        "| $$  | $$|  $$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$  \n" +
                        "|__/  |__/ \\______/ |__/  |__/ \\_______/ \\_______/ \\_______/");
        System.out.println(
                "RShade-1.8 (v0.0.1)     Repository: https://github.com/MShadeProduction/RShade-1.8");
        System.out.println();
        new RShadeServer();
    }

    private void broadcastKeepAlive() {
        connections.getAllConnections().forEach(ClientBridge::sendKeepAlive);
    }

    public Connections getConnections() {
        return connections;
    }

    public String buildServerListResponseJson(String version, int protocol, String motd, int maxPlayers, int playersOnline, BufferedImage favicon) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject versionJson = new JSONObject();
        versionJson.put("name", version);
        versionJson.put("protocol", protocol);
        json.put("version", versionJson);

        JSONObject playersJson = new JSONObject();
        playersJson.put("max", maxPlayers);
        playersJson.put("online", playersOnline);
        json.put("players", playersJson);

        json.put("description", motd);
        
        /*if (favicon != null) {
            if (favicon.getWidth() == 64 && favicon.getHeight() == 64) {
                String base64 = "data:image/png;base64," + ImageUtils.imgToBase64String(favicon, "png");
                json.put("favicon", base64);
            } else {
                console.sendMessage("Server List Favicon must be 64 x 64 in size!");
            }
        }*/

        JSONObject modInfoJson = new JSONObject();
        modInfoJson.put("type", "FML");
        modInfoJson.put("modList", new JSONArray());
        json.put("modinfo", modInfoJson);

        return json.toString();
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public DispatcherDriver<PacketIn> getPacketInDefaultDispatcherDriver() {
        return packetInDefaultDispatcherDriver;
    }
}