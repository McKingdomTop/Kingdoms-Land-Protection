package top.mckingdom.land_protection.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.managers.land.LandEffectsManager;
import org.kingdoms.managers.land.protection.MiscUpgradeManager;
import top.mckingdom.land_protection.Kingdoms_Land_Protection;

public class BeaconEffectsManager implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void whenPlayerInBeacon(EntityPotionEffectEvent event) {
        if (event.getCause() == EntityPotionEffectEvent.Cause.BEACON) {
            if (event.getEntity() instanceof Player) {
                KingdomPlayer player = KingdomPlayer.getKingdomPlayer((Player)event.getEntity());
                Land land = Land.getLand(event.getEntity().getLocation());
                if (land != null && land.isClaimed()) {
                    if (!land.getKingdom().hasAttribute(player.getKingdom(), Kingdoms_Land_Protection.BEACON_EFFECTS)) {
                       event.setCancelled(true);
                    }
                }
            }
        }
    }


}