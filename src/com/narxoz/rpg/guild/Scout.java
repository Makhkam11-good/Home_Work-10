package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = senderName(from);

        switch (topic) {
            case "orders":
                System.out.printf("[%s] marks a route for %s's order: %s%n", getName(), sender, payload);
                break;
            case "scouting":
                System.out.printf("[%s] compares reconnaissance notes from %s: %s%n", getName(), sender, payload);
                break;
            case "urgent":
                System.out.printf("[%s] sends a fast runner after %s warns: %s%n", getName(), sender, payload);
                break;
            default:
                System.out.printf("[%s] logs %s's message on %s: %s%n", getName(), sender, topic, payload);
                break;
        }
    }

    private String senderName(GuildMember from) {
        return from == null ? "GuildHall" : from.getName();
    }
}
