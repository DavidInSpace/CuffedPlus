package git.david.cuffedplus.init;


import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.block.CuffTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CuffedPlusMain.MODID);


    public static final RegistryObject<CuffTableBlock> CUFF_TABLE_BLOCK = BLOCKS.register("cuff_table_block",
            () -> new CuffTableBlock(BlockBehaviour.Properties.of()));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}