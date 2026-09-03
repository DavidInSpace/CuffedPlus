package git.david.cuffedplus.items.item.base;

import git.david.cuffedplus.config.ConfigHandler;
import git.david.cuffedplus.init.ModStatistics;
import git.david.cuffedplus.utils.GeneralUtils;
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


public class JumpsuitKey extends Item {


    public JumpsuitKey(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand);
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        if (!(currentChest.getItem() instanceof JumpsuitItem) || !currentChest.getOrCreateTag().getBoolean("CanBeLocked")) return InteractionResultHolder.fail(itemInHand);


        if (currentChest.getOrCreateTag().getBoolean("Locked")) {
            // UNLOCK

            if (ConfigHandler.handleOwnJumpsuitLockBehavior(player, "unlock")) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                return InteractionResultHolder.fail(itemInHand);
            }

            player.displayClientMessage(Component.literal("🔓 Unlocked your jumpsuit 🔓").withStyle(ChatFormatting.GREEN), true);
            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            currentChest.getOrCreateTag().putBoolean("Locked", false);
            ModStatistics.awardGearUnlocked((ServerPlayer) player, itemInHand.getItem());
        } else if (!currentChest.getOrCreateTag().getBoolean("Locked")) {
            // LOCK

            if (ConfigHandler.handleOwnJumpsuitLockBehavior(player, "lock")) {
                return InteractionResultHolder.fail(itemInHand);
            }

            player.displayClientMessage(Component.literal("🔒 Locked your jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
            player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            currentChest.getOrCreateTag().putBoolean("Locked", true);
            ModStatistics.awardGearLocked((ServerPlayer) player, itemInHand.getItem());
        }

        return InteractionResultHolder.success(itemInHand);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);

        if (user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;
        if (!(targetChest.getItem() instanceof JumpsuitItem) || !(targetChest.getOrCreateTag().getBoolean("CanBeLocked"))) return InteractionResult.FAIL;
        if (!(target instanceof Player)) return InteractionResult.FAIL;


        if (targetChest.getOrCreateTag().getBoolean("Locked")) {
            // UNLOCK
            if (ConfigHandler.handleOthersAnkleMonitorLockBehavior(user, "unlock")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                return InteractionResult.FAIL;
            }

            user.displayClientMessage(Component.literal("🔓 Unlocked " + GeneralUtils.extractPlayerName(target.getName().getString()) + " jumpsuit 🔓").withStyle(ChatFormatting.GREEN), true);
            ((Player) target).displayClientMessage(Component.literal("🔓 Your jumpsuit is now unlocked 🔓").withStyle(ChatFormatting.GREEN), true);
            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, 1.5F);
            targetChest.getOrCreateTag().putBoolean("Locked", false);
            ModStatistics.awardGearUnlocked((ServerPlayer) user, user.getItemInHand(hand).getItem());
        } else if (!targetChest.getOrCreateTag().getBoolean("Locked")) {
            // LOCK
            if (ConfigHandler.handleOthersAnkleMonitorLockBehavior(user, "lock")) {
                user.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                return InteractionResult.FAIL;
            }

            user.displayClientMessage(Component.literal("🔒 Locked " + GeneralUtils.extractPlayerName(target.getName().getString()) + " jumpsuit 🔒").withStyle(ChatFormatting.RED), true);
            ((Player) target).displayClientMessage(Component.literal("🔒 Your jumpsuit is now locked 🔒").withStyle(ChatFormatting.RED), true);
            target.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            user.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1, (float) (0.5F + Math.random() / 5));
            targetChest.getOrCreateTag().putBoolean("Locked", true);
            ModStatistics.awardGearLocked((ServerPlayer) user, user.getItemInHand(hand).getItem());
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.literal("Used to lock and unlock jumpsuits with the lock modifier").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}

