package git.david.cuffedplus.items.item;

import git.david.cuffedplus.data.WorldSavedData;
import git.david.cuffedplus.init.ModModelLayers;
import git.david.cuffedplus.items.restraints.client.model.AnkleMonitorModel;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class AnkleMonitorItem extends ArmorItem {

    public AnkleMonitorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.FEET);
        assert Minecraft.getInstance().player != null;

        if (player.isCrouching()) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide) {
            MinecraftServer server = player.getServer();
            assert server != null;
            WorldSavedData data = WorldSavedData.get(server);

            if (false/*player.getTags().contains("prisoner") && data.getCanPrisonersPutJumpsuitsOn()*/) {
                //player.displayClientMessage(Component.literal("Prisoners can't put on jumpsuits by themselves").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResultHolder.fail(stack);
            }
            ItemStack ankle_monitor = stack.copy();
            ankle_monitor .setCount(1);
            if (stack.getOrCreateTag().getBoolean("locked")) {
                ankle_monitor .enchant(Enchantments.BINDING_CURSE, 1);
            }

            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) {
                    player.drop(currentChest, false);
                }
            }

            player.setItemSlot(EquipmentSlot.FEET, ankle_monitor );

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.success(stack);
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

            tooltip.add(Component.literal("Owner: ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(ownerName).withStyle(ChatFormatting.YELLOW));


            super.appendHoverText(stack, level, tooltip, flag);
        }
    }
}
