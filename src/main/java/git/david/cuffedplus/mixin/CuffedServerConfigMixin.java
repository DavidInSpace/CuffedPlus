package git.david.cuffedplus.mixin;

import com.lazrproductions.cuffed.config.CuffedServerConfig;
import com.lazrproductions.lazrslib.common.config.ConfigCategory;
import com.lazrproductions.lazrslib.common.config.ConfigProperty;
import com.lazrproductions.lazrslib.common.config.LazrConfig;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import net.minecraftforge.fml.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@SuppressWarnings({"AddedMixinMembersNamePattern", "MissingUnique"})
@Mixin(CuffedServerConfig.class)
public abstract class CuffedServerConfigMixin extends LazrConfig implements ICuffedPlusServerConfigMixin {

    ConfigCategory CUFFED_PLUS_SETTINGS;
    ConfigCategory PREFIX_SETTINGS;
    ConfigCategory PLAYERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS;
    ConfigCategory PRISONERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS;
    ConfigCategory BLOCKS_SETTINGS;

    ConfigProperty<Boolean> SHOW_ROLE_PREFIX;
    ConfigProperty<Boolean> ROLE_PREFIX_BOLD;

    ConfigProperty<String> PRISONER_ROLE_PREFIX;
    ConfigProperty<String> POLICE_ROLE_PREFIX;
    ConfigProperty<String> PRISONER_ROLE_PREFIX_COLOR;
    ConfigProperty<String> POLICE_ROLE_PREFIX_COLOR;

    ConfigProperty<String> GET_OWN_PLAYERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String> GET_OWN_PLAYERS_ANKLE_MONITOR_BEHAVIOR;

    ConfigProperty<String> GET_PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR;

    ConfigProperty<String> GET_OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR;


    ConfigProperty<String> GET_OTHER_PRISONERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR;

    ConfigProperty<String> GET_PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR;

    ConfigProperty<String> GET_PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR;
    ConfigProperty<String> GET_OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR;

    ConfigProperty<Integer> INCREASE_REINFORCED_BLOCKS_STRENGTH;


    public CuffedServerConfigMixin(String name, ModConfig.Type type) {
        super(name, type);
    }
    // TODO: Change the descriptions to always start with "whether..." and put options
    @Inject(method = "registerProperties", at = @At("HEAD"), remap = false)
    public void addRegisterProperties(CallbackInfo ci) {
        CUFFED_PLUS_SETTINGS = createCategory(new ConfigCategory(this, "Cuffed Plus Settings"), (c1) -> {

            PREFIX_SETTINGS = createCategory(new ConfigCategory(this, "Prefix Settings"), (c2) -> {
                SHOW_ROLE_PREFIX = c2.putProperty(new ConfigProperty<Boolean>(this, "Show Role Prefixes", "Whether to show role prefixes in chat.", true));
                ROLE_PREFIX_BOLD = c2.putProperty(new ConfigProperty<Boolean>(this, "Bold Role Prefixes", "Whether role prefixes should appear bold.", true));

                PRISONER_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix", "The prefix displayed for prisoners.", "[INMATE]"));
                POLICE_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String>(this, "Officer Role Prefix", "The prefix displayed for officers.", "[OFFICER]"));

                PRISONER_ROLE_PREFIX_COLOR = c2.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix", "What color the prisoner prefix is (in HEX).", "[INMATE]"));
                POLICE_ROLE_PREFIX_COLOR = c2.putProperty(new ConfigProperty<String>(this, "Officer Role Prefix", "What color the officer prefix is (in HEX).", "[OFFICER]"));
            });

            PLAYERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS = createCategory(new ConfigCategory(this, "Players Jumpsuit & Ankle Monitor Behavior"), (c3) -> {
                GET_OWN_PLAYERS_JUMPSUIT_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Jumpsuit Behavior", "Controls the interaction for taking and putting jumpsuits on and off other players (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));
                GET_OWN_PLAYERS_ANKLE_MONITOR_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Ankle Monitor Behavior", "Controls the interaction for taking and putting ankle monitors on and off other players (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));

                GET_PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking your own jumpsuit (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking your own ankle monitor (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking jumpsuits on other players (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking ankle monitors on other players  (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\")", "both"));

                GET_PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Own Tracker Binding", "Controls the interaction for binding and unbinding your own ankle monitor (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
                GET_OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR = c3.putProperty(new ConfigProperty<String>(this, "Other Players Tracker Binding Behavior", "Controls the interaction for binding and unbinding ankle monitor on other players (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));

            });

            PRISONERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS = createCategory(new ConfigCategory(this, "Prisoners Jumpsuit & Ankle Monitor Behavior"), (c4) -> {
                GET_OTHER_PRISONERS_JUMPSUIT_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Jumpsuit Behavior", "Controls the interaction for taking and putting jumpsuits on and off other prisoners (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));
                GET_OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Ankle Monitor Behavior", "Controls the interaction for taking and putting ankle monitors on and off other prisoners (Options: \"none\", \"onlyPutOn\", \"onlyTakeOff\", \"both\").", "both"));

                GET_PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Prisoner Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking your own jumpsuit as a prisoner (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Prisoner Ankle Monitor Lock Behavior", "Controls the interaction for locking and unlocking your own ankle monitor as a prisoner (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Jumpsuit Lock Behavior", "Controls the interaction for locking and unlocking jumpsuits on other prisoners (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\").", "both"));
                GET_OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other Prisoners Ankle Monitor Lock Behavior", "\"Controls the interaction for locking and unlocking ankle monitors on other prisoners  (Options: \"none\", \"onlyLock\", \"onlyUnlock\", \"both\")", "both"));

                GET_PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Own Tracker Binding", "Controls the interaction for binding and unbinding your own ankle monitor as a prisoner (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
                GET_OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR = c4.putProperty(new ConfigProperty<String>(this, "Other prisoners Tracker Binding Behavior", "Controls the interaction for binding and unbinding ankle monitor on other prisoners (Options: \"none\", \"onlyBind\", \"onlyUnbind\", \"both\")", "both"));
            });

            BLOCKS_SETTINGS = createCategory(new ConfigCategory(this, "Blocks Settings"), (c5) -> {
                INCREASE_REINFORCED_BLOCKS_STRENGTH = c5.putProperty(new ConfigProperty<Integer>(this, "Reinforced Blocks Strength Increase", "Blocks that should have increased reinforced strength.", 1000));
            });
        });
    }


