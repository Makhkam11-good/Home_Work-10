package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        if (questLog == null) {
            throw new IllegalArgumentException("QuestLog is required for the council.");
        }
        if (hall == null) {
            throw new IllegalArgumentException("GuildMediator is required for the council.");
        }

        List<Hero> safeParty = party == null ? List.of() : party;
        int notificationsBefore = membersNotifiedTotal(hall);
        int questsTraversed = 0;
        int messagesRouted = 0;

        System.out.println();
        System.out.println("=== War Council Run ===");
        messagesRouted += dispatch(
                hall,
                "general",
                "Council begins with " + partySummary(safeParty) + "."
        );

        System.out.println();
        System.out.println("Iterator pass 1: arrival order");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;
            System.out.println(" [ordered] " + describeQuest(quest));

            messagesRouted += dispatch(
                    hall,
                    "orders",
                    "Assign party preparation for " + quest.getTitle() + "."
            );

            if (quest.isUrgent()) {
                messagesRouted += dispatch(
                        hall,
                        "urgent",
                        quest.getTitle() + " needs immediate council attention."
                );
            }

            if (quest.getTitle().toLowerCase().contains("cursed")) {
                messagesRouted += dispatch(
                        hall,
                        "curse",
                        quest.getTitle() + " requires a curse review before departure."
                );
            }

            if (quest.getTitle().toLowerCase().contains("ruins")) {
                messagesRouted += dispatch(
                        hall,
                        "history",
                        quest.getTitle() + " should be checked against old guild maps."
                );
            }
        }

        System.out.println();
        System.out.println("Iterator pass 2: HIGH priority and above");
        QuestIterator highPriority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (highPriority.hasNext()) {
            Quest quest = highPriority.next();
            questsTraversed++;
            System.out.println(" [priority>=HIGH] " + describeQuest(quest));

            messagesRouted += dispatch(
                    hall,
                    "scouting",
                    "Map risks and routes for " + quest.getTitle() + "."
            );
            messagesRouted += dispatch(
                    hall,
                    "healing",
                    "Prepare recovery plans for " + quest.getTitle() + "."
            );

            if (quest.getRewardGold() >= 300) {
                messagesRouted += dispatch(
                        hall,
                        "rewards",
                        "Reserve " + quest.getRewardGold() + " gold for " + quest.getTitle() + "."
                );
            }
        }

        int membersNotified = membersNotifiedTotal(hall) - notificationsBefore;
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }

    private int dispatch(GuildMediator hall, String topic, String payload) {
        hall.dispatch(topic, null, payload);
        return 1;
    }

    private int membersNotifiedTotal(GuildMediator hall) {
        if (hall instanceof GuildHall guildHall) {
            return guildHall.getMembersNotifiedTotal();
        }
        return 0;
    }

    private String describeQuest(Quest quest) {
        return quest.getTitle()
                + " [" + quest.getPriority() + "]"
                + ", reward=" + quest.getRewardGold()
                + ", urgent=" + quest.isUrgent();
    }

    private String partySummary(List<Hero> party) {
        if (party.isEmpty()) {
            return "no heroes";
        }

        int totalHp = 0;
        int totalAttack = 0;
        int totalDefense = 0;
        for (Hero hero : party) {
            totalHp += hero.getHp();
            totalAttack += hero.getAttackPower();
            totalDefense += hero.getDefense();
        }

        return party.size()
                + " heroes, totalHp=" + totalHp
                + ", totalAttack=" + totalAttack
                + ", totalDefense=" + totalDefense;
    }
}
