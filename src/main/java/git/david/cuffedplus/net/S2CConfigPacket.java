package git.david.cuffedplus.net;

import git.david.cuffedplus.screen.base.AbstractConfigScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CConfigPacket {
    private final String optionValue;

    public S2CConfigPacket(String value) {
        this.optionValue = value;
    }

    public S2CConfigPacket(FriendlyByteBuf buffer) {
        this(buffer.readComponent().getString());
    }

    public static void encode(S2CConfigPacket packet, FriendlyByteBuf buf) {
        buf.writeComponent(Component.literal(packet.optionValue));
    }

    public static S2CConfigPacket decode(FriendlyByteBuf buf) {
        String optionValue = buf.readComponent().toString();
        return new S2CConfigPacket(optionValue);
    }

    public static void handle(S2CConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        AbstractConfigScreen.setServerLevel(ctx.get().getSender().serverLevel());
    }


}
