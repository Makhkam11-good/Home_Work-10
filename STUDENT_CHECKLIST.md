# Student Checklist — Homework 10

## Phase 1 — Setup

- [x] I can compile the scaffold with `javac`.
- [x] I can run `com.narxoz.rpg.Main`.
- [x] I understand which files contain TODOs.

## Phase 2 — Quest Model

- [x] I understand `Quest` fields and getters.
- [x] I created at least 5 quests in the demo.
- [x] My quests use mixed priorities.

## Phase 3 — Iterator Contract

- [x] `QuestLog` does not expose a public `getQuests()`.
- [x] Clients use `QuestIterator`, not `List<Quest>`.
- [x] Iterator classes use snapshots instead of the live internal list.

## Phase 4 — Concrete Iterators

- [x] `OrderedQuestIterator.hasNext()` works.
- [x] `OrderedQuestIterator.next()` works.
- [x] `ReverseQuestIterator.hasNext()` works.
- [x] `ReverseQuestIterator.next()` works.
- [x] `PriorityQuestIterator.hasNext()` works.
- [x] `PriorityQuestIterator.next()` works.

## Phase 5 — Mediator Contract

- [x] Concrete guild members do not store references to other concrete members.
- [x] Concrete guild members use `getMediator().dispatch(...)` for outbound messages.
- [x] No concrete guild member directly calls another member's `receive(...)`.

## Phase 6 — Guild Hall

- [x] `GuildHall.register(...)` assigns members to useful topics.
- [x] `GuildHall.dispatch(...)` finds subscribers by topic.
- [x] The sender is handled intentionally, either skipped or clearly included.
- [x] Dispatch behavior is visible in console output.

## Phase 7 — Colleagues

- [ ] `Quartermaster.receive(...)` has meaningful behavior.
- [ ] `Scout.receive(...)` has meaningful behavior.
- [ ] `Healer.receive(...)` has meaningful behavior.
- [ ] `Captain.receive(...)` has meaningful behavior.
- [ ] Each colleague has one outbound convenience method.

## Phase 8 — Engine and Demo

- [ ] `CouncilEngine` uses at least 2 iterators.
- [ ] `CouncilEngine` dispatches messages through `GuildMediator`.
- [ ] `CouncilRunResult` reports useful counters.
- [ ] `Main.java` prints a readable start-to-finish demo.

## Phase 9 — Final Submission

- [ ] I added `RewardSortedQuestIterator` for Part 4.
- [ ] I added `Loremaster` for Part 4.
- [ ] I included 2 UML diagrams.
- [ ] I removed compiled `.class` files.
- [ ] I zipped the complete source and docs.
