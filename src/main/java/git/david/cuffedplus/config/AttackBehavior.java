package git.david.cuffedplus.config;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class AttackBehavior {

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event) {
        Entity target = event.getTarget();
        Player attacker = event.getEntity();

        if (!(target instanceof Player)) return;
        Player targetPlayer = (Player) target;
        if (!ConfigHandler.handleAttackBehavior(attacker, targetPlayer)) {
            event.setCanceled(true);
        }
    }


}
