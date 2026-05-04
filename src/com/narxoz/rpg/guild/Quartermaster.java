package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for gear, supplies, and rewards.
 */
public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = senderName(from);

        switch (topic) {
            case "orders":
                System.out.printf("[%s] checks gear after %s's order: %s%n", getName(), sender, payload);
                break;
            case "supplies":
                System.out.printf("[%s] updates the supply ledger from %s: %s%n", getName(), sender, payload);
                break;
            case "rewards":
                System.out.printf("[%s] reserves reward coin after %s reports: %s%n", getName(), sender, payload);
                break;
            case "urgent":
                System.out.printf("[%s] opens the emergency stores for %s: %s%n", getName(), sender, payload);
                break;
            default:
                System.out.printf("[%s] notes %s's message on %s: %s%n", getName(), sender, topic, payload);
                break;
        }
    }

    private String senderName(GuildMember from) {
        return from == null ? "GuildHall" : from.getName();
    }
}
