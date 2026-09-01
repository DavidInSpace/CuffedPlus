package git.david.cuffedplus.config.base;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

/*
At first I tried making like a class with a parameter like this ConfigOption<T> so I can have
different types for config options but it was just crazy complex and I couldn't figure it out
so after like 10 hours of trying (no joke) I just gave up and made all options a string
 */
public class ConfigOption {
    Logger LOGGER = LogUtils.getLogger();
    private final String id;
    private final String name;
    private final int defaultValue;
    private final Component[] values;
    private final int description_num;

    public ConfigOption(String id, String name, int description_num, int defaultValue, Component[] values) {
        if (defaultValue > values.length) {
            LOGGER.warn("The default value index ({}) for the config option {} is bigger then amount of values it has {}", defaultValue, id, values.length);
        }

        if (defaultValue > values.length) {
            LOGGER.warn("The amount of descriptions ({}) this config ({}) has is higher then amount of values ({}). Are you sure this is correct?", description_num, id, values.length);
        }

        this.id = id;
        this.name = name;
        this.description_num = description_num;
        this.defaultValue = defaultValue;
        this.values = values;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDescriptionNum() {
        return description_num;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public Component[] getValues() {
        return this.values;
    }

    public Component getValue(int i) {
        if (i >= values.length) {return Component.literal("ERROR: value out of range");}
        return this.values[i];
    }




}
