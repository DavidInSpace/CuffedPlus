package git.david.cuffedplus.net;


import git.david.cuffedplus.config.ConfigSaveData;
import git.david.cuffedplus.init.ModNetwork;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SConfigPacket {
    private final String optionID;
    private final String optionValue;
    private final String action; // "put" or "get"

    public C2SConfigPacket(String id, String value, String action) {
        this.optionID = id;
        this.optionValue = value;
        this.action = action;
    }

    public C2SConfigPacket(FriendlyByteBuf buffer) {
        this(buffer.readComponent().getString(), buffer.readComponent().getString(), buffer.readComponent().getString());
    }

    public static void encode(C2SConfigPacket packet, FriendlyByteBuf buf) {
        buf.writeComponent(Component.literal(packet.optionID));
        buf.writeComponent(Component.literal(packet.optionValue));
        buf.writeComponent(Component.literal(packet.action));
    }

    public static C2SConfigPacket decode(FriendlyByteBuf buf) {
        String optionID = buf.readComponent().toString();
        String optionValue = buf.readComponent().toString();
        String action = buf.readComponent().toString();
        return new C2SConfigPacket(optionID, optionValue, action);
    }

    public static String handle(C2SConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (packet.action.equalsIgnoreCase("put")) {
            ServerPlayer serverPlayer = ctx.get().getSender();
            System.out.println("RECEIVED OPTION ID PACKET: " + packet.optionID);
            System.out.println("RECEIVED OPTION VALUE PACKET: " + packet.optionValue);
            ConfigSaveData data = ConfigSaveData.compute(serverPlayer.serverLevel());
            data.setOption(packet.optionID, packet.optionValue);

            ctx.get().enqueueWork(() -> {

            });
            ctx.get().setPacketHandled(true);
        } else if (packet.action.equalsIgnoreCase("get")) {
            ModNetwork.sendToPlayer(new S2CConfigPacket(packet.optionValue), ctx.get().getSender());
        }
        return "hello";
    }
}

