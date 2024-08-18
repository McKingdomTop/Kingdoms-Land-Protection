package top.mckingdom.land_protection.managers;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.RegisteredListener;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.group.upgradable.MiscUpgrade;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.libs.xseries.particles.ParticleDisplay;
import org.kingdoms.locale.KingdomsLang;
import org.kingdoms.managers.land.protection.MiscUpgradeManager;
import top.mckingdom.land_protection.Kingdoms_Land_Protection;

import javax.annotation.Nonnull;

public class EnderPearlTeleportManager implements Listener {

    @EventHandler(ignoreCancelled = true)
    public final void onPearlTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Player player;
            KingdomPlayer kPlayer = KingdomPlayer.getKingdomPlayer(player = event.getPlayer());
            Land land;
            if ((land = Land.getLand(event.getTo())) != null && land.isClaimed()) {
                Kingdom toKingdom;
                if ((toKingdom = land.getKingdom()).getUpgradeLevel(MiscUpgrade.ANTI_TRAMPLE) >= 3) {
                    Kingdom fromKingdom = kPlayer.getKingdom();
                    if (!Kingdoms_Land_Protection.ENDER_PEARL_TELEPORT.hasAttribute(toKingdom, fromKingdom)) {
                        event.setCancelled(true);
                        ParticleDisplay.of(Particle.CLOUD).withCount(10).spawn(event.getTo());
                        KingdomsLang.LANDS_ENDER_PEARL_PROTECTION.sendError(player);
                    }
                }
            }
        }
    }

}
