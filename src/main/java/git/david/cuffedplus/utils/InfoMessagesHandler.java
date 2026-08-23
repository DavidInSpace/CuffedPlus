package git.david.cuffedplus.utils;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class InfoMessagesHandler {
    static ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    private static void sendInfoMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!config.showInfoMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW), actionbar);
    }
    private static void sendSuccessMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!config.showSuccessMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN), actionbar);
    }

    private static void sendFailMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!config.showFailMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED), actionbar);

    }




}
