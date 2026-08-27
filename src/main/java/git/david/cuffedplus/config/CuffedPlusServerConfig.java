package git.david.cuffedplus.config;

import com.lazrproductions.lazrslib.common.config.ConfigCategory;
import com.lazrproductions.lazrslib.common.config.ConfigProperty;
import com.lazrproductions.lazrslib.common.config.LazrConfig;
import net.minecraftforge.fml.config.ModConfig;


public class CuffedPlusServerConfig extends LazrConfig {

    ConfigCategory CUFFED_PLUS_SETTINGS;
    ConfigCategory GENERAL_SETTINGS;
    ConfigCategory PREFIX_SETTINGS;
    ConfigCategory PLAYERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS;
    ConfigCategory PRISONERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS;

    ConfigProperty<Integer> INCREASE_REINFORCED_BLOCKS_STRENGTH;
    ConfigProperty<Boolean> KEEP_LOCKED_GEAR_ON_DEATH;
    ConfigProperty<String> PLAYERS_ATTACK_BEHAVIOR;
    ConfigProperty<Boolean> CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE;
    ConfigProperty<String> PRISONERS_ATTACK_BEHAVIOR;
    ConfigProperty<Boolean> ALLOW_UNLOCKING_TIME_LOCKED_RESTRAINTS;
    ConfigProperty<Boolean> ALLOW_BREAKING_TIME_LOCKED_RESTRAINTS;
    ConfigProperty<Boolean> ALLOW_LOCKPICKING_TIME_LOCKED_RESTRAINTS;
    ConfigProperty<Boolean> SHOW_INFO_MESSAGES;
    ConfigProperty<Boolean> SHOW_SUCCESS_MESSAGES;
    ConfigProperty<Boolean> SHOW_FAIL_MESSAGES;
    ConfigProperty<Boolean> PUT_PLAYERS_IN_CREATIVE_WHEN_ANTIGOD_RESTRAINTS_TIME_LOCK_RUNS_OUT;
    ConfigProperty<Boolean> ALLOW_RESTRAINED_PLAYERS_EXECUTE_COMMANDS;


    ConfigProperty<Boolean> SHOW_ROLE_PREFIX;
    ConfigProperty<Boolean> ROLE_PREFIX_BOLD;

    ConfigProperty<String> PRISONER_ROLE_PREFIX;
    ConfigProperty<String> OFFICER_ROLE_PREFIX;


    ConfigProperty<String> OTHER_PLAYERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String> OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR;

    ConfigProperty<String> PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR;

    ConfigProperty<String> OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR;

    ConfigProperty<String> PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR;
    ConfigProperty<String> OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR;


    /** PRISONERS **/

    ConfigProperty<String> OTHER_PRISONERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_GEAR_MESSAGE_BEHAVIOR;

    ConfigProperty<String> PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR;
    ConfigProperty<String> PRISONERS_OWN_LOCK_MESSAGE_BEHAVIOR;

    ConfigProperty<String> OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_LOCK_MESSAGE_BEHAVIOR;

    ConfigProperty<String> PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR;
    ConfigProperty<String> PRISONERS_OWN_BINDING_MESSAGE_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR;
    ConfigProperty<String> OTHER_PRISONERS_BINDING_MESSAGE_BEHAVIOR;

    public CuffedPlusServerConfig(String name, ModConfig.Type type) {
        super(name, type);
    }

