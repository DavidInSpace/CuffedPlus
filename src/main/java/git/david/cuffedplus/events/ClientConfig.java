package git.david.cuffedplus.events;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.HashMap;

import static git.david.cuffedplus.CuffedPlusMain.DEBUG;

public class ClientConfig {
    private final static Logger LOGGER = LogUtils.getLogger();

    private static final HashMap<String, String> VALUES = new HashMap<>();

    public static void putValue(String id, String value) {
        if (DEBUG) LOGGER.debug("Client Config value {} put in to {}", value, id);
        VALUES.put(id, value);
    }

    public static String getValue(String id) {
        if (DEBUG) LOGGER.debug("Getting Client Config value {} which is {}", id, VALUES.get(id));
        return VALUES.get(id);
    }

    public static HashMap<String, String> getValues() {
        if (DEBUG) LOGGER.debug("Getting all Client Config values which are {}", VALUES);
        return VALUES;
    }
}
