package git.david.cuffedplus.items.item;

import git.david.cuffedplus.data.WorldSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class JumpsuitItem extends Item {


    public JumpsuitItem(Properties Item) {
        super(Item);
    }


    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return false;
    }
/*
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JumpsuitItem) {
            event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1, 1, false, false));
        }
    } */



    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (player.isCrouching()) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide) {
            MinecraftServer server = player.getServer();
            assert server != null;
            WorldSavedData data = WorldSavedData.get(server);
            if (player.getTags().contains("prisoner") && !data.getCanPrisonersPutJumpsuitsOn()) {
                player.displayClientMessage(Component.literal("You are a Prisoner | Prisoners can't put on jumpsuits by themselves").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResultHolder.fail(stack);
            }
            ItemStack jumpsuit = stack.copy();
            jumpsuit.setCount(1);
            //jumpsuit.enchant();
            //jumpsuit.setCount(1);
            //CompoundTag data = player.getPersistentData();
            if (stack.getOrCreateTag().getBoolean("Locked")) {
                jumpsuit.enchant(Enchantments.BINDING_CURSE, 1);
            }

            //jumpsuit.hideTooltipPart(ItemStack.TooltipPart.ENCHANTMENTS);


            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) {
                    player.drop(currentChest, false);
                }
            }


            player.setItemSlot(EquipmentSlot.CHEST, jumpsuit);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.success(stack);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack chest = target.getItemBySlot(EquipmentSlot.CHEST);

        if (!user.isCrouching()) {
            return InteractionResult.FAIL;
        }


        if (!user.level().isClientSide && target instanceof Player targetPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) user;
            MinecraftServer server = serverPlayer.getServer();
            assert server != null;
            WorldSavedData data = WorldSavedData.get(server);

            if (user.getTags().contains("prisoner") && data.getCanPrisonersPutJumpsuitsOnOthers()) {
                user.displayClientMessage(Component.literal("Prisoners can't put jumpsuits on others").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.FAIL;
            }
            if (!target.hasItemInSlot(EquipmentSlot.CHEST)) {

                ItemStack jumpsuit = stack.copy();
                jumpsuit.enchant(Enchantments.BINDING_CURSE, 1);
                target.addTag("prisoner");
                jumpsuit.getOrCreateTag().putUUID("equippedBy", user.getUUID());
                target.setItemSlot(EquipmentSlot.CHEST, jumpsuit);


                if (!user.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            } else if (chest.getItem() instanceof JumpsuitItem) {

                ItemStack suit = target.getItemBySlot(EquipmentSlot.CHEST).copyAndClear();

                suit.setCount(1);
                boolean added = user.getInventory().add(suit);
                target.getItemBySlot(EquipmentSlot.CHEST).setCount(0);
            //    target.removeTag("prisoner");
                if (!added) {
                    user.drop(chest, false);
                }

            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }


  /**  public static void setNumber(ItemStack stack, byte number) {
        Item item = stack.getItem();
        if (!(item instanceof JumpsuitItem)) {return;}
       stack.getOrCreateTag().putByte("number", number);
    }

    public static byte getNumber(ItemStack stack) {
        Item item = stack.getItem();
        return stack.getOrCreateTag().getByte("number");
    }
  */

    public static void setNumber(ItemStack stack, byte number) {
        stack.getOrCreateTag().putByte("JumpsuitNumber", number);
    }

    public static byte getNumber(ItemStack stack) {
        return stack.getOrCreateTag().getByte("JumpsuitNumber");
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

    public static boolean getHighVisiblity(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("HighVisibility");
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        byte number = getNumber(stack);
        Item item = stack.getItem();
        if (!(item instanceof JumpsuitItem)) {return;}

       // System.out.println("Number: " + number);
       // System.out.println(String.valueOf(getNumber(stack)));
            tooltip.add(Component.literal("Number: " + number).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
