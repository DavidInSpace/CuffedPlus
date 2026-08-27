package git.david.cuffedplus.config;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.utils.InfoMessagesHandler;
import net.minecraft.world.entity.player.Player;


public class ConfigHandler {

    public static boolean handleOwnJumpsuitLockBehavior(Player player, boolean bold) {
        if (player.getTags().contains("prisoner")) {
            if (CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("onlyLock") || CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("none")) {
                return false;
            }
        }
        return true;
    }


    public static boolean handleAttackBehavior(Player player, Player target) {
        if (!player.getTags().contains("prisoner")) {
            if (CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior().equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack prisoners or officers ×", false, true);
                return false;
            }

            if (CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior().equalsIgnoreCase("onlyPrisoners") && !target.getTags().contains("prisoner")) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack prisoners ×", false, true);
                return false;
            }

            if (CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior().equalsIgnoreCase("onlyOfficers") && !target.getTags().contains("officer")) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack police officers ×", false, true);
                return false;
            }

        } else {
            if (!CuffedPlusMain.SERVER_CONFIG.canPrisonersAttackWithoutRole() && (!target.getTags().contains("prisoner") && !target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack players without a role ×", false, true);
                return false;
            }

            if (CuffedPlusMain.SERVER_CONFIG.getPrisonersAttackBehavior().equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack other prisoners or officers ×", false, true);
                return false;
            }

            if (CuffedPlusMain.SERVER_CONFIG.getPrisonersAttackBehavior().equalsIgnoreCase("onlyPrisoners") && (!target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack officers ×", false, true);
                return false;
            }

            if (CuffedPlusMain.SERVER_CONFIG.getPrisonersAttackBehavior().equalsIgnoreCase("onlyOfficers") && (!target.getTags().contains("officer") || target.getTags().contains("prisoner"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack other prisoners ×", false, true);
                return false;
            }
        }


        return true;
    }


}
