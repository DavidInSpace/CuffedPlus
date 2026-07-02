package git.david.cuffedplus.mixin;

import com.lazrproductions.cuffed.config.CuffedServerConfig;
import com.lazrproductions.lazrslib.common.config.ConfigCategory;
import com.lazrproductions.lazrslib.common.config.ConfigProperty;
import com.lazrproductions.lazrslib.common.config.LazrConfig;
import net.minecraftforge.fml.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CuffedServerConfig.class)
public abstract class CuffedServerConfigMixin extends LazrConfig {

    public CuffedServerConfigMixin(String name, ModConfig.Type type) {
        super(name, type);
    }


    @Unique
    public ConfigCategory CUFFED_PLUS_SETTINGS;
    @Unique
    public ConfigProperty<Boolean> CAN_PRISONERS_TAKE_OFF_JUMPSUITS;
    @Unique
    public ConfigProperty<Boolean> CAN_PRISONERS_PUT_ON_JUMPSUITS;
    @Unique
    public ConfigProperty<Boolean> CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS;
    @Unique
    public ConfigProperty<Boolean> CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS;

    @Inject(method = "registerProperties", at = @At("HEAD"), remap = false)
    public void addRegisterProperties(CallbackInfo ci) {
        CUFFED_PLUS_SETTINGS = createCategory(new ConfigCategory(this, "Cuffed Plus Settings"), (c) -> {
            CAN_PRISONERS_TAKE_OFF_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Off jumpsuits", "Whether or not to require players to be restrained to get anchored.", false));
            CAN_PRISONERS_PUT_ON_JUMPSUITS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put On Jumpsuits", "The maximum length of the chain when anchoring.", false));
            CAN_PRISONERS_TAKE_JUMPSUITS_OFF_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Take Jumpsuits Off Others", "The distance when anchored entites start suffocating.", false));
            CAN_PRISONERS_PUT_JUMPSUITS_ON_OTHERS = c.putProperty(new ConfigProperty<Boolean>(this, "Can Prisoners Put Jumpsuits On Others", "Whether or not players should be allowed to anchor entities to FENCES.", true));
        });
    }
}
