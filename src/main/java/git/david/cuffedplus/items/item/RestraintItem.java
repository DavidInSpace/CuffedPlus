package git.david.cuffedplus.items.item;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;
import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import com.lazrproductions.cuffed.restraints.RestraintAPI;
import com.lazrproductions.cuffed.restraints.base.AbstractArmRestraint;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class RestraintItem extends AbstractRestraintItem {

    public RestraintItem(Properties p) {
        super(p);
    }

    public static void enableTimer(ItemStack stack, boolean value) {stack.getOrCreateTag().putBoolean("TimerEnabled", value);}
    public static boolean getTimerEnabled(ItemStack stack) {return stack.getOrCreateTag().getBoolean("TimerEnabled");}
    public static void setTime(ItemStack stack, long value) {stack.getOrCreateTag().putLong("Time", value);}
    public static long getTime(ItemStack stack) {return stack.getOrCreateTag().getLong("Time");}

    public static void setSaturationModifier(ItemStack stack, boolean value) {stack.getOrCreateTag().putBoolean("SaturationModifier", value);}
    public static void setHungerModifier(ItemStack stack, int value) {stack.getOrCreateTag().putInt("HungerModifier", value);}
    public static void setAntiGodModifier(ItemStack stack, int value) {stack.getOrCreateTag().putInt("AntiGodModifier", value);}
    public static void setJumpModifier(ItemStack stack, boolean value) {stack.getOrCreateTag().putBoolean("JumpModifier", value);}
    public static void setCanBeBrokenOutOf(ItemStack stack, boolean value) {stack.getOrCreateTag().putBoolean("CanBeBrokenOutOf", value);}
    public static void setLockpickable(ItemStack stack, boolean value) {stack.getOrCreateTag().putBoolean("Lockpickable", value);}

    public static boolean getSaturationModifier(ItemStack stack) {return stack.getOrCreateTag().getBoolean("SaturationModifier");}
    public static int getHungerModifier(ItemStack stack) {return stack.getOrCreateTag().getInt("HungerModifier");}
    public static int getAntiGodModifier(ItemStack stack) {return stack.getOrCreateTag().getInt("AntiGodModifier");}
    public static boolean getJumpModifier(ItemStack stack) {return stack.getOrCreateTag().getBoolean("JumpModifier");}
    public static boolean canBeBrokenOutOf(ItemStack stack) {return stack.getOrCreateTag().getBoolean("CanBeBrokenOutOf");}
    public static boolean isLockpickable(ItemStack stack) {return stack.getOrCreateTag().getBoolean("isLockpickable");}




    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.literal("Arms Restraint").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Leg Restraint").withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.literal("").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Modifiers:").withStyle(ChatFormatting.GRAY));

        if (getSaturationModifier(stack)) {
            tooltip.add(Component.literal("Saturation Modifier").withStyle(ChatFormatting.YELLOW));
        } else {
            tooltip.remove(Component.literal("Breaking Blocks Disabled").withStyle(ChatFormatting.YELLOW));
        }

        if (getHungerModifier(stack) > 0) {
            tooltip.add(Component.literal("Hunger Modifier " + getHungerModifier(stack)).withStyle(ChatFormatting.DARK_GREEN));
        } else {
            tooltip.remove(Component.literal("Hunger Modifier").withStyle(ChatFormatting.DARK_GREEN));
        }

        if (getAntiGodModifier(stack) > 0) {
            tooltip.add(Component.literal("Anti-God Modifier").withStyle(ChatFormatting.GOLD));
        } else {
            tooltip.remove(Component.literal("Anti-God Modifier " + getAntiGodModifier(stack)).withStyle(ChatFormatting.GOLD));
        }

        if (getJumpModifier(stack)) {
            tooltip.add(Component.literal("Jumping Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Jumping Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (canBeBrokenOutOf(stack)) {
            tooltip.add(Component.literal("Unbreakable").withStyle(ChatFormatting.BLUE));
        } else {
            tooltip.remove(Component.literal("Unbreakable").withStyle(ChatFormatting.BLUE));
        }

        if (isLockpickable(stack)) {
            tooltip.add(Component.literal("Lockpicking Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Lockpicking Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (getTimerEnabled(stack)) {
            long time = getTime(stack);
            tooltip.add(Component.literal(String.valueOf(getTime(stack))).withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }


}
