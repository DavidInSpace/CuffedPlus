package git.david.cuffedplus.config;

import com.lazrproductions.cuffed.CuffedMod;
import net.minecraft.world.entity.player.Player;

public class ConfigHandler {

    public static boolean handleOwnJumpsuitLockBehavior(Player player, boolean bold) {
        if (player.getTags().contains("prisoner"))
            if (config.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("onlyLock") || config.getPrisonersOwnJumpsuitLockBehavior().equalsIgnoreCase("none")) {

                return false;
            }
    }





}
