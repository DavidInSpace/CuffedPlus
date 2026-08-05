package git.david.cuffedplus.net;

import git.david.cuffedplus.items.item.JumpsuitItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ApplyJumpsuitSettingsPacket {
    private final byte number;

    public ApplyJumpsuitSettingsPacket(byte number) {
        this.number = number;
    }

    public static void encode(ApplyJumpsuitSettingsPacket packet, FriendlyByteBuf buf) {
        buf.writeByte(packet.number);
    }

    public static ApplyJumpsuitSettingsPacket decode(FriendlyByteBuf buf) {
        byte number = buf.readByte();
        return new ApplyJumpsuitSettingsPacket(number);
    }

    public static void handle(ApplyJumpsuitSettingsPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ItemStack stack = player.containerMenu.getSlot(0).getItem();
            if (!(stack.getItem() instanceof JumpsuitItem)) return;

            JumpsuitItem.setNumber(stack, packet.number);

        });
        ctx.get().setPacketHandled(true);
    }
}

