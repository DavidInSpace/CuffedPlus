package git.david.cuffedplus.items.item.base;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PoliceUniformItem extends Item {
    public PoliceUniformItem(Item.Properties properties) {super(properties);}

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(itemInHand); // Player must be crouching
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);
        //if (itemInHand.getItem() instanceof ArmorItem && ((ArmorItem) itemInHand.getItem()).getType() == ArmorItem.Type.CHESTPLATE && currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) return InteractionResultHolder.fail(itemInHand);

        ItemStack police_uniform = itemInHand.copy();
        police_uniform.setCount(1);

        if (!(currentChest.getItem() instanceof JumpsuitItem)) {
            player.setItemSlot(EquipmentSlot.CHEST, police_uniform);
            if (!player.getAbilities().instabuild) itemInHand.shrink(1);
        }

        return InteractionResultHolder.success(itemInHand);
    }
}
