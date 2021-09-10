package io.github.jackzheng870.tofubot;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.mamoe.mirai.Bot;

public class FindBotTask extends BukkitRunnable {
    private TofubotPlugin plugin;
    private Logger logger;

    public FindBotTask(TofubotPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
    }

    @Override
    public void run() {
        List<Bot> bots = Bot.getInstances();

        if (bots.isEmpty()) {
            logger.warning("No online bot account found. Retry in one minute.");
        } else {
            Bot bot = bots.get(0);
            logger.info("Online bot account found. Using " + bot.getNick() + " (" + bot.getId() + ").");
            Bukkit.getPluginManager().registerEvents(new PlayerListener(bot), plugin);
            cancel();
        }
    }
}
