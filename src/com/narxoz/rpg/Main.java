package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 *
 * The scaffold prints the banner only; students fill in the guild demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        List<Hero> party = List.of(
                new Hero("Aruzhan the Shield", 120, 10, 18),
                new Hero("Daniyar the Embermage", 85, 40, 22, 6, 25)
        );

        System.out.println();
        System.out.println("Party roster:");
        for (Hero hero : party) {
            System.out.println(" - " + hero);
        }

        QuestLog questLog = new QuestLog();

        System.out.println();
        System.out.println("Quest log setup:");
        addQuest(questLog, new Quest("Clear the Rat Cellar", QuestPriority.LOW, 60, false));
        addQuest(questLog, new Quest("Escort the Merchant Caravan", QuestPriority.NORMAL, 140, false));
        addQuest(questLog, new Quest("Break the Cursed Obelisk", QuestPriority.HIGH, 320, false));
        addQuest(questLog, new Quest("Rescue Miners from the Ash Pit", QuestPriority.URGENT, 500, true));
        addQuest(questLog, new Quest("Map the Frostwood Ruins", QuestPriority.NORMAL, 210, false));
        System.out.println("Total quests in log: " + questLog.size());

        GuildHall hall = new GuildHall();
        Captain captain = new Captain("Captain Mira", hall);
        Quartermaster quartermaster = new Quartermaster("Oryn the Ledger", hall);
        Scout scout = new Scout("Sayan Swiftstep", hall);
        Healer healer = new Healer("Dana Brightleaf", hall);

        System.out.println();
        System.out.println("Guild officers registered:");
        System.out.println(" - " + captain.getName() + " (Captain)");
        System.out.println(" - " + quartermaster.getName() + " (Quartermaster)");
        System.out.println(" - " + scout.getName() + " (Scout)");
        System.out.println(" - " + healer.getName() + " (Healer)");

        System.out.println();
        System.out.println("Mediator warm-up:");
        captain.issueOrder("orders", "Open the war council and ready every station.");
        scout.reportRoute("scouting", "North road is clear, east bridge needs rope.");
        quartermaster.requestSupplies("supplies", "Rations, rope, and lantern oil are counted.");
        healer.prepareAid("healing", "Potion satchels and bandages are ready.");

        printTraversal("Quest preview: newest contracts first", questLog.reverse());

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println();
        System.out.println("Final council result:");
        System.out.println(result);
    }

    private static void addQuest(QuestLog questLog, Quest quest) {
        questLog.add(quest);
        System.out.printf(
                " - %s [%s], reward=%d gold, urgent=%s%n",
                quest.getTitle(),
                quest.getPriority(),
                quest.getRewardGold(),
                quest.isUrgent()
        );
    }

    private static void printTraversal(String title, QuestIterator iterator) {
        System.out.println();
        System.out.println(title);
        while (iterator.hasNext()) {
            Quest quest = iterator.next();
            System.out.printf(" - %s [%s]%n", quest.getTitle(), quest.getPriority());
        }
    }
}
