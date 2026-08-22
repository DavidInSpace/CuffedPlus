package git.david.cuffedplus.items.item.base;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;
import com.lazrproductions.cuffed.restraints.RestraintAPI;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
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

    long ticks_time = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;


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



    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (user.isCrouching()) return InteractionResult.FAIL; // Player must not be crouching
        if (user.level().isClientSide) return InteractionResult.FAIL;
        if (!(target instanceof Player)) return InteractionResult.FAIL;

      /*  Player targetPlayer = (Player) target;


        double maxDist = user.getEyePosition().distanceTo(target.position());
        Vec3 interactionPos = new Vec3(target.position().x, user.getLookAngle()
                .multiply(new Vec3(maxDist, maxDist, maxDist)).add(user.getEyePosition()).y,
                target.position().z);

        double interactionHeight = interactionPos.y - target.position().y;

        RestrainableCapability targetCap = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(targetPlayer);
        if (interactionHeight > 1.5f && targetCap.isRestrained(RestraintType.Head)) {
            // Head Restraint
            assert targetCap.getHeadRestraint() != null;
            ItemStack restraintStack = targetCap.getHeadRestraint().saveToItemStack();
            restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
        }

        if (interactionHeight > 0.33f && interactionHeight <= 1.5f && targetCap.isRestrained(RestraintType.Arm)) {
            // Arm Restraint
            assert targetCap.getArmRestraint() != null;
            ItemStack restraintStack = targetCap.getArmRestraint().saveToItemStack();
            restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
        }

        if (interactionHeight <= 0.33f && targetCap.isRestrained(RestraintType.Leg)) {
            // Leg Restraint
            assert targetCap.getLegRestraint() != null;
            ItemStack restraintStack = targetCap.getLegRestraint().saveToItemStack();
            restraintStack.getOrCreateTag().putLong("Time", this.ticks_time);
        }
*/
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Restraint Modifier").withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.literal("Set a time using \"/cuffed plus time_lock set [seconds] [minutes] [hours]\"").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Right click on someones restraint to apply the time to it").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Shift + Right click while looking down to apply time to your leg restraint or look forward to apply to arm restraint").withStyle(ChatFormatting.GRAY));
    }
}