    @Override
    public void registerProperties() {
        CUFFED_PLUS_SETTINGS = createCategory(new ConfigCategory(this, "Cuffed Plus Settings"), (c1) -> {

            GENERAL_SETTINGS = createCategory(new ConfigCategory(this, "General Settings"), (c5) -> {
                KEEP_LOCKED_GEAR_ON_DEATH = c5.putProperty(new ConfigProperty<Boolean>(this, "Keep Locked Gear On Death", "Locked jumpsuits wont drop on death even if keepInventory gamerule is turned off", true));
                // INCREASE_REINFORCED_BLOCKS_STRENGTH = c5.putProperty(new ConfigProperty<Integer>(this, "Reinforced Blocks Strength Increase", "Blocks that should have increased reinforced strength.", 1000));
                PLAYERS_ATTACK_BEHAVIOR = c5.putProperty(new ConfigProperty<String>(this, "Players Attack Behavior", "Who can players attack. players will always able to attack players without a role (Options: \"none\", \"onlyPrisoners\", \"onlyOfficers\", \"both\"", "both"));
                CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE = c5.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Attack Players Without A Role", "Whether prisoners can attack players with no role", false));
                PRISONERS_ATTACK_BEHAVIOR = c5.putProperty(new ConfigProperty<String>(this, "Prisoners Attack Behavior", "Who can prisoners attack. prisoners will always able to attack players without a role (Options: \"none\", \"onlyPrisoners\", \"onlyOfficers\", \"both\")", "onlyPrisoners"));
                //ALLOW_UNLOCKING_TIME_LOCKED_RESTRAINTS = c5.putProperty(new ConfigProperty<Boolean>(this, "Allow Unlocking Time Locked Restraints", "Whether restraints locked with a time lock can be unlocked with their respective key", true));
                ALLOW_BREAKING_TIME_LOCKED_RESTRAINTS = c5.putProperty(new ConfigProperty<Boolean>(this, "Allow Breaking Time Locked Restraints", "Whether restraints locked with a time lock can be broken out of", false));
                // ALLOW_LOCKPICKING_TIME_LOCKED_RESTRAINTS = c5.putProperty(new ConfigProperty<Boolean>(this, "Allow Lockpicking Time Locked Restraints", "Whether restraints locked with a time lock can be lockpicked", true));
                SHOW_INFO_MESSAGES = c5.putProperty(new ConfigProperty<Boolean>(this, "Show Info Messages", "Whether to show (yellow) info message when doing certain interactions (not fully implemented)", true));
                SHOW_SUCCESS_MESSAGES = c5.putProperty(new ConfigProperty<Boolean>(this, "Show Success Messages", "Whether to show (green) success message when doing certain interactions (not fully implemented)", true));
                SHOW_FAIL_MESSAGES = c5.putProperty(new ConfigProperty<Boolean>(this, "Show Fail Messages", "Whether to show (red) fail message when doing certain interactions (not fully implemented)", true));
                PUT_PLAYERS_IN_CREATIVE_WHEN_ANTIGOD_RESTRAINTS_TIME_LOCK_RUNS_OUT = c5.putProperty(new ConfigProperty<Boolean>(this, "Put Players In To Creative When Anti God Restraint Time Lock Runs Out", "Whether to turn the player back in to creative when a time lock on a restraint which has the Anti-God modifier runs out", true));
                ALLOW_RESTRAINED_PLAYERS_EXECUTE_COMMANDS = c5.putProperty(new ConfigProperty<Boolean>(this, "Allow Restrained Players Execute Commands", "Whether to turn the player back in to creative when a time lock on a restraint which has the Anti-God modifier runs out", false));
            });


            PREFIX_SETTINGS = createCategory(new ConfigCategory(this, "Prefix Settings"), (c2) -> {
                SHOW_ROLE_PREFIX = c2.putProperty(new ConfigProperty<Boolean>(this, "Show Role Prefixes", "Whether to show role prefixes in chat.", true));
                ROLE_PREFIX_BOLD = c2.putProperty(new ConfigProperty<Boolean>(this, "Bold Role Prefixes", "Whether role prefixes should appear bold.", true));

                PRISONER_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix", "The prefix displayed for prisoners.", "[INMATE]"));
                OFFICER_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String>(this, "Officer Role Prefix", "The prefix displayed for officers.", "[OFFICER]"));

                // PRISONER_ROLE_PREFIX_COLOR = c2.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix", "What color the prisoner prefix is (in HEX).", "#ff8800"));
                // OFFICER_ROLE_PREFIX_COLOR = c2.putProperty(new ConfigProperty<String>(this, "Officer Role Prefix", "What color the officer prefix is (in HEX).", "#5050ff"));
            });

            PLAYERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS = createCategory(new ConfigCategory(this, "Players Jumpsuit & Ankle Monitor Behavior"), (c3) -> {
                OTHER_PLAYERS_JUMPSUIT_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Jumpsuit Behavior", "Controls the interaction for taking and putting jumpsuits on and off other players (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));
                OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Ankle Monitor Behavior", "Controls the interaction for taking and putting ankle monitors on and off other players (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));

                PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking your own jumpsuit (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking your own ankle monitor (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking jumpsuits on other players (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking ankle monitors on other players  (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\")", "both"));

                PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Tracker Binding", "Controls the interaction for binding and unbinding your own ankle monitor (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
                OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Tracker Binding Behavior", "Controls the interaction for binding and unbinding ankle monitor on other players (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
            });

            PRISONERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS = createCategory(new ConfigCategory(this, "Prisoners Jumpsuit & Ankle Monitor Behavior"), (c4) -> {
                OTHER_PRISONERS_JUMPSUIT_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Jumpsuit Behavior", "Controls the interaction for taking and putting jumpsuits on and off other prisoners (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));
                OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Ankle Monitor Behavior", "Controls the interaction for taking and putting ankle monitors on and off other prisoners (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));

                PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Prisoner Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking your own jumpsuit as a prisoner (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Prisoner Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking your own ankle monitor as a prisoner (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking jumpsuits on other prisoners (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Ankle Monitor Lock Behavior", "\"Controls the interaction for locking and unlocking ankle monitors on other prisoners  (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\")", "both"));

                PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Tracker Binding", "Controls the interaction for binding and unbinding your own ankle monitor as a prisoner (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
                OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other prisoners Tracker Binding Behavior", "Controls the interaction for binding and unbinding ankle monitor on other prisoners (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
            });
        });
    }


    public boolean keepLockedGearOnDeath() {return KEEP_LOCKED_GEAR_ON_DEATH.get();}

    public int increaseReinforcedBlockStrength() {return INCREASE_REINFORCED_BLOCKS_STRENGTH.get();}

    public String getPlayersAttackBehavior() {return PLAYERS_ATTACK_BEHAVIOR.get().toLowerCase();}

    public boolean canPrisonersAttackWithoutRole() {return CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE.get();}

    public String getPrisonersAttackBehavior() {return PRISONERS_ATTACK_BEHAVIOR.get().toLowerCase();}

    public boolean allowUnlockingTimeLockedRestraints() {return ALLOW_UNLOCKING_TIME_LOCKED_RESTRAINTS.get();}

    public boolean allowBreakingTimeLockedRestraints() {return ALLOW_BREAKING_TIME_LOCKED_RESTRAINTS.get();}

    public boolean allowLockpickingTimeLockedRestraints() {return ALLOW_LOCKPICKING_TIME_LOCKED_RESTRAINTS.get();}

    public boolean showInfoMessages() {return SHOW_INFO_MESSAGES.get();}

    public boolean showSuccessMessages() {return SHOW_SUCCESS_MESSAGES.get();}

    public boolean showFailMessages() {return SHOW_FAIL_MESSAGES.get();}

    public boolean putPlayersInToCreativeWhenAntiGodRestraintTimeLockRunsOut() {return PUT_PLAYERS_IN_CREATIVE_WHEN_ANTIGOD_RESTRAINTS_TIME_LOCK_RUNS_OUT.get();}

    public boolean allowRestrainedPlayersExecuteCommands() {return ALLOW_RESTRAINED_PLAYERS_EXECUTE_COMMANDS.get();}

    public boolean showRolePrefixes() {return SHOW_ROLE_PREFIX.get();}

    public boolean rolePrefixesBold() {return ROLE_PREFIX_BOLD.get();}

    public String getPrisonerRolePrefix() {return PRISONER_ROLE_PREFIX.get();}

    public String getOfficerRolePrefix() {return OFFICER_ROLE_PREFIX.get();}

    public String getPrisonerRolePrefixColor() {return PRISONER_ROLE_PREFIX.get().toLowerCase();}

    public String getOfficerRolePrefixColor() {return OFFICER_ROLE_PREFIX.get().toLowerCase();}


    public String getOtherPlayersJumpsuitBehavior() {return OTHER_PLAYERS_JUMPSUIT_BEHAVIOR.get().toLowerCase();}

    public String getOtherPlayersAnkleMonitorBehavior() {return OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR.get().toLowerCase();}

    public String getPlayersOwnJumpsuitLockBehavior() {return PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getPlayersOwnAnkleMonitorLockBehavior() {return PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getOtherPlayersJumpsuitLockBehavior() {return OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getOtherPlayersAnkleMonitorLockBehavior() {return OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getPlayersOwnTrackerBindingBehavior() {return PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR.get().toLowerCase();}

    public String getOtherPlayersTrackerBindingBehavior() {return OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR.get().toLowerCase();}


    public String getOtherPrisonersJumpsuitBehavior() {return OTHER_PRISONERS_JUMPSUIT_BEHAVIOR.get().toLowerCase();}

    public String getOtherPrisonersAnkleMonitorBehavior() {return OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR.get().toLowerCase();}

    public String getPrisonersOwnJumpsuitLockBehavior() {return PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getPrisonersOwnAnkleMonitorLockBehavior() {return PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getOtherPrisonersJumpsuitLockBehavior() {return OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getOtherPrisonersAnkleMonitorLockBehavior() {return OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR.get().toLowerCase();}

    public String getPrisonersOwnTrackerBindingBehavior() {return PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR.get().toLowerCase();}

    public String getOtherPrisonersTrackerBindingBehavior() {return OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR.get().toLowerCase();}
}
