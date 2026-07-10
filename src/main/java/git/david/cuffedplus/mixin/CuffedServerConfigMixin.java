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

    ConfigProperty<String[]> PRISONER_ROLE_PREFIX;
    ConfigProperty<String[]> POLICE_ROLE_PREFIX;


    ConfigProperty<String[]> GET_PLAYERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String[]> GET_PLAYERS_ANKLE_MONITOR_BEHAVIOR;

    ConfigProperty<String[]> GET_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String[]> GET_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR;


    ConfigProperty<String[]> GET_PRISONERS_JUMPSUIT_BEHAVIOR;
    ConfigProperty<String[]> GET_PRISONERS_ANKLE_MONITOR_BEHAVIOR;

    ConfigProperty<String[]> GET_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR;
    ConfigProperty<String[]> GET_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR;


    ConfigProperty<String[]> INCREASE_REINFORCED_BLOCKS_STRENGTH;


    public CuffedServerConfigMixin(String name, ModConfig.Type type) {
        super(name, type);
    }

    @Inject(method = "registerProperties", at = @At("HEAD"), remap = false)
    public void addRegisterProperties(CallbackInfo ci) {
        CUFFED_PLUS_SETTINGS = createCategory(new ConfigCategory(this, "Cuffed Plus Settings"), (c1) -> {

            PREFIX_SETTINGS = createCategory(new ConfigCategory(this, "Prefix Settings"), (c2) -> {
                SHOW_ROLE_PREFIX = c2.putProperty(new ConfigProperty<Boolean>(this, "Show Role Prefixes", "Whether to show role prefixes like [INMATE] or [OFFICER].", true));
                ROLE_PREFIX_BOLD = c2.putProperty(new ConfigProperty<Boolean>(this, "Make Role Prefixes Bold", "Whether to make role prefixes like [INMATE] or [OFFICER] bold.", true));

                PRISONER_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String[]>(this, "Prisoner Role Prefix", "How will the prisoner role prefix look like.", new String[]{"[INMATE]", "#ff234D"}));
                POLICE_ROLE_PREFIX = c2.putProperty(new ConfigProperty<String[]>(this, "Police Role Prefix", "How will the police role prefix look like.", new String[]{"[OFFICER]", "#ff234D"}));
            });

            PLAYERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS  = createCategory(new ConfigCategory(this, "Players Jumpsuit & Ankle Monitor Behavior"), (c3) -> {});
            PRISONERS_JUMPSUIT_AND_ANKLE_MONITOR_BEHAVIOR_SETTINGS = createCategory(new ConfigCategory(this, "Prisoners Jumpsuit & Ankle Monitor Behavior"), (c4) -> {});
            BLOCKS_SETTINGS = createCategory(new ConfigCategory(this, "Blocks Settings"), (c5) -> {});




            CAN_PLAYERS_UNLOCK_OWN_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Players Unlock Their Own Jumpsuit", "Whether players will be able to lock jumpsuits with the lock modifier on themselves using a key", true));
            CAN_PLAYERS_LOCK_OWN_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Players Lock Their Own Jumpsuit", "Whether players will be able to unlock jumpsuits with the lock modifier on themselves using a key", true));

            CAN_PLAYERS_UNLOCK_OWN_ANKLE_MONITORS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Players Unlock Their Own Ankle Monitor", "Whether players will be able to unlock ankle monitors with the lock modifier on themselves using a key", true));
            CAN_PLAYERS_LOCK_OWN_ANKLE_MONITORS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Players Lock Their Own Ankle Monitor", "Whether players will be able to lock ankle monitors with the lock modifier on themselves using a key", true));


            CAN_PRISONERS_TAKE_ANKLE_MONITORS_OFF_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Ankle Monitors Off Others", "Whether prisoners can take ankle monitors off other players", false));
            CAN_PRISONERS_PUT_ANKLE_MONITORS_ON_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put Ankle Monitors On Others", "Whether prisoners can put ankle monitors on other players.", false));

            CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Jumpsuits Off Others", "Whether prisoners can take jumpsuits off other players", false));
            CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put Jumpsuits On Others", "Whether prisoners can put jumpsuits on other players.", false));

        });

    }




    @Override public boolean showRolePrefixes() {return SHOW_ROLE_PREFIX.get();}
    @Override public boolean rolePrefixesBold() {return ROLE_PREFIX_BOLD.get();}


    @Override public boolean canPlayersUnlockOwnJumpsuits() {return CAN_PLAYERS_UNLOCK_OWN_JUMPSUITS.get();}
    @Override public boolean canPlayersLockOwnJumpsuits() {return CAN_PLAYERS_LOCK_OWN_JUMPSUITS.get();}

    @Override public boolean canPlayersUnlockOwnAnkleMonitors() {return  CAN_PLAYERS_UNLOCK_OWN_ANKLE_MONITORS.get();}
    @Override public boolean canPlayersLockOwnAnkleMonitors() {return  CAN_PLAYERS_LOCK_OWN_ANKLE_MONITORS.get();}


    @Override public String getPrisonerRolePrefix() {return PRISONER_ROLE_PREFIX.get();}
    @Override public String getPrisonerRolePrefixColor() {return PRISONER_ROLE_PREFIX_COLOR.get();}

    @Override public String getOfficerRolePrefix() {return POLICE_ROLE_PREFIX.get();}
    @Override public String getOfficerRolePrefixColor() {return POLICE_ROLE_PREFIX_COLOR.get();}


    @Override public boolean canPrisonersTakeAnkleMonitorsOffOthers() {return CAN_PRISONERS_TAKE_ANKLE_MONITORS_OFF_OTHERS.get();}
    @Override public boolean canPrisonersPutAnkleMonitorsOnOthers() {return CAN_PRISONERS_PUT_ANKLE_MONITORS_ON_OTHERS.get();}

    @Override public boolean canPrisonersTakeJumpsuitsOffOthers() {return CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS.get();}
    @Override public boolean canPrisonersPutJumpsuitsOnOthers() {return CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS.get();}
}
