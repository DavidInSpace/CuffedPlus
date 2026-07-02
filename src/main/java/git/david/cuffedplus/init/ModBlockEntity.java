package git.david.cuffedplus.init;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.blockentitie.CuffTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CuffedPlusMain.MODID);



    public static final RegistryObject<BlockEntityType<CuffTableBlockEntity>> CUFF_TABLE_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("cuff_table_block_entity",
                    () -> BlockEntityType.Builder.of(CuffTableBlockEntity::new, ModBlock.CUFF_TABLE_BLOCK.get())
                            .build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

}
