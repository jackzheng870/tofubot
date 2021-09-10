package io.github.jackzheng870.tofubot;

import com.vdurmont.emoji.EmojiParser;

import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.MessageChain;

public abstract class AbstractBroadcaster {
    private TofubotPlugin plugin;

    AbstractBroadcaster(TofubotPlugin plugin) {
        this.plugin = plugin;
    }

    abstract void broadcastMessage(Member sender, MessageChain messageChain);

    String formatMessage(Member sender, MessageChain messageChain) {
        String nameCard = sender.getNameCard();

        return EmojiParser.parseToAliases("[" + plugin.getName() + "] <"
                + (nameCard.equals("") ? sender.getNick() : nameCard) + "> " + messageChain.contentToString());
    }
}
