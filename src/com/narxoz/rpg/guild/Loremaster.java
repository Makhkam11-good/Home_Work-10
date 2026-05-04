package com.narxoz.rpg.guild;

/**
 * Guild scholar responsible for lore, curses, and old histories.
 */
public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = senderName(from);

        switch (topic) {
            case "lore":
                System.out.printf("[%s] opens the lore index for %s: %s%n", getName(), sender, payload);
                break;
            case "curse":
                System.out.printf("[%s] checks warding notes after %s warns: %s%n", getName(), sender, payload);
                break;
            case "history":
                System.out.printf("[%s] compares old campaign records from %s: %s%n", getName(), sender, payload);
                break;
            case "urgent":
                System.out.printf("[%s] searches emergency prophecies for %s: %s%n", getName(), sender, payload);
                break;
            default:
                System.out.printf("[%s] annotates %s's message on %s: %s%n", getName(), sender, topic, payload);
                break;
        }
    }

    private String senderName(GuildMember from) {
        return from == null ? "GuildHall" : from.getName();
    }
}
