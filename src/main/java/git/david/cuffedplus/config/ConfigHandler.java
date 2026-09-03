package git.david.cuffedplus.config;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.mojang.logging.LogUtils;
import git.david.cuffedplus.client.ClientConfig;
import git.david.cuffedplus.constants.ConfigIDS;
import git.david.cuffedplus.utils.InfoMessagesHandler;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;


public class ConfigHandler {
    private final static Logger LOGGER = LogUtils.getLogger();

    public static boolean handleOthersAnkleMonitorBehavior(Player player, String action) {
        // Check whether the player is a prisoner
        if (!player.getTags().contains("prisoner")) {
            // If the player is not a prisoner check for the player values

            // If the config value is "none" the player is not allowed to do anything so immediately return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("none")) {
                InfoMessagesHandler.sendFailMessage(player, " You can't put ankle monitors on others ×", false, false);
                return false;
            }

            // If the config value is "onlyPutOn" and the action and the action is not "putOn" (meaning its "takeOff" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("onlyPutOn") && !action.equalsIgnoreCase("putOn")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not take ankle monitors off other players ×", false, false);
                return false;
            }

            // If the config value is "onlyTakeOff" and the action and the action is not "takeOff" (meaning its "putOn" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("onlyTakeOff") && !action.equalsIgnoreCase("takeOff")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can't put ankle monitors on others ×", false, false);
                return false;
            }
        } else {
            // If the player is a prisoner check for the prisoner values

            // If the config value is "none" the player is not allowed to do anything so immediately return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("putOn"))
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't take ankle monitors off others ×", false, false);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't put ankle monitors on others ×", false, false);
                return false;
            }

            // If the config value is "onlyPutOn" and the action and the action is not "putOn" (meaning its "takeOff" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("onlyPutOn") && !action.equalsIgnoreCase("putOn")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't take ankle monitors off others ×", false, false);
                return false;
            }

            // If the config value is "onlyTakeOff" and the action and the action is not "takeOff" (meaning its "putOn" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_BEHAVIOR).equalsIgnoreCase("onlyTakeOff") && !action.equalsIgnoreCase("takeOff")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't put ankle monitors on others ×", false, false);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOthersJumpsuitBehavior(Player player, String action) {
        // Check whether the player is a prisoner
        if (!player.getTags().contains("prisoner")) {
            // If the player is not a prisoner check for the player values

            // If the config value is "none" the player is not allowed to do anything so immediately return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("none")) {
                InfoMessagesHandler.sendFailMessage(player, " You can't put jumpsuits on others ×", false, false);
                return false;
            }

            // If the config value is "onlyPutOn" and the action and the action is not "putOn" (meaning its "takeOff" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("onlyPutOn") && !action.equalsIgnoreCase("putOn")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not take ankle monitors off other players ×", false, false);
                return false;
            }

            // If the config value is "onlyTakeOff" and the action and the action is not "takeOff" (meaning its "putOn" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("onlyTakeOff") && !action.equalsIgnoreCase("takeOff")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can't put ankle monitors on others ×", false, false);
                return false;
            }
        } else {
            // If the player is a prisoner check for the prisoner values

            // If the config value is "none" the player is not allowed to do anything so immediately return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("putOn"))
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't take jumpsuits off others ×", false, false);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't put jumpsuits on others ×", false, false);
                return false;
            }

            // If the config value is "onlyPutOn" and the action and the action is not "putOn" (meaning its "takeOff" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("onlyPutOn") && !action.equalsIgnoreCase("putOn")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't take jumpsuits off others ×", false, false);
                return false;
            }

            // If the config value is "onlyTakeOff" and the action and the action is not "takeOff" (meaning its "putOn" since there are only 2 possible actions) return false
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_BEHAVIOR).equalsIgnoreCase("onlyTakeOff") && !action.equalsIgnoreCase("takeOff")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners can't put jumpsuits on others ×", false, false);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOwnJumpsuitLockBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "× You can not lock your own jumpsuit ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock your own jumpsuit 🔒", false, true);

                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock your own jumpsuit 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not lock your own jumpsuit ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock their own jumpsuit 🔒", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock their own jumpsuit ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock their own jumpsuit 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock their own jumpsuit ×", false, true);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOwnAnkleMonitorLockBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "× You can not lock your own ankle monitor ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock your own ankle monitor 🔒", false, true);

                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock your own ankle monitor 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not lock your own ankle monitor ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock their own ankle monitor 🔒", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock their own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock their own ankle monitor 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock their own ankle monitor ×", false, true);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOthersJumpsuitLockBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "× You can not lock your others jumpsuits ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock others jumpsuits 🔒", false, true);

                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock others jumpsuits 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not lock others jumpsuits ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock others jumpsuits 🔒", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock others jumpsuits ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock others jumpsuits 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock others jumpsuits ×", false, true);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOthersAnkleMonitorLockBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "× You can not lock your others ankle monitors ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock others ankle monitors 🔒", false, true);

                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You can not unlock others ankle monitors 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× You can not lock others ankle monitors ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("lock"))
                    InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock others ankle monitors 🔒", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock others ankle monitors ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyLock") && !action.equalsIgnoreCase("lock")) {
                InfoMessagesHandler.sendFailMessage(player, "🔒 You are a prisoner!  Prisoners can not unlock others ankle monitors 🔒", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR).equalsIgnoreCase("onlyUnlock") && action.equalsIgnoreCase("unlock")) {
                InfoMessagesHandler.sendFailMessage(player, "× ️️You are a prisoner!  Prisoners can not lock others ankle monitors ×", false, true);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOwnBindingBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("bind"))
                    InfoMessagesHandler.sendFailMessage(player, "× You are not allowed bind your own ankle monitor ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyUnbind") && !action.equalsIgnoreCase("unbind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed bind your own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyBind") && !action.equalsIgnoreCase("bind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("none")) {
                if (action.equalsIgnoreCase("bind"))
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners are not allowed to bind their own ankle monitor ×", false, true);
                else
                    InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners are not allowed to unbind their own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyUnbind") && !action.equalsIgnoreCase("unbind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners are not allowed to bind their own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyBind") && !action.equalsIgnoreCase("bind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  Prisoners are not allowed to unbind their own ankle monitor ×", false, true);
                return false;
            }
        }
        return true;
    }

    public static boolean handleOthersBindingBehavior(Player player, String action) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("none")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyUnbind") && !action.equalsIgnoreCase("unbind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to bind trackers to others ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyBind") && !action.equalsIgnoreCase("bind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }
        } else {
            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("none")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyUnbind") && !action.equalsIgnoreCase("unbind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR).equalsIgnoreCase("onlyBind") && !action.equalsIgnoreCase("bind")) {
                InfoMessagesHandler.sendFailMessage(player, "× You are not allowed to unbind your own ankle monitor ×", false, true);
                return false;
            }
        }
        return true;
    }


    public static boolean handleCommandExecution(Player player) {
        if (player.getTags().contains("prisoner")) {
            if (!ClientConfig.getBoolValue(ConfigIDS.ALLOW_PRISONERS_EXECUTE_COMMANDS)) {
                InfoMessagesHandler.sendFailMessage(player, "You are a prisoner!  Prisoners are not allowed to perform commands!", false, false);
                return false;
            }
        }

        if (!ClientConfig.getBoolValue(ConfigIDS.ALLOW_EXECUTE_COMMANDS_WHILE_RESTRAINED) && CuffedAPI.Capabilities.getRestrainableCapability(player).isRestrained()) {
            InfoMessagesHandler.sendFailMessage(player, "You can't perform commands while you're restrained!", false, false);
            return false;
        }

        return true;
    }


    public static boolean handleAttackBehavior(Player player, Player target) {
        if (!player.getTags().contains("prisoner")) {
            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_ATTACK_BEHAVIOR).equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack prisoners or officers ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_ATTACK_BEHAVIOR).equalsIgnoreCase("onlyPrisoners") && !target.getTags().contains("prisoner")) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack prisoners ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PLAYERS_ATTACK_BEHAVIOR).equalsIgnoreCase("onlyOfficers") && !target.getTags().contains("officer")) {
                InfoMessagesHandler.sendFailMessage(player, "× players can't attack police officers ×", false, true);
                return false;
            }

        } else {
            if (!ClientConfig.getBoolValue(ConfigIDS.CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE) && (!target.getTags().contains("prisoner") && !target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack players without a role ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_ATTACK_BEHAVIOR).equalsIgnoreCase("none") && (target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack other prisoners or officers ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_ATTACK_BEHAVIOR).equalsIgnoreCase("onlyPrisoners") && (!target.getTags().contains("prisoner") || target.getTags().contains("officer"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack officers ×", false, true);
                return false;
            }

            if (ClientConfig.getStringValue(ConfigIDS.PRISONERS_ATTACK_BEHAVIOR).equalsIgnoreCase("onlyOfficers") && (!target.getTags().contains("officer") || target.getTags().contains("prisoner"))) {
                InfoMessagesHandler.sendFailMessage(player, "× You are a prisoner!  prisoners can't attack other prisoners ×", false, true);
                return false;
            }
        }


        return true;
    }


}
