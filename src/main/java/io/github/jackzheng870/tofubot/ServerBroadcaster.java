package io.github.jackzheng870.tofubot;

import org.bukkit.Bukkit;

import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.MessageChain;

public class ServerBroadcaster extends AbstractBroadcaster {
    public ServerBroadcaster(TofubotPlugin plugin) {
        super(plugin);
    }

    @Override
    void broadcastMessage(Member sender, MessageChain messageChain) {
        Bukkit.broadcastMessage(formatMessage(sender, messageChain));
    }
}
