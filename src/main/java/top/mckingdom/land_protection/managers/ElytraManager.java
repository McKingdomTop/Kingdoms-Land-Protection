package top.mckingdom.land_protection.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.events.lands.LandChangeEvent;
import top.mckingdom.land_protection.Kingdoms_Land_Protection;
import top.mckingdom.land_protection.configs.LPLang;

public class ElytraManager implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void onPlayerFlyBy(LandChangeEvent event) {

        Player player = event.getPlayer();
        if (player.isGliding()) {
            KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
            Land land = event.getToLand();
            if (land != null && (!Kingdoms_Land_Protection.ELYTRA.hasAttribute(land.getKingdom(), kingdomPlayer.getKingdom()))) {
                player.setGliding(false);
                LPLang.LANDS_ELYTRA_PROTECTION.sendError(player);
            }
        }
    }


    @EventHandler(ignoreCancelled = true)
    public void onPlayerFly(EntityToggleGlideEvent event) {
        Entity entity = event.getEntity();
        if (event.isGliding()) {
            if (entity instanceof Player player) {
                KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
                Land land = Land.getLand(entity.getLocation());
                if (land != null && (!Kingdoms_Land_Protection.ELYTRA.hasAttribute(land.getKingdom(), kingdomPlayer.getKingdom()))) {
                    Bukkit.getScheduler().runTaskLater(Kingdoms_Land_Protection.get(), () -> {

                        try {
                            player.setGliding(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }, 1);
                    LPLang.LANDS_ELYTRA_PROTECTION.sendError(player);
                }
            }
        }
    }


}
