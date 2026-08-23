package git.david.cuffedplus.config;

import com.lazrproductions.cuffed.CuffedMod;
import net.minecraft.world.entity.player.Player;

public class ConfigHandler {
    private static ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    public static boolean handleOwnJumpsuitLockBehavior(Player player, boolean bold) {
        if (player.getTags().contains("prisoner")) {
            if (config.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("onlyLock") || config.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("none")) {

                return false;
            }
        }
        return true;
    }


    public static boolean handleAttackBehavior(Player player, Player target, boolean bold, boolean actionbar) {

        if (config.getPlayersAttackBehavior().equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
            return false;
        }

        if (config.getPlayersAttackBehavior().equalsIgnoreCase("onlyPrisoners") && !target.getTags().contains("prisoner")) {
            return false;
        }

        if (config.getPlayersAttackBehavior().equalsIgnoreCase("onlyOfficers") && !target.getTags().contains("officer")) {
            return false;
        }

        if (player.getTags().contains("prisoner")) {
            if (config.getPrisonersAttackBehavior().equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                return false;
            }

            if (config.getPrisonersAttackBehavior().equalsIgnoreCase("onlyPrisoners") && !target.getTags().contains("prisoner")) {
                return false;
            }

            if (config.getPrisonersAttackBehavior().equalsIgnoreCase("onlyOfficers") && !target.getTags().contains("officer")) {
                return false;
            }
        }


        return true;
    }





}
