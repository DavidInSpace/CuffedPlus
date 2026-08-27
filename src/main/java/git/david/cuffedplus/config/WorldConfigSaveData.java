package git.david.cuffedplus.config;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class WorldConfigSaveData extends SavedData {
    // In some class
    public WorldConfigSaveData create() {
        return new WorldConfigSaveData();
    }

    public WorldConfigSaveData load(CompoundTag tag) {
        WorldConfigSaveData data = this.create();
        // Load saved data
        return data;
    }

    @Override public CompoundTag save(CompoundTag pCompoundTag) {
        return null;
    }

}
