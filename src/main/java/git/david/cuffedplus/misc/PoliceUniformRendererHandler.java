package git.david.cuffedplus.misc;

import git.david.cuffedplus.init.ModItems;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "cuffedplus", value = Dist.CLIENT)
public class PoliceUniformRendererHandler {

    // TODO: The second layer of the minecraft skin is still visible when putting on the uniform

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        AbstractClientPlayer player = (AbstractClientPlayer) event.getEntity();

        if (isWearingPoliceUniform(player)) return;

        PlayerModel<?> model = event.getRenderer().getModel();


        model.jacket.visible = false;
        model.leftSleeve.visible = false;
        model.rightSleeve.visible = false;
        model.leftPants.visible = false;
        model.rightPants.visible = false;
        model.hat.visible = true;
    }

    @SubscribeEvent
    public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        AbstractClientPlayer player = (AbstractClientPlayer) event.getEntity();

        if (isWearingPoliceUniform(player)) return;

        PlayerModel<?> model = event.getRenderer().getModel();

        model.jacket.visible = true;
        model.leftSleeve.visible = true;
        model.rightSleeve.visible = true;
        model.leftPants.visible = true;
        model.rightPants.visible = true;
        model.hat.visible = true;
    }

    private static boolean isWearingPoliceUniform(AbstractClientPlayer player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        return !chest.is(ModItems.POLICE_UNIFORM_1.get());
    }
}
