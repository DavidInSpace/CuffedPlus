package git.david.cuffedplus.config;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.base.ConfigOption;
import git.david.cuffedplus.constants.Styles;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.slf4j.Logger;

import java.util.ArrayList;

public class Config {


    private final static Logger LOGGER = LogUtils.getLogger();
    private static final Style TRUE_BOOL_STYLE = Style.EMPTY.withColor(ChatFormatting.GREEN).withBold(true);
    private static final Style FALSE_BOOL_STYLE = Style.EMPTY.withColor(ChatFormatting.RED).withBold(true);

    private final static Component[] BOOL_OPTIONS = {Component.literal("True").setStyle(TRUE_BOOL_STYLE), Component.literal("False").setStyle(FALSE_BOOL_STYLE)};
    private final static Component[] ROLES_PLAYERS_OPTIONS = {
            Component.literal("none").setStyle(Styles.getDarkRedStyle(true)),
            Component.literal("onlyPrisoners").setStyle(Styles.getPrisonStyle(true)),
            Component.literal("onlyOfficer").setStyle(Styles.getOfficerStyle(true)),
            Component.literal("both").setStyle(Styles.getDarkGreenStyle(true))
    };

    private final static Component[] INTERACTION_BEHAVIOR_OPTIONS = {
            Component.literal("none").setStyle(Styles.getDarkRedStyle(true)),
            Component.literal("onlyPutOn").withStyle(ChatFormatting.BOLD),
            Component.literal("onlyTakeOff").withStyle(ChatFormatting.BOLD),
            Component.literal("both").setStyle(Styles.getDarkGreenStyle(true))
    };

    private final static Component[] BINDING_BEHAVIOR_OPTIONS = {
            Component.literal("none").setStyle(Styles.getDarkRedStyle(true)),
            Component.literal("onlyBind").withStyle(ChatFormatting.BOLD),
            Component.literal("onlyUnbind").withStyle(ChatFormatting.BOLD),
            Component.literal("both").setStyle(Styles.getDarkGreenStyle(true))
    };

    private final static Component[] LOCK_BEHAVIOR_OPTIONS = {
            Component.literal("none").setStyle(Styles.getDarkRedStyle(true)),
            Component.literal("onlyLock").withStyle(ChatFormatting.BOLD),
            Component.literal("onlyUnlock").withStyle(ChatFormatting.BOLD),
            Component.literal("both").setStyle(Styles.getDarkGreenStyle(true))
    };

    public static ArrayList<ConfigOption> OPTIONS = new ArrayList<>();
    public static ArrayList<ConfigOption> GENERAL_OPTIONS = new ArrayList<>();
    public static ArrayList<ConfigOption> ROLES_OPTIONS = new ArrayList<>();
    public static ArrayList<ConfigOption> PLAYERS_OPTIONS = new ArrayList<>();
    public static ArrayList<ConfigOption> PRISONERS_OPTIONS = new ArrayList<>();
    public static ArrayList<ConfigOption> MISC_OPTIONS = new ArrayList<>();

    public static void RegisterConfig() {
        LOGGER.debug("Initializing Config");
        RegisterGeneralConfig();
        RegisterRolesConfig();
        RegisterPlayersConfig();
        RegisterPrisonersConfig();
        RegisterMiscConfig();
        OPTIONS.addAll(GENERAL_OPTIONS);
        OPTIONS.addAll(ROLES_OPTIONS);
        OPTIONS.addAll(PLAYERS_OPTIONS);
        OPTIONS.addAll(PRISONERS_OPTIONS);
        OPTIONS.addAll(MISC_OPTIONS);
        LOGGER.info("Finished Initializing Config");
    }


