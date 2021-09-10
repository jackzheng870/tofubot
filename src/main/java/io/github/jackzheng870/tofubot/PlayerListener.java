package io.github.jackzheng870.tofubot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.mamoe.mirai.Bot;

public class PlayerListener implements Listener {
    private Bot bot;

    public PlayerListener(Bot bot) {
        this.bot = bot;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (player.hasPlayedBefore()) {
            sendMessage(playerName + " joined the server");
        } else {
            sendMessage(playerName + " joined the server for the first time");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        sendMessage(event.getPlayer().getName() + " left the server");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        sendMessage(event.getDeathMessage());
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        sendMessage(
                event.getPlayer().getName() + " has made the advancement " + event.getAdvancement().getKey().getKey());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        sendMessage("<" + event.getPlayer().getName() + "> " + event.getMessage());
    }

    private void sendMessage(String message) {
        bot.getGroups().forEach(group -> group.sendMessage(message));
    }
}
