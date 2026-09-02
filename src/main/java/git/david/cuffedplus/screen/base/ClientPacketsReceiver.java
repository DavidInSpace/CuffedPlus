package git.david.cuffedplus.screen.base;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.ConfigSaveData;
import git.david.cuffedplus.init.ModNetwork;
import git.david.cuffedplus.net.ConfigSyncPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

import static git.david.cuffedplus.CuffedPlusMain.DEBUG;

public class ClientPacketsReceiver {
    private final static Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (DEBUG) LOGGER.debug("Cuffed+  PlayerLoginEvent Called");
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (DEBUG) LOGGER.debug("Player " + player.getName().getString() + " logged in");
        ConfigSaveData data = ConfigSaveData.compute(player.serverLevel());

        ModNetwork.sendToAllClients(new ConfigSyncPacket(data.getOptions()));

    }

}
