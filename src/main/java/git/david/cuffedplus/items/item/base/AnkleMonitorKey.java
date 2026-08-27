package git.david.cuffedplus.items.item.base;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModStatistics;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class AnkleMonitorKey extends Item {


    public AnkleMonitorKey(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentFeet = player.getItemBySlot(EquipmentSlot.FEET);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand);
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        if (!(currentFeet.getItem() instanceof AnkleMonitorItem) || !currentFeet.getOrCreateTag().getBoolean("CanBeLocked")) return InteractionResultHolder.fail(itemInHand);

        if (currentFeet.getOrCreateTag().getBoolean("Locked")) {


            // TODO: Make a helper function to check whether players can do a certain action instead of reusing code
            if (CuffedPlusMain.SERVER_CONFIG.getPlayersOwnAnkleMonitorLockBehavior().equals("onlyLock") || CuffedPlusMain.SERVER_CONFIG.getPlayersOwnAnkleMonitorLockBehavior().equals("none")) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 You can not unlock your own ankle monitor 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            // TODO: Make a helper function to check whether prisoners can do a certain action instead of reusing code
            if (player.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnAnkleMonitorLockBehavior().equals("onlyLock".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnAnkleMonitorLockBehavior().equals("none"))) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 You are a prisoner!  Prisoners can not unlock their own ankle monitor 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }


            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            currentFeet.getOrCreateTag().putBoolean("Locked", false);
            ModStatistics.awardGearUnlocked((ServerPlayer) player, itemInHand.getItem());
        } else if (!currentFeet.getOrCreateTag().getBoolean("Locked")) {

            if (CuffedPlusMain.SERVER_CONFIG.getPlayersOwnAnkleMonitorLockBehavior().equals("onlyUnlock".toLowerCase()) || CuffedPlusMain.SERVER_CONFIG.getPlayersOwnAnkleMonitorLockBehavior().equals("none")) {
                player.displayClientMessage(Component.literal("× You can not lock your own ankle monitor ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            if (player.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnAnkleMonitorLockBehavior().equals("onlyUnlock".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getPrisonersOwnAnkleMonitorLockBehavior().equals("none"))) {
                player.displayClientMessage(Component.literal("× ️️You are a prisoner!  Prisoners can not lock their own ankle monitor ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }

            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            currentFeet.getOrCreateTag().putBoolean("Locked", true);
            ModStatistics.awardGearLocked((ServerPlayer) player, itemInHand.getItem());
        }

        return InteractionResultHolder.success(itemInHand);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack targetFeet = target.getItemBySlot(EquipmentSlot.FEET);

        if (user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;
        if (!(targetFeet.getItem() instanceof AnkleMonitorItem) || !(targetFeet.getOrCreateTag().getBoolean("CanBeLocked"))) return InteractionResult.FAIL;
        if (!(target instanceof Player)) return InteractionResult.FAIL;

        if (targetFeet.getOrCreateTag().getBoolean("Locked")) {

            if (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorLockBehavior().equals("onlyLock") || CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorLockBehavior().equals("none")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("🔒 You can not unlock other players ankle monitor 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorLockBehavior().equals("onlyLock".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorLockBehavior().equals("none"))) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("🔒 You are a prisoner!  Prisoners can not unlock other players ankle monitor 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            targetFeet.getOrCreateTag().putBoolean("Locked", false);
            ModStatistics.awardGearUnlocked((ServerPlayer) user, user.getItemInHand(hand).getItem());
        } else if (!targetFeet.getOrCreateTag().getBoolean("Locked")) {

            if (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorLockBehavior().equals("onlyUnlock") || CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorLockBehavior().equals("none")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal("× You can not lock other players ankle monitor ×").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorLockBehavior().equals("onlyUnlock".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorLockBehavior().equals("none"))) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                user.displayClientMessage(Component.literal(" You are a prisoner!  Prisoners can not lock other players ankle monitor ").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            targetFeet.getOrCreateTag().putBoolean("Locked", true);
            ModStatistics.awardGearLocked((ServerPlayer) user, user.getItemInHand(hand).getItem());
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.literal("Used to lock and unlock ankle monitors with the lock modifier").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
