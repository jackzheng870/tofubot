package io.github.jackzheng870.tofubot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.code.MiraiCode;

public class MiraiMCListener implements Listener {
    private AbstractBroadcaster broadcaster;

    public MiraiMCListener(AbstractBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @EventHandler
    public void onGroupMessage(MiraiGroupMessageEvent event) {
        broadcaster.broadcastMessage(
                Bot.getInstance(event.getBotID()).getGroup(event.getGroupID()).get(event.getSenderID()),
                MiraiCode.deserializeMiraiCode(event.getMessageToMiraiCode()));
    }
}
