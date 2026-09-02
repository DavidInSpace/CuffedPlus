package git.david.cuffedplus.net;


import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.ConfigSaveData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class C2SConfigPacket {
    private final static Logger LOGGER = LogUtils.getLogger();
    private final String optionID;
    private final String optionValue;

    public C2SConfigPacket(String id, String value) {
        this.optionID = id;
        this.optionValue = value;
    }

    public C2SConfigPacket(FriendlyByteBuf buffer) {
        this(buffer.readComponent().getString(), buffer.readComponent().getString());
    }

    public static void encode(C2SConfigPacket packet, FriendlyByteBuf buf) {
        buf.writeComponent(Component.literal(packet.optionID));
        buf.writeComponent(Component.literal(packet.optionValue));
    }

    public static C2SConfigPacket decode(FriendlyByteBuf buf) {
        String optionID = buf.readComponent().getString();
        String optionValue = buf.readComponent().getString();
        return new C2SConfigPacket(optionID, optionValue);
    }

    public static void handle(C2SConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer serverPlayer = ctx.get().getSender();
            LOGGER.debug("RECEIVED OPTION ID PACKET: {}", packet.optionID);
            LOGGER.debug("RECEIVED OPTION VALUE PACKET: {}", packet.optionValue);

            assert serverPlayer != null;
            LOGGER.warn("C2SConfigPacket Server level is NOT null! :D");
            ConfigSaveData data = ConfigSaveData.compute(serverPlayer.serverLevel());
            data.setOption(packet.optionID, packet.optionValue);

        });
        ctx.get().setPacketHandled(true);
    }
}

