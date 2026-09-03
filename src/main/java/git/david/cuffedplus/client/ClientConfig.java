package git.david.cuffedplus.client;

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

    public static String getStringValue(String id) {
        if (DEBUG) LOGGER.debug("Getting Client Config value {} which is {}", id, VALUES.get(id));
        return VALUES.get(id);
    }


    public static Boolean getBoolValue(String id) {
        String value = VALUES.get(id);

        if (value == null) return false;

        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))
            LOGGER.warn("Trying to get boolean client config id {} which is {} (not a bool)  ({})", id, VALUES.get(id), value);

        return Boolean.parseBoolean(value);
    }


    public static HashMap<String, String> getValues() {
        if (DEBUG) LOGGER.debug("Getting all Client Config values which are {}", VALUES);
        return VALUES;
    }
}
