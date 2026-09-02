package git.david.cuffedplus.net;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.events.ClientConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.function.Supplier;

public class ConfigSyncPacket {
    private final static Logger LOGGER = LogUtils.getLogger();
    private final HashMap<String, String> values;

    public ConfigSyncPacket(HashMap<String, String> values) {
        this.values = values;
    }

    public ConfigSyncPacket(FriendlyByteBuf buf) {
        int size = buf.readInt();

        this.values = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String id = buf.readUtf();
            String value = buf.readUtf();

            values.put(id, value);
        }
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(values.size());

        for (var entry : values.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeUtf(entry.getValue());
        }
    }


    public static void handle(ConfigSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        LOGGER.debug("Handle ConfigSyncPacket Start");
        ctx.get().enqueueWork(() -> {
            for (String id : packet.values.keySet()) {
                LOGGER.debug("Handling ConfigSyncPacket ID: {}  Value: {}", id, packet.values.get(id));
                ClientConfig.putValue(id, packet.values.get(id));
            }
        });

        ctx.get().setPacketHandled(true);
    }

}
