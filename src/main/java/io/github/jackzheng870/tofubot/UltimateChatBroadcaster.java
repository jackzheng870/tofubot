package io.github.jackzheng870.tofubot;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import br.net.fabiozumbi12.UltimateChat.Bukkit.UCChannel;
import br.net.fabiozumbi12.UltimateChat.Bukkit.UChat;
import br.net.fabiozumbi12.UltimateChat.Bukkit.API.uChatAPI;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.MessageChain;

public class UltimateChatBroadcaster extends AbstractBroadcaster {
    private UCChannel qqChannel;
    private Method sendMessageMethod;
    private ConsoleCommandSender consoleSender;
    private Constructor<?> ultimateFancyConstructor;
    private Method hoverShowTextMethod;

    public UltimateChatBroadcaster(TofubotPlugin plugin) {
        super(plugin);
        uChatAPI ultimateChat = UChat.get().getAPI();

        try {
            ultimateChat.registerNewChannel(new UCChannel("QQ"));
            Class<?> ultimateFancyClass = Class.forName("br.net.fabiozumbi12.UltimateChat.Bukkit.util.UltimateFancy");
            sendMessageMethod = Class.forName("br.net.fabiozumbi12.UltimateChat.Bukkit.UCChannel")
                    .getMethod("sendMessage", ConsoleCommandSender.class, ultimateFancyClass, boolean.class);
            ultimateFancyConstructor = ultimateFancyClass.getDeclaredConstructor(String.class);
            hoverShowTextMethod = ultimateFancyClass.getDeclaredMethod("hoverShowText", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IOException e) {
            e.printStackTrace();
        }

        qqChannel = ultimateChat.getChannel("QQ");
        consoleSender = Bukkit.getConsoleSender();
    }

    @Override
    void broadcastMessage(Member sender, MessageChain messageChain) {
        try {
            sendMessageMethod.invoke(qqChannel, consoleSender,
                    hoverShowTextMethod.invoke(
                            ultimateFancyConstructor.newInstance(formatMessage(sender, messageChain)),
                            messageChain.serializeToMiraiCode()),
                    true);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
