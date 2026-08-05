package git.david.cuffedplus.items.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class ModifierItem extends Item {


    public ModifierItem(Properties properties) {
        super(properties);
    }

    public static String getModifierName(ItemStack stack) {
        return String.valueOf(stack.getItem());
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        switch (getModifierName(stack)) {
            case "saturation_modifier":
                tooltip.add(Component.literal("Restraint Modifier").withStyle(ChatFormatting.DARK_GRAY));
                tooltip.add(Component.literal("Players wearing a restraint with this modifier will never starve").withStyle(ChatFormatting.GRAY));
                break;
            case "hunger_modifier":
                tooltip.add(Component.literal("Restraint Modifier").withStyle(ChatFormatting.DARK_GRAY));
                tooltip.add(Component.literal("Players wearing a restraint with this modifier will loose hunger much faster").withStyle(ChatFormatting.GRAY));
                break;
           /* case "protection_movement":
                tooltip.add(Component.literal("Restraint Modifier").withStyle(ChatFormatting.DARK_GRAY));
                tooltip.add(Component.literal("Players wearing a restraint with this modifier will tale less damage and regenerate much faster").withStyle(ChatFormatting.GRAY));
                break;*/
            case "jump_modifier":
                tooltip.add(Component.literal("Restricts jumping").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.literal("only works if applied to hands").withStyle(ChatFormatting.DARK_GRAY));
                break;
            case "can_be_broken_out_of":
                tooltip.add(Component.literal("Enables/Disables the ability to break out of the restraint").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.literal("works on hands and legs").withStyle(ChatFormatting.DARK_GRAY));
                break;
            case "is_lockpickable":
                tooltip.add(Component.literal("Enables/Disables the ability to lockpick the restraint").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.literal("works on hands and legs").withStyle(ChatFormatting.DARK_GRAY));
                break;
            default:
                tooltip.add(Component.literal("Unknown modifier").withStyle(ChatFormatting.RED));
                break;
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}

