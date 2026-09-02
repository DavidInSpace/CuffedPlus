package git.david.cuffedplus.events;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;

public class ClientConfig {
    private final static Logger LOGGER = LogUtils.getLogger();

    public String id;
    public String name;
    public List<Component> options;

    private static final HashMap<String, String> VALUES = new HashMap<>();

    public static void putValue(String id, String value) {
        LOGGER.debug("Client Config value {} put in to {}", value, id);
        VALUES.put(id, value);
    }

    public static String getValue(String id) {
        LOGGER.debug("Getting Client Config value {} which is {}", id, VALUES.get(id));
        return VALUES.get(id);
    }
}
