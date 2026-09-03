package git.david.cuffedplus.utils;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.client.ClientConfig;
import git.david.cuffedplus.constants.ConfigIDS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import static git.david.cuffedplus.CuffedPlusMain.DEBUG;


public class InfoMessagesHandler {
    private final static Logger LOGGER = LogUtils.getLogger();

    public static void sendInfoMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!ClientConfig.getBoolValue(ConfigIDS.SHOW_INFO_MESSAGES)) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW), actionbar);
    }

    public static void sendSuccessMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!ClientConfig.getBoolValue(ConfigIDS.SHOW_SUCCESS_MESSAGES)) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN), actionbar);
    }

    public static void sendFailMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (DEBUG)
            LOGGER.info("FAIL MESSAGE: Player {}  message {}  bold {}  actionbar {}", player, message, bold, actionbar);
        if (!ClientConfig.getBoolValue(ConfigIDS.SHOW_FAIL_MESSAGES)) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED), actionbar);

    }

}
