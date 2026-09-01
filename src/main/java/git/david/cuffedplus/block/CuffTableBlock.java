package git.david.cuffedplus.block;

import git.david.cuffedplus.blockentities.CuffTableBlockEntity;
import git.david.cuffedplus.init.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CuffTableBlock extends Block implements EntityBlock {
    public CuffTableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ModBlockEntity.CUFF_TABLE_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof CuffTableBlockEntity blockEntity))
            return InteractionResult.PASS;

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        if (player instanceof ServerPlayer sPlayer) {
            NetworkHooks.openScreen(
                    sPlayer,
                    blockEntity,
                    (buf) -> buf.writeBlockPos(pos)
            );

        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CuffTableBlockEntity blockEntity) {
                ItemStackHandler inventory = blockEntity.getInventory();
                for (int index = 0; index < inventory.getSlots(); index++) {
                    ItemStack stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
                    level.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
