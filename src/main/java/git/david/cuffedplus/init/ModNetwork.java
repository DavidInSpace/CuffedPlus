package git.david.cuffedplus.init;

import git.david.cuffedplus.net.ApplyJumpsuitSettingsPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1.0";

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath("cuffedplus", "main"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(ApplyJumpsuitSettingsPacket.class, 0)
                .encoder(ApplyJumpsuitSettingsPacket::encode)
                .decoder(ApplyJumpsuitSettingsPacket::decode)
                .consumerMainThread(ApplyJumpsuitSettingsPacket::handle)
                .add();
    }
}
