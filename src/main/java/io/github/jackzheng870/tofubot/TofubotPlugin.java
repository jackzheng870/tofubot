package io.github.jackzheng870.tofubot;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TofubotPlugin extends JavaPlugin {
    private Logger logger = getLogger();

    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();
        AbstractBroadcaster broadcaster;

        if (pluginManager.isPluginEnabled("UltimateChat")) {
            logger.info("Using UltimateChat for broadcasting messages.");
            broadcaster = new UltimateChatBroadcaster(this);
        } else {
            broadcaster = new ServerBroadcaster(this);
        }

        if (pluginManager.isPluginEnabled("MiraiMC")) {
            logger.info("Using MiraiMC as the dependency.");
            pluginManager.registerEvents(new MiraiMCListener(broadcaster), this);
        } else if (pluginManager.isPluginEnabled("McQQBot")) {
            logger.info("Using McQQBot as the dependency.");
            pluginManager.registerEvents(new McQQBotListener(broadcaster), this);
        } else {
            logger.warning("Neither MiraiMC nor McQQBot is enabled.");
            pluginManager.disablePlugin(this);
            return;
        }

        new FindBotTask(this).runTaskTimer(this, 0, 20L * 60);
        logger.info(getName() + " has been enabled.");
    }

    @Override
    public void onDisable() {
        logger.info(getName() + " has been disabled.");
    }
}
