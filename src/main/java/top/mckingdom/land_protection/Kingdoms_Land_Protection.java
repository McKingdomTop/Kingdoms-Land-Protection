package top.mckingdom.land_protection;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.kingdoms.addons.Addon;
import org.kingdoms.config.KingdomsConfig;
import org.kingdoms.locale.LanguageManager;
import org.kingdoms.main.Kingdoms;
import top.mckingdom.land_protection.configs.LPLang;
import top.mckingdom.land_protection.entitlements.RelationAttributeRegister;
import top.mckingdom.land_protection.entitlements.XRelationAttribute;
import top.mckingdom.land_protection.managers.BeaconEffectsManager;
import top.mckingdom.land_protection.managers.ElytraManager;
import top.mckingdom.land_protection.managers.EnderPearlTeleportManager;

import java.io.File;

public final class Kingdoms_Land_Protection extends JavaPlugin implements Addon {

    public static XRelationAttribute BEACON_EFFECTS = null;
    public static XRelationAttribute ENDER_PEARL_TELEPORT = null;
    public static XRelationAttribute ELYTRA = null;

    public static Kingdoms_Land_Protection INSTANCE;

    public Kingdoms_Land_Protection() {
        INSTANCE = this;
    }

    @Override
    public void onLoad() {

        LanguageManager.registerMessenger(LPLang.class);

    }

    @Override
    public void onEnable() {

        if (!Kingdoms.get().isEnabled()) {
            if (!isKingdomsEnabled()) {
                getLogger().severe("Kingdoms plugin didn't load correctly, disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            };
        }


        BEACON_EFFECTS = RelationAttributeRegister.register("AuspiceAddon", "BEACON_EFFECTS", 450);
        ENDER_PEARL_TELEPORT = RelationAttributeRegister.register("AuspiceAddon", "ENDER_PEARL_TELEPORT", 460);
        ELYTRA = RelationAttributeRegister.register("AuspiceAddon", "ELYTRA", 470);

        registerAllEvents();

        registerAddon();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public void uninstall() {
        Kingdoms.get().getDataCenter().getKingdomManager().getKingdoms().forEach(kingdom -> {     //处理王国的数据

            kingdom.getGroup().getAttributes().values().forEach(attrSet -> {    //去除每种外交关系里面的特定外交属性
                attrSet.remove(ELYTRA);
                attrSet.remove(BEACON_EFFECTS);
                attrSet.remove(ENDER_PEARL_TELEPORT);
            });

        });
    }

    @Override
    public void reloadAddon() {
        registerAllEvents();
    }

    @Override
    public String getAddonName() {
        return "land-protection-addon";
    }

    @Override
    public File getFile() {
        return super.getFile();
    }

    public void registerAllEvents() {

        if (KingdomsConfig.Claims.BEACON_PROTECTED_EFFECTS.getManager().getBoolean()) {
            getServer().getPluginManager().registerEvents(new BeaconEffectsManager(), this);
            EntityPotionEffectEvent.getHandlerList().unregister(Kingdoms.get());
        }
        getServer().getPluginManager().registerEvents(new EnderPearlTeleportManager(), this);
        PlayerTeleportEvent.getHandlerList().unregister(Kingdoms.get());
        getServer().getPluginManager().registerEvents(new ElytraManager(), this);

    }

    public static Kingdoms_Land_Protection get() {
        return INSTANCE;
    }

}
