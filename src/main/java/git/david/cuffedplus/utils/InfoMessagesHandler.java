package git.david.cuffedplus.utils;

import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;


public class InfoMessagesHandler {


    public static void sendInfoMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!CuffedPlusMain.SERVER_CONFIG.showInfoMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW), actionbar);
    }

    public static void sendSuccessMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!CuffedPlusMain.SERVER_CONFIG.showSuccessMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GREEN), actionbar);
    }

    public static void sendFailMessage(Player player, String message, boolean bold, boolean actionbar) {
        if (!CuffedPlusMain.SERVER_CONFIG.showFailMessages()) {return;}
        if (bold)
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), actionbar);
        else
            player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.RED), actionbar);

    }

}
