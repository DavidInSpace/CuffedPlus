package git.david.cuffedplus.items.item.base;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class TimeLockItem extends Item {

    public long ticks_time = 0;
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;


    public TimeLockItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand); // Player must be crouching
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);

        player.displayClientMessage(Component.literal(String.valueOf(player.getEyePosition())), false);

        return InteractionResultHolder.success(itemInHand);
    }


    // TODO: Make so the time lock gets used when putting it on a restraint and drops when the restraint gets unlocked

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (user.isCrouching()) return InteractionResult.FAIL; // Player must not be crouching
        if (user.level().isClientSide) return InteractionResult.FAIL;
        if (!(target instanceof Player)) return InteractionResult.FAIL;

        if (stack.getOrCreateTag().getLong("Time") > 0 && (this.seconds + this.minutes + this.hours < 1)) {
            this.ticks_time = stack.getOrCreateTag().getLong("Time");
            int[] time = ticksToTime(this.ticks_time);
            this.seconds = time[0];
            this.minutes = time[1];
            this.hours = time[2];
        }


        if (!(ticks_time > 0)) {
            user.displayClientMessage(Component.literal("You must first set a time with \"").append(Component.literal("/cuffed plus time_lock set [seconds] [minutes] [hours]").withStyle(ChatFormatting.BOLD)).append(Component.literal("\" before you can apply the time lock to a restraint")), false);
            return InteractionResult.FAIL;
        }

        Player targetPlayer = (Player) target;

        double maxDist = user.getEyePosition().distanceTo(target.position());
        Vec3 interactionPos = new Vec3(target.position().x, user.getLookAngle()
                .multiply(new Vec3(maxDist, maxDist, maxDist)).add(user.getEyePosition()).y,
                target.position().z);

        double interactionHeight = interactionPos.y - target.position().y;
        user.displayClientMessage(Component.literal(String.valueOf(interactionHeight)), false);

        RestrainableCapability targetCap = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(targetPlayer);
        // Head Restraint
        if (interactionHeight > 1.5f && targetCap.isRestrained(RestraintType.Head)) {
            assert targetCap.getHeadRestraint() != null;
            ItemStack restraintStack = targetCap.getHeadRestraint().saveToItemStack();

            if (restraintStack.getOrCreateTag().getBoolean("Timer")) {
                user.displayClientMessage(Component.literal("This restraint already has an active time lock with ").append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.BOLD).append(Component.literal(" remaining"))).withStyle(ChatFormatting.RED), false);
                return InteractionResult.FAIL;
            }

                restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
                restraintStack.getOrCreateTag().putBoolean("Timer", true);
                targetPlayer.displayClientMessage(Component.literal("A time lock was applied to your head restraint lasting ").withStyle(ChatFormatting.RED).append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)), false);

        }

        // Arm Restraint
        if (interactionHeight > 0.33f && interactionHeight <= 1.5f && targetCap.isRestrained(RestraintType.Arm)) {

            assert targetCap.getArmRestraint() != null;
            ItemStack restraintStack = targetCap.getArmRestraint().saveToItemStack();

            if (restraintStack.getOrCreateTag().getBoolean("Timer")) {
                user.displayClientMessage(Component.literal("This restraint already has an active time lock with ").append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.BOLD).append(Component.literal(" remaining"))).withStyle(ChatFormatting.RED), false);
                return InteractionResult.FAIL;
            }

            restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
            restraintStack.getOrCreateTag().putBoolean("Timer", true);
            targetPlayer.displayClientMessage(Component.literal("A time lock was applied to your arm restraint lasting ").withStyle(ChatFormatting.RED).append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)), false);
        }

        // Leg Restraint
        if (interactionHeight <= 0.33f && targetCap.isRestrained(RestraintType.Leg)) {

            assert targetCap.getLegRestraint() != null;
            ItemStack restraintStack = targetCap.getLegRestraint().saveToItemStack();

            if (restraintStack.getOrCreateTag().getBoolean("Timer")) {
                user.displayClientMessage(Component.literal("This restraint already has an active time lock with ").append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.BOLD).append(Component.literal(" remaining"))).withStyle(ChatFormatting.RED), false);
                return InteractionResult.FAIL;
            }
            restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
            restraintStack.getOrCreateTag().putBoolean("Timer", true);
            targetPlayer.displayClientMessage(Component.literal("A time lock was applied to your leg restraint lasting ").append(Component.literal(this.seconds + "s : " + this.minutes + "m : " + this.hours + "h : (" + this.ticks_time + " ticks)").withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.RED), false);
        }
        if (!user.getAbilities().instabuild) stack.shrink(1);
        return InteractionResult.PASS;
    }

    public static int[] ticksToTime(long ticks) {
        int total_seconds = (int) ticks / 20;
        int seconds = total_seconds % 60;
        int minutes = total_seconds / 60;
        int hours = minutes / 60;
        minutes = (minutes - (hours * 60));
        // System.out.println("SECONDS: " + (seconds - ((minutes * 60) * hours)) + "  " + seconds + "  " + ticks % 20);
        // System.out.println("MINUTES: " + (minutes - (hours * 60)) + "  " + seconds % 60);
        // System.out.println("HOUR: " + hours + "  " + hours % 60);
        return new int[] {seconds , minutes, hours};
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        int[] time = ticksToTime(stack.getOrCreateTag().getLong("Time"));
        tooltip.add(Component.literal("⌚ " + time[0] + "s : " + time[1] + "m : " + time[2] + "h (" + stack.getOrCreateTag().getLong("Time") + " ticks)").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal("Set a time using \"/cuffed plus time_lock set [seconds] [minutes] [hours]\"").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Right click on someones restraint to apply the time to it").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Shift + Right click while looking down to apply time to your leg restraint or look forward to apply to arm restraint").withStyle(ChatFormatting.GRAY));
    }
}
