package git.david.cuffedplus.items.item;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
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


public class JumpsuitKey extends Item {

    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    public JumpsuitKey(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (player.isCrouching()) return InteractionResultHolder.fail(itemInHand);
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        if (!(currentChest.getItem() instanceof JumpsuitItem) || !currentChest.getOrCreateTag().getBoolean("CanBeLocked")) return InteractionResultHolder.fail(itemInHand);

        if (currentChest.getOrCreateTag().getBoolean("Locked")) {


            // TODO: Make a helper function to check whether players can do a certain action instead of reusing code
            if (config.getPlayersOwnJumpsuitLockBehavior().equals("onlyLock") || config.getPlayersOwnJumpsuitLockBehavior().equals("none")) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 You can not unlock your own jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            // TODO: Make a helper function to check whether prisoners can do a certain action instead of reusing code
            if (player.getTags().contains("prisoner") && config.getPrisonersOwnJumpsuitLockBehavior().equals("onlyLock".toLowerCase()) || (config.getPrisonersOwnJumpsuitLockBehavior().equals("none"))) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 You are a prisoner!  Prisoners can not unlock their own jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            currentChest.getOrCreateTag().putBoolean("Locked", false);

        } else if (!currentChest.getOrCreateTag().getBoolean("Locked")) {

            if (config.getPlayersOwnJumpsuitLockBehavior().equals("onlyUnlock".toLowerCase()) || config.getPlayersOwnJumpsuitLockBehavior().equals("none")) {
                player.displayClientMessage(Component.literal("× You can not lock your own jumpsuit ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            if (player.getTags().contains("prisoner") && config.getPrisonersOwnJumpsuitLockBehavior().equals("onlyUnlock".toLowerCase()) || (config.getPrisonersOwnJumpsuitLockBehavior().equals("none"))) {
                player.displayClientMessage(Component.literal("× ️️You are a prisoner!  Prisoners can not lock their own jumpsuit ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            currentChest.getOrCreateTag().putBoolean("Locked", true);

        }

        return InteractionResultHolder.success(itemInHand);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);

        if (!user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;
        if (!(targetChest.getItem() instanceof JumpsuitItem) || !(targetChest.getOrCreateTag().getBoolean("CanBeLocked"))) return InteractionResult.FAIL;

        if (targetChest.getOrCreateTag().getBoolean("Locked")) {

            if (config.getOtherPlayersJumpsuitLockBehavior().equals("onlyLock") || config.getOtherPlayersJumpsuitLockBehavior().equals("none")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("🔒 You can not unlock other players jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && config.getOtherPrisonersJumpsuitLockBehavior().equals("onlyLock".toLowerCase()) || (config.getOtherPrisonersJumpsuitLockBehavior().equals("none"))) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("🔒 You are a prisoner!  Prisoners can not unlock other players jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            targetChest.getOrCreateTag().putBoolean("Locked", false);
        } else if (!targetChest.getOrCreateTag().getBoolean("Locked")) {

            if (config.getOtherPlayersJumpsuitLockBehavior().equals("onlyUnlock") || config.getOtherPlayersJumpsuitLockBehavior().equals("none")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("× You can not lock other players jumpsuit ×").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && config.getOtherPrisonersJumpsuitLockBehavior().equals("onlyUnlock".toLowerCase()) || (config.getOtherPrisonersJumpsuitLockBehavior().equals("none"))) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal(" You are a prisoner!  Prisoners can not lock other players jumpsuit ").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            targetChest.getOrCreateTag().putBoolean("Locked", true);
        }

        return InteractionResult.SUCCESS;

    }





    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.literal("Used to lock and unlock jumpsuits with the lock modifier").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}

