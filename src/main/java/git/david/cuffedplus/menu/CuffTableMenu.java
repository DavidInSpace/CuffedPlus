package git.david.cuffedplus.menu;


import git.david.cuffedplus.blockentitie.CuffTableBlockEntity;
import git.david.cuffedplus.init.ModBlock;
import git.david.cuffedplus.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CuffTableMenu extends AbstractContainerMenu {
    private final CuffTableBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // Client Constructor
    public CuffTableMenu(int containerId, Inventory playerInv, @org.jetbrains.annotations.Nullable FriendlyByteBuf additionalData) {
        this(containerId, playerInv, playerInv.player.level().getBlockEntity(Objects.requireNonNull(additionalData).readBlockPos()));
    }

    // Server Constructor
    public CuffTableMenu(int containerId, Inventory playerInv, BlockEntity blockEntity) {
        super(ModMenuTypes.CUFF_TABLE_MENU.get(), containerId);
        if (blockEntity instanceof CuffTableBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed into ExampleMenu!"
                    .formatted(blockEntity.getClass().getCanonicalName()));
        }

        assert blockEntity.getLevel() != null;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createBlockEntityInventory(be);
        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);

    }

    private void createBlockEntityInventory(CuffTableBlockEntity be) {
        be.getOptional().ifPresent(inventory -> {
            addSlot(new SlotItemHandler(inventory, 0, 125, 35));
        });

    }
    private void createPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv,
                        10 + column + (row * 10),
                        8 + (column * 18),
                        84 + (row * 18)));
            }
        }
    }

    private void createPlayerHotbar(Inventory playerInv) {
        for (int column = 1; column < 10; column++) {
            addSlot(new Slot(playerInv,
                    column,
                    8 + ((column - 1) * 18),
                    142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        Slot fromSlot = getSlot(pIndex);
        ItemStack fromStack = fromSlot.getItem();

        if (fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if (!fromSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack copyFromStack = fromStack.copy();

        if (pIndex < 1) {
            // We are inside of the player's inventory
            if (!moveItemStackTo(fromStack, 0, 0, false))
                return ItemStack.EMPTY;
        } else if (pIndex < 1) {
            // We are inside of the block entity inventory
            if (!moveItemStackTo(fromStack, 0, 36, false))
                return ItemStack.EMPTY;
        } else {
            System.err.println("Invalid slot index: " + pIndex);
            return ItemStack.EMPTY;
        }

        fromSlot.setChanged();
        fromSlot.onTake(pPlayer, fromStack);

        return copyFromStack;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, ModBlock.CUFF_TABLE_BLOCK.get());
    }

    public CuffTableBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}