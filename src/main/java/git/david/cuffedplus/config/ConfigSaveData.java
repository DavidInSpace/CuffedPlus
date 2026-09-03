package git.david.cuffedplus.config;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.base.ConfigOption;
import git.david.cuffedplus.init.ModNetwork;
import git.david.cuffedplus.net.ConfigSyncPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.HashMap;

import static git.david.cuffedplus.CuffedPlusMain.DEBUG;
import static git.david.cuffedplus.config.Config.OPTIONS;

public class ConfigSaveData extends SavedData {
    private final static Logger LOGGER = LogUtils.getLogger();
    private HashMap<String, String> options = new HashMap<>();

    private ConfigSaveData(HashMap<String, String> list) {
        this.options = list;
        this.setDirty();
        if (DEBUG) LOGGER.debug("creative ConfigSaveData ConfigSyncPacket with options {}", this.options);
        ModNetwork.sendToAllClients(new ConfigSyncPacket(this.options));
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
        return this.options.getOrDefault(id, Config.getOptionById(id).getValues()[Config.getOptionById(id).getDefaultValue()].getString());
    }

    public HashMap<String, String> getOptions(boolean fillEmpty) {


        HashMap<String, String> optionsMap = new HashMap<>();
        for (ConfigOption option : OPTIONS) {
            String id = option.getID();
            optionsMap.put(id, this.options.get(id));
            if (fillEmpty && (this.options.get(id).isEmpty() || this.options.get(id) == null || this.options.get(id).isBlank())) {
                optionsMap.put(id, option.getValue(option.getDefaultValue()).getString());
            }
        }
        return optionsMap;
    }

    public void resetAllToDefault() {
        for (ConfigOption configOption : OPTIONS) {
            this.options.replace(configOption.getID(), configOption.getValues()[configOption.getDefaultValue()].getString());
        }
        this.setDirty();
        if (DEBUG) LOGGER.debug("sending ConfigSyncPacket with options {}", this.options);
        ModNetwork.sendToAllClients(new ConfigSyncPacket(this.options));
    }

    public void resetCategoryToDefault(String category) {
        if (category.equals("general")) {

        } else if (category.equals("roles")) {

        }
        if (category.equals("prisoners")) {

        }
        if (category.equals("players")) {

        }
        if (category.equals("misc")) {

        }


        this.setDirty();
        ModNetwork.sendToAllClients(new ConfigSyncPacket(this.options));
    }

    public void resetOptionToDefault(String id) {
        for (ConfigOption configOption : OPTIONS) {
            if (configOption.getID().equals(id)) {
                this.options.replace(configOption.getID(), configOption.getID());
            }
        }
        this.setDirty();
        ModNetwork.sendToAllClients(new ConfigSyncPacket(this.options));
    }


    public void setOption(String id, String value) {
        this.options.put(id, value);
        this.setDirty();
        if (DEBUG) LOGGER.debug("setOption ConfigSyncPacket with options {}", this.options);
        ModNetwork.sendToAllClients(new ConfigSyncPacket(this.options));
    }


    public static ConfigSaveData compute(ServerLevel level) {

        ConfigSaveData data = level.getDataStorage().computeIfAbsent(
                ConfigSaveData::load,
                ConfigSaveData::new,
                "cuffedplus_config"
        );
        if (DEBUG) LOGGER.debug("compute ConfigSyncPacket with options {}", data.getOptions(false));
        ModNetwork.sendToAllClients(new ConfigSyncPacket(data.getOptions(true)));
        return data;
    }


}