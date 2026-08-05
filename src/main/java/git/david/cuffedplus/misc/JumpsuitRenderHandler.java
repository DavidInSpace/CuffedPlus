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


@Mod.EventBusSubscriber(modid ="cuffedplus", value = Dist.CLIENT)
    public class JumpsuitRenderHandler {

        @SubscribeEvent
        public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
            AbstractClientPlayer player = (AbstractClientPlayer) event.getEntity();

            if (isWearingJumpsuit(player)) return;

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

            if (isWearingJumpsuit(player)) return;

            PlayerModel<?> model = event.getRenderer().getModel();


            model.jacket.visible = true;
            model.leftSleeve.visible = true;
            model.rightSleeve.visible = true;
            model.leftPants.visible = true;
            model.rightPants.visible = true;
            model.hat.visible = true;
        }

        private static boolean isWearingJumpsuit(AbstractClientPlayer player) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            return !chest.is(ModItems.DCLASS_JUMPSUIT.get())
                    && !chest.is(ModItems.PRISON_JUMPSUIT_1.get())
                    && !chest.is(ModItems.PRISON_JUMPSUIT_2.get())
                    && !chest.is(ModItems.PRISON_JUMPSUIT_3.get());
        }
    }