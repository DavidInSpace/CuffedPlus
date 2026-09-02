package git.david.cuffedplus.items.item.base;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModModelLayers;
import git.david.cuffedplus.items.restraints.client.model.AnkleMonitorModel;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;


public class AnkleMonitorItem extends ArmorItem {

    public AnkleMonitorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
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

    public static void setGlowing(ItemStack stack, int value) {stack.getOrCreateTag().putInt("Glowing", value);}

    public static int getGlowing(ItemStack stack) {return stack.getOrCreateTag().getInt("Glowing");}

    // TODO: Add a way to unbind ankle monitors
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.FEET);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand); // Player must be crouching
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        //if (itemInHand.getItem() instanceof ArmorItem && ((ArmorItem) itemInHand.getItem()).getType() == ArmorItem.Type.CHESTPLATE && currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) return InteractionResultHolder.fail(itemInHand);

        ItemStack monitor = itemInHand.copy();
        monitor.setCount(1);

        if (!(currentChest.getItem() instanceof AnkleMonitorItem)) {
            player.setItemSlot(EquipmentSlot.FEET, monitor);
            if (!player.getAbilities().instabuild) itemInHand.shrink(1);
        }

        return InteractionResultHolder.success(itemInHand);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack targetFeet = target.getItemBySlot(EquipmentSlot.FEET);

        if (user.isCrouching()) return InteractionResult.FAIL; // Player must not be crouching
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;

        if (!target.hasItemInSlot(EquipmentSlot.FEET)) {

            if (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorBehavior().equals("onlyTakeOff".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorBehavior().equals("none"))) {
                user.displayClientMessage(Component.literal("× You can't put ankle monitors on others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.FAIL;
            }

            if (user.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorBehavior().equals("onlyTakeOff".toLowerCase()) || CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorBehavior().equals("none")) {
                user.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't put ankle monitors on others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.FAIL;
            }

            ItemStack monitor = stack.copy();
            monitor.setCount(1);
            target.setItemSlot(EquipmentSlot.FEET, monitor);

            if (!user.getAbilities().instabuild) stack.shrink(1);

        } else if (targetFeet.getItem() instanceof AnkleMonitorItem) {

            user.displayClientMessage(Component.literal("Trying to take off"), false);
            if (targetFeet.getOrCreateTag().getBoolean("CanBeLocked") && targetFeet.getOrCreateTag().getBoolean("Locked")) {
                user.displayClientMessage(Component.literal("🔒 " + GeneralUtils.extractPlayerName(String.valueOf(target.getName())) + "'s ankle monitor is locked on them! 🔒").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            ItemStack ankle_monitor = target.getItemBySlot(EquipmentSlot.FEET).copyAndClear();

            ankle_monitor.setCount(1);
            boolean added = user.getInventory().add(ankle_monitor);
            target.getItemBySlot(EquipmentSlot.FEET).setCount(0);

            if (!added) user.drop(targetFeet, false);

            ItemStack monitor = stack.copy();

            if (!user.getAbilities().instabuild) stack.shrink(1);
            target.setItemSlot(EquipmentSlot.FEET, monitor);


        }
        return InteractionResult.SUCCESS;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                if (slot == EquipmentSlot.FEET) {
                    ModelPart bakedModel = Minecraft.getInstance()
                            .getEntityModels()
                            .bakeLayer(ModModelLayers.ANKLE_MONITOR_LAYER);
                    AnkleMonitorModel<LivingEntity> model = new AnkleMonitorModel<>(bakedModel);

                    model.rightLeg.copyFrom(defaultModel.rightLeg);

                    // Only show right leg
                    model.head.visible = true;
                    model.body.visible = true;
                    model.leftArm.visible = true;
                    model.rightArm.visible = true;
                    model.leftLeg.visible = true;
                    model.rightLeg.visible = true;

                    model.young = defaultModel.young;
                    model.crouching = entity.isCrouching();
                    model.riding = defaultModel.riding;

                    return model;
                }

                return defaultModel;
            }
        });
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getItem() instanceof AnkleMonitorItem) {
            String ownerName;
            ownerName = stack.getOrCreateTag().getString("ownerName");

            tooltip.add(Component.literal("Owner: ").withStyle(ChatFormatting.GRAY).append(Component.literal(GeneralUtils.extractPlayerName(ownerName)).withStyle(ChatFormatting.YELLOW)));


            if (stack.getOrCreateTag().getBoolean("CanBeLocked") && stack.getOrCreateTag().getBoolean("Locked")) {
                tooltip.add(Component.literal("Locked 🔒").withStyle(ChatFormatting.RED));
            } else if (stack.getOrCreateTag().getBoolean("CanBeLocked") && !stack.getOrCreateTag().getBoolean("Locked")) {
                tooltip.add(Component.literal("Unlocked 🔓").withStyle(ChatFormatting.GREEN));
            }

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Modifiers:").withStyle(ChatFormatting.DARK_GRAY));

            if (stack.getOrCreateTag().getBoolean("HighVisibility")) {
                tooltip.add(Component.literal("High Visibility").withStyle(ChatFormatting.YELLOW));
            } else {
                tooltip.remove(Component.literal("High Visibility").withStyle(ChatFormatting.YELLOW));
            }

            if (stack.getOrCreateTag().getInt("Glowing") > 0) {
                tooltip.add(Component.literal("Glowing " + getGlowing(stack)).withStyle(ChatFormatting.YELLOW));
            } else {
                tooltip.remove(Component.literal("Glowing " + getGlowing(stack)).withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, tooltip, flag);
        }
    }
}
