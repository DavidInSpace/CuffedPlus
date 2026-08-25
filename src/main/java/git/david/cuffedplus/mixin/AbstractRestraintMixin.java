package git.david.cuffedplus.mixin;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;
import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;
import git.david.cuffedplus.init.ModItems;
import git.david.cuffedplus.items.item.base.RestraintItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractRestraint.class)
public class AbstractRestraintMixin {

    @Inject(method = "onTickServer", at = @At("TAIL"))
    public void onTickServer(ServerPlayer player, CallbackInfo ci) {
        public void tick (ServerPlayer player, ItemStack restraintStack){
            ticks_time--;
            int[] time = RestraintItem.ticksToTime(ticks_time);
            int seconds = time[0];
            int minutes = time[1];
            int hours = time[2];
            player.displayClientMessage(Component.literal("🔒 " + seconds + "s : " + minutes + "m : " + hours + "h 🔒").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            restraintStack.getOrCreateTag().putLong("Time", ticks_time);

            //if (!config.allowBreakingTimeLockedRestraints()) this.setDurability(player, getMaxDurability());

            if (ticks_time - ticks <= -20 || ticks == 0) {
                ticks = ticks_time;
            }

            if (ticks_time < 1) {
                time_locked = false;
                player.displayClientMessage(Component.literal("🔓 Time ran out 🔓").withStyle(ChatFormatting.GREEN), true);
                ticks_time = -1;
                restraintStack.getOrCreateTag().putLong("Time", ticks_time);
                restraintStack.getOrCreateTag().putBoolean("Timer", false);
                RestrainableCapability playerRestrainableCapability = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(player);
                playerRestrainableCapability.UnequipRestraint(player, player, this.getType());
                if (restraintStack.getOrCreateTag().getBoolean("DropTimeLock")) {
                    ItemStack timeLockStack = new ItemStack(ModItems.TIME_LOCK.get());
                    ItemEntity itemEntity = new ItemEntity(player.level(), player.getX(), player.getY() + 0.6D, player.getZ(), timeLockStack);
                    itemEntity.setDefaultPickUpDelay();
                    player.level().addFreshEntity(itemEntity);
                }
                if (restraintStack.getOrCreateTag().getInt("AntiGodModifier") > 0 && config.putPlayersInToCreativeWhenAntiGodRestraintTimeLockRunsOut()) {
                    player.setGameMode(GameType.CREATIVE);
                }
            }
        }
    }
}
