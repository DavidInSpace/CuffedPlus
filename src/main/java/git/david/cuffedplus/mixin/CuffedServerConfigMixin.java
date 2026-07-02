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

@Mixin(CuffedServerConfig.class)
public abstract class CuffedServerConfigMixin extends LazrConfig implements ICuffedPlusServerConfigMixin {

    ConfigCategory CUFFED_PLUS_SETTINGS;


    ConfigProperty<Boolean> SHOW_ROLE_PREFIX;
    ConfigProperty<Boolean> ROLE_PREFIX_BOLD;

    ConfigProperty<String> PRISONER_ROLE_PREFIX;
    ConfigProperty<String> PRISONER_ROLE_PREFIX_COLOR;

    ConfigProperty<String> POLICE_ROLE_PREFIX;
    ConfigProperty<String> POLICE_ROLE_PREFIX_COLOR;



    ConfigProperty<Boolean> CAN_PRISONERS_TAKE_OFF_ANKLE_MONITORS;
    ConfigProperty<Boolean> CAN_PRISONERS_PUT_ON_ANKLE_MONITORS;
    ConfigProperty<Boolean> CAN_PRISONERS_TAKE_ANKLE_MONITORS_OFF_OTHERS;
    ConfigProperty<Boolean> CAN_PRISONERS_PUT_ANKLE_MONITORS_ON_OTHERS;

    ConfigProperty<Boolean> CAN_PRISONERS_TAKE_OFF_JUMPSUITS;
    ConfigProperty<Boolean> CAN_PRISONERS_PUT_ON_JUMPSUITS;
    ConfigProperty<Boolean> CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS;
    ConfigProperty<Boolean> CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS;


    public CuffedServerConfigMixin(String name, ModConfig.Type type) {
        super(name, type);
    }

    @Inject(method = "registerProperties", at = @At("HEAD"), remap = false)
    public void addRegisterProperties(CallbackInfo ci) {
        CUFFED_PLUS_SETTINGS = createCategory(new ConfigCategory(this, "Cuffed Plus Settings"), (c) -> {
            SHOW_ROLE_PREFIX = c.putProperty(new ConfigProperty<Boolean>(this, "Show Role Prefixes", "Whether to show role prefixes like [INMATE] or [OFFICER].", true));
            ROLE_PREFIX_BOLD = c.putProperty(new ConfigProperty<Boolean>(this, "Make Role Prefixes Bold", "Whether to make role prefixes like [INMATE] or [OFFICER] bold.", true));

            PRISONER_ROLE_PREFIX = c.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix", "How will the prisoner role prefix look like.", "[INMATE]"));
            PRISONER_ROLE_PREFIX_COLOR = c.putProperty(new ConfigProperty<String>(this, "Prisoner Role Prefix Color", "What color will the prisoner role prefix have (eg. GOLD, GREEN, DARK_GREEN, AQUA, RED, BLUE, WHITE)", "GOLD"));

            POLICE_ROLE_PREFIX = c.putProperty(new ConfigProperty<String>(this, "Police Role Prefix", "How will the police role prefix look like.", "[OFFICER]"));
            POLICE_ROLE_PREFIX_COLOR = c.putProperty(new ConfigProperty<String>(this, "Police Role Prefix", "What color will the prisoner role prefix have (eg. GOLD, GREEN, DARK_GREEN, AQUA, RED, BLUE, WHITE)", "BLUE"));


            CAN_PRISONERS_TAKE_OFF_ANKLE_MONITORS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Off Ankle Monitors", "Whether prisoners can take off ankle monitors by themselves.", false));
            CAN_PRISONERS_PUT_ON_ANKLE_MONITORS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put On Ankle Monitors", "Whether prisoners put on ankle monitors by themselves.", false));
            CAN_PRISONERS_TAKE_ANKLE_MONITORS_OFF_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Ankle Monitors Off Others", "Whether prisoners can take ankle monitors off other players", false));
            CAN_PRISONERS_PUT_ANKLE_MONITORS_ON_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put Ankle Monitors On Others", "Whether prisoners can put ankle monitors on other players.", true));

            CAN_PRISONERS_TAKE_OFF_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Off jumpsuits", "Whether prisoners can take off jumpsuits by themselves.", false));
            CAN_PRISONERS_PUT_ON_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put On Jumpsuits", "Whether prisoners put on jumpsuits by themselves.", false));
            CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Jumpsuits Off Others", "Whether prisoners can take jumpsuits off other players", false));
            CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put Jumpsuits On Others", "Whether prisoners can put jumpsuits on other players.", true));
        });
    }

    @Override
    public boolean showRolePrefixes() {
        return SHOW_ROLE_PREFIX.get();
    }

    @Override
    public boolean rolePrefixesBold() {
        return ROLE_PREFIX_BOLD.get();
    }

    @Override
    public String getPrisonerRolePrefix() {
        return PRISONER_ROLE_PREFIX.get();
    }

    @Override
    public String getPrisonerRolePrefixColor() {
        return PRISONER_ROLE_PREFIX_COLOR.get();
    }

    @Override
    public String getOfficerRolePrefix() {
        return POLICE_ROLE_PREFIX.get();
    }

    @Override
    public String getOfficerRolePrefixColor() {
        return POLICE_ROLE_PREFIX_COLOR.get();
    }

    @Override
    public boolean canPrisonersTakeOffAnkleMonitors() {
        return CAN_PRISONERS_TAKE_OFF_ANKLE_MONITORS.get();
    }

    @Override
    public boolean canPrisonersPutOnAnkleMonitors() {
        return CAN_PRISONERS_PUT_ON_ANKLE_MONITORS.get();
    }

    @Override
    public boolean canPrisonersTakeAnkleMonitorsOffOthers() {
        return CAN_PRISONERS_TAKE_ANKLE_MONITORS_OFF_OTHERS.get();
    }

    @Override
    public boolean canPrisonersPutAnkleMonitorsOnOthers() {
        return CAN_PRISONERS_PUT_ANKLE_MONITORS_ON_OTHERS.get();
    }

    @Override
    public boolean canPrisonersTakeOffJumpsuits() {
        return CAN_PRISONERS_TAKE_OFF_JUMPSUITS.get();
    }

    @Override
    public boolean canPrisonersPutOnJumpsuits() {
        return CAN_PRISONERS_PUT_ON_JUMPSUITS.get();
    }

    @Override
    public boolean canPrisonersTakeJumpsuitsOffOthers() {
        return CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS.get();
    }

    @Override
    public boolean canPrisonersPutJumpsuitsOnOthers() {
        return CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS.get();
    }
}
