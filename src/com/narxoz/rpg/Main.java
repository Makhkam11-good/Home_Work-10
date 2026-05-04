package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.quest.Quest;
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

        // 3. Register at least 4 GuildMembers (Quartermaster, Scout, Healer, Captain) on the GuildHall.
        // 4. Iterate the quest log with at least 2 different QuestIterator implementations.
        // 5. Dispatch coordinating messages through the mediator during quest planning.
        // 6. Run the CouncilEngine and print a final CouncilRunResult.
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
}
