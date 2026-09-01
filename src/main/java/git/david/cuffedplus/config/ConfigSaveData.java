package git.david.cuffedplus.config;

import git.david.cuffedplus.config.base.ConfigOption;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static git.david.cuffedplus.config.Config.OPTIONS;

public class ConfigSaveData extends SavedData {
    private HashMap<String, String> options = new HashMap<>();

    private ConfigSaveData(HashMap<String, String> list) {
        this.options = list;
        this.setDirty();
    }

    public ConfigSaveData() {
        this.setDirty();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        for (String id : this.options.keySet()) {
            tag.putString(id, this.options.get(id));
        }
        return tag;
    }

    public static ConfigSaveData load(CompoundTag tag) {
        HashMap<String, String> map = new HashMap<>();
        for (ConfigOption configOption : OPTIONS) {
            System.out.println("Putting Option: " + configOption.getID() + "VALUE: " + tag.getString(configOption.getID()));
            map.put(configOption.getID(), tag.getString(configOption.getID()));
        }

        return new ConfigSaveData(map);
    }

    public String getOptionByID(String id) {
        System.out.println("SEARCHING FOR: " + id + " OPTIONS SIZE: " + this.options.size());
        return this.options.get(id);
    }

    public HashMap<String, String> getOptions() {
        return this.options;
    }

    public void resetAllToDefault() {
        for (ConfigOption configOption : OPTIONS) {
            this.options.replace(configOption.getID(), configOption.getValues()[configOption.getDefaultValue()].getString());
        }
        this.setDirty();
    }

    public void resetToDefault(String id) {
        for (ConfigOption configOption : OPTIONS) {
            if (configOption.getID().equals(id)) {
                this.options.replace(configOption.getID(), configOption.getID());
            }
        }
        this.setDirty();
    }


    public void setOption(String id, String value) {
        this.options.replace(id, value);
        this.setDirty();
    }


    public static ConfigSaveData compute(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                ConfigSaveData::load,
                ConfigSaveData::new,
                "cuffedplus_config"
        );
    }


}