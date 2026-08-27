package git.david.cuffedplus.events;


import com.lazrproductions.cuffed.entity.base.IRestrainableEntity;
import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.client.Keybindings;
import git.david.cuffedplus.init.ModMenuTypes;
import git.david.cuffedplus.items.restraints.custom.HazardTapeHeadRestraint;
import git.david.cuffedplus.misc.JumpsuitLayer;
import git.david.cuffedplus.misc.PoliceUniformLayer;
import git.david.cuffedplus.screen.ConfigScreen;
import git.david.cuffedplus.screen.CuffTableMenuScreen;
import git.david.cuffedplus.utils.InfoMessagesHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Random;


@Mod.EventBusSubscriber(modid = CuffedPlusMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public void clientChatEvent(ClientChatEvent event) {
        System.out.println(event.getOriginalMessage() + " " + event.getOriginalMessage().charAt(0) + " " + CuffedPlusMain.SERVER_CONFIG.allowRestrainedPlayersExecuteCommands());
        char Char = event.getOriginalMessage().charAt(0);
        if (Char == '/' && !CuffedPlusMain.SERVER_CONFIG.allowRestrainedPlayersExecuteCommands()) {
            InfoMessagesHandler.sendInfoMessage(Minecraft.getInstance().player, "You can not run commands while restrained", false, false);
            System.out.println("CANCELING");
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void commandEvent(CommandEvent event) {
        System.out.println(event.getParseResults().getContext().getSource().getEntity() + " " + event.getParseResults().getReader() + " " + CuffedPlusMain.SERVER_CONFIG.allowRestrainedPlayersExecuteCommands());
        if (event.getParseResults().getContext().getSource().getEntity() instanceof Player && !CuffedPlusMain.SERVER_CONFIG.allowRestrainedPlayersExecuteCommands()) {
            InfoMessagesHandler.sendInfoMessage(Minecraft.getInstance().player, "You can not run commands while restrained", false, false);
            System.out.println("CANCELING");
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent event) {

    }

    // Event is on the Forge event bus only on the physical client
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (Keybindings.INSTANCE.openConfigKey.consumeClick() && minecraft.player != null) {

            minecraft.forceSetScreen(new ConfigScreen(Component.literal("CONFIG SCREEN :D")));
            minecraft.player.displayClientMessage(Component.literal("OPEN CONFIG MENU KEY PRESSED"), false);
        }
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            var renderer = event.getSkin(skin);

            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new JumpsuitLayer(playerRenderer));
                playerRenderer.addLayer(new PoliceUniformLayer(playerRenderer));
            }
        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.CUFF_TABLE_MENU.get(), CuffTableMenuScreen::new);
        });
    }



    @SubscribeEvent
    public void chat(ClientChatEvent event) {
        Minecraft instance = Minecraft.getInstance();
        if(instance.player instanceof IRestrainableEntity e)
            if(e.getHeadRestraintId().equals(HazardTapeHeadRestraint.ID))
                event.setMessage(mufflifyPhrase(event.getMessage()));
    }

    static final String[] variants = new String[] { "mph", "mhm", "hmm", "fmp", "mpr", "mrp" };

    String mufflifyPhrase(String message) {
        String[] words = message.split(" ");

        String output = "";
        for (int i = 0; i < words.length; i++) {
            output += mufflifyWord(words[i]);
            if(i < words.length - 1)
                output += " ";
        }
        //System.out.printf("Output Phrase: " + output);
        return output;
    }

    String mufflifyWord(String word) {
        String myVariant = variants[new Random().nextInt(3)];

        String output = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(Character.isLetter(c)) {
                if(i == word.length()-1)
                    output += myVariant.charAt(2);
                else if(i == word.length()-2)
                    output += myVariant.charAt(1);
                else
                    output += myVariant.charAt(0);
            } else
                output += c;
        }
        //System.out.printf("Output Word: " + output);
        return output;
    }

}
