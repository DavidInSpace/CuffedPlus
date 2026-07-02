package git.david.cuffedplus.events;


import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.JumpsuitLayer;
import git.david.cuffedplus.init.ModMenuTypes;
import git.david.cuffedplus.screen.CuffTableMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = CuffedPlusMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {


    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            var renderer = event.getSkin(skin);

            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new JumpsuitLayer(playerRenderer));
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
        System.out.printf("Output Phrase: " + output);
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
        System.out.printf("Output Word: " + output);
        return output;
    }

}
