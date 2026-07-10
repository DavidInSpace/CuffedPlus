package git.david.cuffedplus.mixin;


import com.lazrproductions.cuffed.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModBlocks.class)
public class CuffedBlockInitMixin {


    @ModifyArg(
            method = "lambda$static$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;strength(FF)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"
            ),
            index = 0
    )
    private static float modifyHardness(float original) {
        return 5000.0F;
    }

    @ModifyArg(
            method = "lambda$static$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;strength(FF)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"
            ),
            index = 1
    )
    private static float modifyResistance(float original) {
        return 12000.0F;
    }
}