    private static void RegisterGeneralConfig() {
        LOGGER.debug("Initializing General Config");
        GENERAL_OPTIONS.add(new ConfigOption("KEEP_LOCKED_GEAR_ON_DEATH", "Keep Locked Gear On Death", 2, 0, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("PLAYERS_ATTACK_BEHAVIOR", "Players Attack Behavior", 4, 2, ROLES_PLAYERS_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("PRISONERS_ATTACK_BEHAVIOR", "Prisoners Attack Behavior", 4, 2, ROLES_PLAYERS_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE", "Can Prisoners Attack Players Without Role", 2, 1, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("ALLOW_BREAKING_TIME_LOCKED_RESTRAINTS", "Allow Breaking Time Locked Restraints", 2, 2, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("SHOW_INFO_MESSAGES", "Show Info Messages", 2, 0, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("SHOW_SUCCESS_MESSAGES ", "Show Success Messages", 2, 0, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("SHOW_FAIL_MESSAGES", "Show Fail Messages", 2, 0, BOOL_OPTIONS));
        GENERAL_OPTIONS.add(new ConfigOption("PUT_PLAYERS_IN_CREATIVE_WHEN_ANTIGOD_RESTRAINTS_TIME_LOCK_RUNS_OUT", "Put Players In Creative When Antigod Restraints Time Lock Runs Out", 4, 2, BOOL_OPTIONS));

    }

    private static void RegisterRolesConfig() {
        LOGGER.debug("Initializing Roles Config");
        ROLES_OPTIONS.add(new ConfigOption("SHOW_ROLE_PREFIX", "Show Role Prefixes", 4, 0, BOOL_OPTIONS));
        ROLES_OPTIONS.add(new ConfigOption("ROLE_PREFIX_BOLD", "Bold Role Prefixes", 4, 0, BOOL_OPTIONS));
        ROLES_OPTIONS.add(new ConfigOption("PRISONER_ROLE_PREFIX", "Prisoner Role Prefix", 4, 0, new Component[]{Component.literal("[INMATE]").setStyle(Styles.getPrisonStyle(true)), Component.literal("[PRISONER]").setStyle(Styles.getPrisonStyle(true)), Component.literal("[CONVICT]").setStyle(Styles.getPrisonStyle(true)), Component.literal("[D-CLASS]").setStyle(Styles.getPrisonStyle(true))}));
        ROLES_OPTIONS.add(new ConfigOption("OFFICER_ROLE_PREFIX", "Officer Role Prefix", 4, 0, new Component[]{Component.literal("[OFFICER]").setStyle(Styles.getOfficerStyle(true)), Component.literal("[POLICE]").setStyle(Styles.getOfficerStyle(true))}));
    }

    private static void RegisterPlayersConfig() {
        LOGGER.debug("Initializing Players Config");
        PLAYERS_OPTIONS.add(new ConfigOption("OTHER_PLAYERS_JUMPSUIT_BEHAVIOR", "Other Players Jumpsuit Behavior", 4, 0, INTERACTION_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR", "Other players ankle Monitor Behavior", 4, 2, INTERACTION_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR", "Players Own Jumpsuit Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR", "Players Own Ankle Monitor Lock Behavior", 4, 0, LOCK_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR", "Other Players Jumpsuit Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR", "Other Players Ankle Monitor Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR", "Players Own Tracker Binding Behavior", 4, 0, BINDING_BEHAVIOR_OPTIONS));
        PLAYERS_OPTIONS.add(new ConfigOption("OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR", "Other Players Tracker Binding Behavior", 4, 2, BINDING_BEHAVIOR_OPTIONS));
    }

    private static void RegisterPrisonersConfig() {
        LOGGER.debug("Initializing Prisoners Config");
        PRISONERS_OPTIONS.add(new ConfigOption("OTHER_PRISONERS_JUMPSUIT_BEHAVIOR", "Other Prisoners Jumpsuit Behavior", 4, 0, INTERACTION_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("OTHER_PRISONERS_ANKLE_MONITOR_BEHAVIOR", "Other Prisoners ankle Monitor Behavior", 4, 2, INTERACTION_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR", "Prisoners Own Jumpsuit Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR", "Prisoners Own Ankle Monitor Lock Behavior", 4, 0, LOCK_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR", "Other Prisoners Jumpsuit Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR", "Other Prisoners Ankle Monitor Lock Behavior", 4, 2, LOCK_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR", "Prisoners Own Tracker Binding Behavior", 4, 0, BINDING_BEHAVIOR_OPTIONS));
        PRISONERS_OPTIONS.add(new ConfigOption("OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR", "Other Prisoners Tracker Binding Behavior", 4, 2, BINDING_BEHAVIOR_OPTIONS));
    }

    private static void RegisterMiscConfig() {
        LOGGER.debug("Initializing Misc Config");
        MISC_OPTIONS.add(new ConfigOption("Test", "Test", 4, 0, new Component[]{Component.literal("Test1"), Component.literal("Test2"), Component.literal("Test3"), Component.literal("Test4")}));
    }

    public static ConfigOption getOptionById(String id) {
        for (ConfigOption option : OPTIONS) {
            if (option.getID().equals(id)) {
                return option;
            }
        }
        return null;
    }


    public static void printAllOptions() {
        ConfigSaveData data = new ConfigSaveData();
        for (ConfigOption option : OPTIONS) {
            LOGGER.info("Config ID: {}  Value: {}  Default Value: {}", option.getID(), data.getOptionByID(option.getID()), option.getDefaultValue());
        }
    }


}
