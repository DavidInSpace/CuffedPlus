package git.david.cuffedplus.screen.base;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.ConfigSaveData;
import git.david.cuffedplus.init.ModNetwork;
import git.david.cuffedplus.net.ConfigSyncPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import org.slf4j.Logger;

public class ClientPacketsReceiver {
    private final static Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ConfigSaveData data = ConfigSaveData.compute(player.serverLevel());

        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ConfigSyncPacket(data.getOptions()));

    }

}
