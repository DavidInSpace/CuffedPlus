package git.david.cuffedplus.logic;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.client.ClientConfig;
import git.david.cuffedplus.constants.ConfigIDS;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;


public class RolesLogic {
    private final static Logger LOGGER = LogUtils.getLogger();

    // TODO: Figure out how to make so the role colors are taken from the config


    private static Component getFormattedName(Player player, Component originalName) {
        LOGGER.debug("GET FORMATTED NAME " + ClientConfig.getBoolValue(ConfigIDS.SHOW_ROLE_PREFIX) + "    " + ClientConfig.getStringValue(ConfigIDS.PRISONER_ROLE_PREFIX));

        LOGGER.info("SHOULD SHOW NAME {}", player.shouldShowName());
        player.setCustomNameVisible(true);

        MutableComponent prefix;
        if (ClientConfig.getBoolValue(ConfigIDS.SHOW_ROLE_PREFIX)) {
            if (player.getTags().contains("prisoner")) {
                prefix = Component.literal(ClientConfig.getStringValue(ConfigIDS.PRISONER_ROLE_PREFIX) + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff8800)).withBold(ClientConfig.getBoolValue(ConfigIDS.ROLE_PREFIX_BOLD)));
                return prefix.append(originalName);
            } else if (player.getTags().contains("officer")) {
                prefix = Component.literal(ClientConfig.getStringValue(ConfigIDS.OFFICER_ROLE_PREFIX) + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x5050ff)).withBold(ClientConfig.getBoolValue(ConfigIDS.ROLE_PREFIX_BOLD)));
                return prefix.append(originalName);
            }
        }
        return originalName;
    }


    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();
        Component originalName = event.getUsername();
        Component newName = getFormattedName(player, originalName);
        event.setDisplayname(newName);
    }
}
