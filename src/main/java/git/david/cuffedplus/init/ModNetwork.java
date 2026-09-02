package git.david.cuffedplus.init;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.net.ApplyJumpsuitSettingsPacket;
import git.david.cuffedplus.net.C2SConfigPacket;
import git.david.cuffedplus.net.ConfigSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static git.david.cuffedplus.CuffedPlusMain.MODID;

public class ModNetwork {
    private final static Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = "1.0";

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(MODID, "main"))
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(ApplyJumpsuitSettingsPacket.class, 0)
                .encoder(ApplyJumpsuitSettingsPacket::encode)
                .decoder(ApplyJumpsuitSettingsPacket::decode)
                .consumerMainThread(ApplyJumpsuitSettingsPacket::handle)
                .add();

        INSTANCE.messageBuilder(C2SConfigPacket.class, 1)
                .encoder(C2SConfigPacket::encode)
                .decoder(C2SConfigPacket::decode)
                .consumerMainThread(C2SConfigPacket::handle)
                .add();

        INSTANCE.messageBuilder(ConfigSyncPacket.class, 2)
                .encoder(ConfigSyncPacket::encode)
                .decoder(ConfigSyncPacket::new)
                .consumerMainThread(ConfigSyncPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }

    public static void sendToPlayer(Object msg, ServerPlayer target) {
        INSTANCE.send(PacketDistributor.PLAYER.with((Supplier<ServerPlayer>) target), msg);
    }

    public static void sendToAllClients(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }


}
