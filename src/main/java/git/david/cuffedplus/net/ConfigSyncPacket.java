package git.david.cuffedplus.net;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.client.ClientConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

import static git.david.cuffedplus.CuffedPlusMain.DEBUG;

public class ConfigSyncPacket {
    private final static Logger LOGGER = LogUtils.getLogger();
    private final HashMap<String, String> values;

    public ConfigSyncPacket(HashMap<String, String> values) {
        if (DEBUG) LOGGER.debug("Creating ConfigSyncPacket With Value");
        this.values = values;
    }

    public ConfigSyncPacket(FriendlyByteBuf buf) {
        this.values = (HashMap<String, String>) buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readUtf);

        if (DEBUG) LOGGER.debug("Creating ConfigSyncPacket With Buf");

        for (String id : this.values.keySet()) {
            if (DEBUG) LOGGER.debug("Putting value {} to id {} ", this.values.get(id), id);
            values.put(id, this.values.get(id));
        }
    }

    public void encode(FriendlyByteBuf buf) {
        if (DEBUG) LOGGER.debug("Writting ConfigSyncPacket Map");
        buf.writeMap(this.values, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeUtf);
    }

    public static ConfigSyncPacket decode(FriendlyByteBuf buf) {
        HashMap<String, String> map = (HashMap<String, String>) buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readUtf);
        return new ConfigSyncPacket(map);
    }

    public static void handle(ConfigSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        LOGGER.debug("Handle ConfigSyncPacket Start");
        ctx.get().enqueueWork(() -> {
            for (String id : packet.values.keySet()) {
                String value = packet.values.get(id);
                LOGGER.debug("Handling ConfigSyncPacket ID: {}  Value: {}", id, packet.values.get(id));
                if (value != null && !value.isEmpty()) {
                    LOGGER.debug("Putting ConfigSyncPacket ID: {}  with Value: {}", id, packet.values.get(id));
                    ClientConfig.putValue(id, value);
                } else {
                    LOGGER.warn("ConfigSyncPacket ID: {}  Is Empty (Value: {})", id, packet.values.get(id));
                }
            }

            Objects.requireNonNull(ctx.get().getSender()).refreshDisplayName();
        });

        ctx.get().setPacketHandled(true);
    }


}
