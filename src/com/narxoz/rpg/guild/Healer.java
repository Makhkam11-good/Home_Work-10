package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = senderName(from);

        switch (topic) {
            case "orders":
                System.out.printf("[%s] prepares recovery kits for %s's order: %s%n", getName(), sender, payload);
                break;
            case "healing":
                System.out.printf("[%s] adjusts the treatment plan from %s: %s%n", getName(), sender, payload);
                break;
            case "urgent":
                System.out.printf("[%s] readies triage beds after %s warns: %s%n", getName(), sender, payload);
                break;
            default:
                System.out.printf("[%s] records %s's message on %s: %s%n", getName(), sender, topic, payload);
                break;
        }
    }

    private String senderName(GuildMember from) {
        return from == null ? "GuildHall" : from.getName();
    }
}
