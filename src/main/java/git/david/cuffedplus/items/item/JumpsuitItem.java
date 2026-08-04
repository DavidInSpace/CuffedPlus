package git.david.cuffedplus.items.item;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class JumpsuitItem extends Item {

    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    public JumpsuitItem(Properties Item) {
        super(Item);
    }


    /**
     * public static void setNumber(ItemStack stack, byte number) {
     * Item item = stack.getItem();
     * if (!(item instanceof JumpsuitItem)) {return;}
     * stack.getOrCreateTag().putByte("number", number);
     * }
     * <p>
     * public static byte getNumber(ItemStack stack) {
     * Item item = stack.getItem();
     * return stack.getOrCreateTag().getByte("number");
     * }
     */

    public static void setNumber(ItemStack stack, byte number) {
        stack.getOrCreateTag().putByte("JumpsuitNumber", number);
    }

    public static byte getNumber(ItemStack stack) {
        return stack.getOrCreateTag().getByte("JumpsuitNumber");
    }

    public static void setCanBeLocked(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("CanBeLocked", value);
    }

    public static void canBeLocked(ItemStack stack) {
        stack.getOrCreateTag().getBoolean("CanBeLocked");
    }

    public static void setLocked(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("Locked", value);
    }

    public static boolean getLocked(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("Locked");
    }

    public static void setHighVisibility(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("HighVisibility", value);
    }

    public static boolean getHighVisibility(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("HighVisibility");
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return false;
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand); // Player must be crouching
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        //if (itemInHand.getItem() instanceof ArmorItem && ((ArmorItem) itemInHand.getItem()).getType() == ArmorItem.Type.CHESTPLATE && currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) return InteractionResultHolder.fail(itemInHand);

        ItemStack jumpsuit = itemInHand.copy();
        jumpsuit.setCount(1);

        if (!(currentChest.getItem() instanceof JumpsuitItem)) {
            player.setItemSlot(EquipmentSlot.CHEST, jumpsuit);
            if (!player.getAbilities().instabuild) itemInHand.shrink(1);
        }

        return InteractionResultHolder.success(itemInHand);
    }




    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);

        if (user.isCrouching()) return InteractionResult.FAIL; // Player must not be crouching
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;

        if (!target.hasItemInSlot(EquipmentSlot.CHEST)) {

            if (config.getOtherPlayersJumpsuitBehavior().equals("onlyTakeOff".toLowerCase()) || (config.getOtherPlayersJumpsuitBehavior().equals("none"))) {
                user.displayClientMessage(Component.literal("× You can't put jumpsuits on others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && config.getOtherPrisonersJumpsuitBehavior().equals("onlyTakeOff".toLowerCase()) || config.getOtherPrisonersJumpsuitBehavior().equals("none")) {
                user.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't put jumpsuits on others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.FAIL;
            }

            ItemStack jumpsuit = stack.copy();
            jumpsuit.setCount(1);
            target.setItemSlot(EquipmentSlot.CHEST, jumpsuit);

            if (!user.getAbilities().instabuild) stack.shrink(1);

        } else if (targetChest.getItem() instanceof JumpsuitItem) {

            user.displayClientMessage(Component.literal("Trying to take off"), false);
            if (targetChest.getOrCreateTag().getBoolean("CanBeLocked") && targetChest.getOrCreateTag().getBoolean("Locked")) {
                user.displayClientMessage(Component.literal("🔒 " + target.getDisplayName() + "'s jumpsuit is locked on him! 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            ItemStack suit = target.getItemBySlot(EquipmentSlot.CHEST).copyAndClear();

            suit.setCount(1);
            boolean added = user.getInventory().add(suit);
            target.getItemBySlot(EquipmentSlot.CHEST).setCount(0);

            if (!added) user.drop(targetChest, false);

            ItemStack jumpsuit = stack.copy();

            user.getItemInHand(hand).shrink(1);
            if (!user.getAbilities().instabuild) stack.shrink(1);
            target.setItemSlot(EquipmentSlot.CHEST, jumpsuit);


        }
        return InteractionResult.SUCCESS;
    }



    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        //byte number = getNumber(stack);

        if (stack.getOrCreateTag().getBoolean("CanBeLocked") && stack.getOrCreateTag().getBoolean("Locked")) {
            tooltip.add(Component.literal("Locked").withStyle(ChatFormatting.RED));
        } else if (stack.getOrCreateTag().getBoolean("CanBeLocked") && !stack.getOrCreateTag().getBoolean("Locked")) {
            tooltip.add(Component.literal("Unlocked").withStyle(ChatFormatting.GREEN));
        }

        //tooltip.add(Component.literal("Number: " + number).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
