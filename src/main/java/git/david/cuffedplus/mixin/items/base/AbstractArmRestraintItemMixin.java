package git.david.cuffedplus.mixin.items.base;

import com.lazrproductions.cuffed.items.base.AbstractArmRestraintItem;
import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

import static git.david.cuffedplus.items.item.base.RestraintItem.*;


public class AbstractArmRestraintItemMixin extends AbstractRestraintItem {

    public AbstractArmRestraintItemMixin(Properties p) {
        super(p);
    }
    /*
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    public void appendHoverText(ItemStack stack, Level level, java.util.List<net.minecraft.network.chat.Component> components, TooltipFlag tooltipFlag, CallbackInfo ci) {


    }
     */
}