    @Override public boolean showRolePrefixes() {return SHOW_ROLE_PREFIX.get();}
    @Override public boolean rolePrefixesBold() {return ROLE_PREFIX_BOLD.get();}

    @Override public String getPrisonerRolePrefix() {return PRISONER_ROLE_PREFIX.get();}
    @Override public String getOfficerRolePrefix() {return POLICE_ROLE_PREFIX.get();}
    @Override public String getPrisonerRolePrefixColor() {return PRISONER_ROLE_PREFIX.get();}
    @Override public String getOfficerRolePrefixColor() {return POLICE_ROLE_PREFIX.get();}


    @Override public String getOtherPlayersJumpsuitBehavior() {return GET_OWN_PLAYERS_JUMPSUIT_BEHAVIOR.get();}
    @Override public String getOtherPlayersAnkleMonitorBehavior() {return GET_OWN_PLAYERS_ANKLE_MONITOR_BEHAVIOR.get();}

    @Override public String getPlayersOwnJumpsuitLockBehavior() {return GET_PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR.get();}
    @Override public String getPlayersOwnAnkleMonitorLockBehavior() {return GET_PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR.get();}
    @Override public String getOtherPlayersJumpsuitLockBehavior() {return GET_OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR.get();}
    @Override public String getOtherPlayersAnkleMonitorLockBehavior() {return GET_OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR.get();}

    @Override public String getPlayersOwnTrackerBindingBehavior() {return GET_PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR.get();}
    @Override public String getOtherPlayersTrackerBindingBehavior() {return GET_OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR.get();}


    @Override public String getOtherPrisonersJumpsuitBehavior() {return GET_OTHER_PRISONERS_JUMPSUIT_BEHAVIOR.get();}
    @Override public String getOtherPrisonersAnkleMonitorBehavior() {return GET_OTHER_PRISONERS_OWN_ANKLE_MONITOR_BEHAVIOR.get();}

    @Override public String getPrisonersOwnJumpsuitLockBehavior() {return GET_PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR.get();}
    @Override public String getPrisonersOwnAnkleMonitorLockBehavior() {return GET_PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR.get();}
    @Override public String getOtherPrisonersJumpsuitLockBehavior() {return GET_OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR.get();}
    @Override public String getOtherPrisonersAnkleMonitorLockBehavior() {return GET_OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR.get();}

    @Override public String getPrisonersOwnTrackerBindingBehavior() {return GET_PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR.get();}
    @Override public String getOtherPrisonersTrackerBindingBehavior() {return GET_OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR.get();}


    @Override public int increaseReinforcedBlockStrength() {return INCREASE_REINFORCED_BLOCKS_STRENGTH.get();}

}
