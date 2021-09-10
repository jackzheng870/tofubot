package io.github.jackzheng870.tofubot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import pro.sandiao.mcqqbot.event.BukkitMcBotEvent;

public class McQQBotListener implements Listener {
    private AbstractBroadcaster broadcaster;

    public McQQBotListener(AbstractBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @EventHandler
    public void onMcQQBot(BukkitMcBotEvent event) {
        BotEvent botEvent = event.getEvent();

        if (botEvent instanceof GroupMessageEvent) {
            onGroupMessage((GroupMessageEvent) botEvent);
        }
    }

    private void onGroupMessage(GroupMessageEvent event) {
        broadcaster.broadcastMessage(event.getSender(), event.getMessage());
    }
}
