package git.david.cuffedplus.mixin;

import com.lazrproductions.cuffed.CuffedMod;
import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;

import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.init.ModItems;

import git.david.cuffedplus.items.item.base.RestraintItem;
import git.david.cuffedplus.items.restraints.base.IAbstractRestraintAccessor;
import git.david.cuffedplus.utils.InfoMessagesHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractRestraint.class)
public abstract class AbstractRestraintMixin implements IAbstractRestraintAccessor {
    @Unique ICuffedPlusServerConfigMixin cuffedplus$config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;
    boolean cuffedplus$time_locked = false;


    // TODO: Rework all the names, variables, logic etc. because right now everything looks just miserable
    // BUT AS LONG AS IT WORKS!!!!!! I WILL JUST KEEP IT LIKE THAT FOR A WHILE >:)
    long cuffedplus$ticks_time = 0;
    long cuffedplus$ticks = 0;
    int cuffedplus$player_tick_count = 0;
    boolean cuffedplus$drop_time_lock = false;

    @Shadow public abstract RestraintType getType();

    @Override public boolean isTimeLocked() {return cuffedplus$time_locked;}
    @Override public void setTimeLocked(boolean time_locked) {this.cuffedplus$time_locked = time_locked;}

    @Override public long getTicksTime() {return this.cuffedplus$ticks_time;}
    @Override public void setTicksTime(long amount) {this.cuffedplus$ticks_time = amount;}

    @Override public long getTicks() {return this.cuffedplus$ticks;}
    @Override public void setTicks(long amount) {this.cuffedplus$ticks = amount;}

    @Override public int getPlayerTickCount() {return this.cuffedplus$player_tick_count;}
    @Override public void setPlayerTickCount(int amount) {this.cuffedplus$player_tick_count = amount;}

    @Override public boolean getDropTimeLock() {return this.cuffedplus$drop_time_lock;}
    @Override public void setDropTimeLock(boolean drop) {this.cuffedplus$drop_time_lock = drop;}


    @Unique public void cuffedplus$tick(ServerPlayer player, ItemStack restraintStack) {
        //System.out.println("TICKING: " + getTicksTime());
        setTicksTime(getTicksTime() - 1);
        int[] time = RestraintItem.ticksToTime(getTicksTime());
        int seconds = time[0];
        int minutes = time[1];
        int hours = time[2];
        player.displayClientMessage(Component.literal("🔒 " + seconds + "s : " + minutes + "m : " + hours + "h 🔒").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
        restraintStack.getOrCreateTag().putLong("Time", getTicksTime());

        if (getTicksTime() - getTicks() <= -20 || getTicks() == 0) {
            setTicks(getTicksTime());
        }

        if (getTicksTime() < 1) {
            setTimeLocked(false);
            player.displayClientMessage(Component.literal("🔓 Time ran out 🔓").withStyle(ChatFormatting.GREEN), true);
            setTicksTime(-1);
            restraintStack.getOrCreateTag().putLong("Time", getTicksTime());
            restraintStack.getOrCreateTag().putBoolean("Timer", false);
            RestrainableCapability playerRestrainableCapability = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(player);
            playerRestrainableCapability.UnequipRestraint(player, player, getType());
            if (getDropTimeLock()) {
                ItemStack timeLockStack = new ItemStack(ModItems.TIME_LOCK.get());
                ItemEntity itemEntity = new ItemEntity(player.level(), player.getX(), player.getY() + 0.6D, player.getZ(), timeLockStack);
                itemEntity.setDefaultPickUpDelay();
                player.level().addFreshEntity(itemEntity);
            }
            if (restraintStack.getOrCreateTag().getInt("AntiGodModifier") > 0 && cuffedplus$config.putPlayersInToCreativeWhenAntiGodRestraintTimeLockRunsOut()) {
                player.setGameMode(GameType.CREATIVE);
            }
        }

    }

    @Inject(method = "onEquippedServer", at = @At("TAIL"), remap = false)
    public void onEquippedServer(ServerPlayer player, ServerPlayer captor, CallbackInfo ci) {
        RestrainableCapability playerCap = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(player);
        assert playerCap.getArmRestraint() != null;
        ItemStack restraintStack = playerCap.getArmRestraint().saveToItemStack();
        if (restraintStack.getOrCreateTag().getInt("AntiGodModifier") == 1) {
            InfoMessagesHandler.sendInfoMessage(player, " ! You have been reduced to a normal person !", false, true);
            player.setGameMode(GameType.SURVIVAL);
        } else if (restraintStack.getOrCreateTag().getInt("AntiGodModifier") == 2) {
            InfoMessagesHandler.sendInfoMessage(player, " ! You have been reduced to a normal person without a way back !", false, true);
        }
    }

    @Inject(method = "onTickServer", at = @At("TAIL"), remap = false)
    public void onTickServer(ServerPlayer player, CallbackInfo ci) {
        RestrainableCapability playerCap = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(player);
        assert playerCap.getArmRestraint() != null;
        ItemStack restraintStack = playerCap.getArmRestraint().saveToItemStack();
        setTicksTime(restraintStack.getOrCreateTag().getLong("Time"));
        //System.out.println("TIME: " + cuffedplus$ticks_time + "  " + restraintStack.getOrCreateTag().getLong("Time") + "  " + restraintStack.getOrCreateTag().getBoolean("Timer"));
        if (restraintStack.getOrCreateTag().getBoolean("Timer") && getTicksTime() > 0) {
            setTimeLocked(true);
            setDropTimeLock(restraintStack.getOrCreateTag().getBoolean("DropTimeLock"));
            cuffedplus$tick(player, restraintStack);
        }

        if (restraintStack.getOrCreateTag().getBoolean("Timer") && getTicksTime() < 1) {
            restraintStack.getOrCreateTag().putBoolean("Timer", false);
        } else if (!restraintStack.getOrCreateTag().getBoolean("Timer") && getTicksTime() > 0) {
            restraintStack.getOrCreateTag().putBoolean("Timer", true);
        }

        if (restraintStack.getOrCreateTag().getBoolean("SaturationModifier")) {
            if (player.getFoodData().needsFood()) {
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 10, false, false));
            }
        } else if (restraintStack.getOrCreateTag().getInt("HungerModifier") > 0) {
            if (player.tickCount - getPlayerTickCount() >= 200 / restraintStack.getOrCreateTag().getInt("HungerModifier") && player.getFoodData().getFoodLevel() > 3) {
                player.causeFoodExhaustion(1);
                setPlayerTickCount(player.tickCount);
                this.getType();
            }
        } else if (restraintStack.getOrCreateTag().getInt("AntiGodModifier") == 2 && (isTimeLocked() && getTicksTime() > 5)) {
            player.setGameMode(GameType.SURVIVAL);
        }
    }

}
