package git.david.cuffedplus.items.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class JumpsuitModifierItem extends Item {


    public JumpsuitModifierItem(Properties properties) {
        super(properties);
    }

    public static String getJumpsuitModifierName(ItemStack stack) {
        return String.valueOf(stack.getItem());
    }

    // Optional: Show properties in tooltip for debugging
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        switch (getJumpsuitModifierName(stack)) {
            case "lock_modifier":
                tooltip.add(Component.literal("Jumpsuits/Ankle Monitor can be locked using the jumpsuit/ankle monitor key").withStyle(ChatFormatting.GRAY));
                break;
            case "high_visibility_modifier":
                tooltip.add(Component.literal("Highlights the wearer").withStyle(ChatFormatting.GRAY));
                break;
            case "glow_modifier":
                tooltip.add(Component.literal("Make the wearer emit light").withStyle(ChatFormatting.GRAY));
                break;
            default:
                tooltip.add(Component.literal("Unknown jumpsuit modifier").withStyle(ChatFormatting.RED));
                break;
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}

