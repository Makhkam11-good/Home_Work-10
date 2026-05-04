package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int messagesRouted;
    private int membersNotifiedTotal;
    private int lastDispatchNotifications;

    @Override
    public void register(GuildMember member) {
        if (member == null) {
            return;
        }

        addSubscriber("general", member);

        if (member instanceof Captain) {
            addSubscriber("scouting", member);
            addSubscriber("supplies", member);
            addSubscriber("healing", member);
            addSubscriber("urgent", member);
            addSubscriber("rewards", member);
            addSubscriber("lore", member);
            addSubscriber("curse", member);
            addSubscriber("history", member);
        } else if (member instanceof Quartermaster) {
            addSubscriber("orders", member);
            addSubscriber("supplies", member);
            addSubscriber("rewards", member);
            addSubscriber("urgent", member);
        } else if (member instanceof Scout) {
            addSubscriber("orders", member);
            addSubscriber("scouting", member);
            addSubscriber("urgent", member);
        } else if (member instanceof Healer) {
            addSubscriber("orders", member);
            addSubscriber("healing", member);
            addSubscriber("urgent", member);
            addSubscriber("curse", member);
        } else if (member instanceof Loremaster) {
            addSubscriber("orders", member);
            addSubscriber("lore", member);
            addSubscriber("curse", member);
            addSubscriber("history", member);
            addSubscriber("urgent", member);
        } else {
            addSubscriber("orders", member);
            addSubscriber("urgent", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        String normalizedTopic = normalizeTopic(topic);
        String senderName = from == null ? "GuildHall" : from.getName();
        String message = payload == null ? "" : payload;
        int notified = 0;
        messagesRouted++;

        System.out.printf("[GuildHall] %s dispatches '%s': %s%n", senderName, normalizedTopic, message);

        for (GuildMember member : subscribersFor(normalizedTopic)) {
            if (member == from) {
                System.out.printf("[GuildHall] skipping sender %s%n", member.getName());
                continue;
            }

            System.out.printf("[GuildHall] -> notifying %s%n", member.getName());
            member.receive(normalizedTopic, from, message);
            notified++;
        }

        if (notified == 0) {
            System.out.printf("[GuildHall] no other subscribers for '%s'%n", normalizedTopic);
        }
        lastDispatchNotifications = notified;
        membersNotifiedTotal += notified;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        if (member == null) {
            return;
        }

        List<GuildMember> subscribers = membersByTopic.computeIfAbsent(
                normalizeTopic(topic),
                key -> new ArrayList<>()
        );
        if (!subscribers.contains(member)) {
            subscribers.add(member);
        }
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(normalizeTopic(topic), List.of());
    }

    public int getMessagesRouted() {
        return messagesRouted;
    }

    public int getMembersNotifiedTotal() {
        return membersNotifiedTotal;
    }

    public int getLastDispatchNotifications() {
        return lastDispatchNotifications;
    }

    private String normalizeTopic(String topic) {
        if (topic == null || topic.isBlank()) {
            return "general";
        }
        return topic.trim().toLowerCase();
    }
}
