package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = senderName(from);

        switch (topic) {
            case "scouting":
                System.out.printf("[%s] updates the battle map from %s's scout report: %s%n", getName(), sender, payload);
                break;
            case "supplies":
                System.out.printf("[%s] adjusts the marching plan after %s reports supplies: %s%n", getName(), sender, payload);
                break;
            case "healing":
                System.out.printf("[%s] sets the rest schedule after %s reports aid: %s%n", getName(), sender, payload);
                break;
            case "urgent":
                System.out.printf("[%s] calls the table to attention after %s warns: %s%n", getName(), sender, payload);
                break;
            case "rewards":
                System.out.printf("[%s] weighs reward risk after %s reports: %s%n", getName(), sender, payload);
                break;
            default:
                System.out.printf("[%s] acknowledges %s's message on %s: %s%n", getName(), sender, topic, payload);
                break;
        }
    }

    private String senderName(GuildMember from) {
        return from == null ? "GuildHall" : from.getName();
    }
}
