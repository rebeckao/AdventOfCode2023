package day07;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public class Day07 {
    static int totalWinnings(List<String> hands) {
        List<HandBid> handBids = hands.stream()
                .map(it -> it.split(" "))
                .map(it -> new HandBid(it[0], Integer.parseInt(it[1])))
                .sorted((a, b) -> compare(a.hand, b.hand))
                .toList();
        int winnings = 0;
        for (int i = 0; i < handBids.size(); i++) {
            winnings += (i + 1) * handBids.get(i).bid;
        }
        return winnings;
    }

    static int totalWinningsUsingJokerRule(List<String> hands) {
        List<HandBid> handBids = hands.stream()
                .map(it -> it.split(" "))
                .map(it -> new HandBid(it[0], Integer.parseInt(it[1])))
                .sorted((a, b) -> compareUsingJokerRule(a.hand, b.hand))
                .toList();
        int winnings = 0;
        for (int i = 0; i < handBids.size(); i++) {
            winnings += (i + 1) * handBids.get(i).bid;
        }
        return winnings;
    }

    private static int compare(String hand1, String hand2) {
        int strength1 = handStrength(hand1);
        int strength2 = handStrength(hand2);
        if (strength1 > strength2) {
            return 1;
        }
        if (strength2 > strength1) {
            return -1;
        }
        for (int i = 0; i < hand1.length(); i++) {
            int card1 = cardStrength(hand1.charAt(i));
            int card2 = cardStrength(hand2.charAt(i));
            if (card1 == card2) {
                continue;
            }
            return card1 > card2 ? 1 : -1;
        }
        return 0;
    }

    private static int compareUsingJokerRule(String hand1, String hand2) {
        int strength1 = handStrengthUsingJokerRule(hand1);
        int strength2 = handStrengthUsingJokerRule(hand2);
        if (strength1 > strength2) {
            return 1;
        }
        if (strength2 > strength1) {
            return -1;
        }
        for (int i = 0; i < hand1.length(); i++) {
            int card1 = cardStrengthUsingJokerRule(hand1.charAt(i));
            int card2 = cardStrengthUsingJokerRule(hand2.charAt(i));
            if (card1 == card2) {
                continue;
            }
            return card1 > card2 ? 1 : -1;
        }
        return 0;
    }

    private static int handStrength(String hand) {
        Collection<Integer> occurrences = hand.chars().boxed().collect(toMap(it -> it, _ -> 1, (existing, _) -> existing + 1)).values();
        if (occurrences.size() == 1) {
            return 7;
        }
        if (occurrences.size() == 2) {
            if (occurrences.contains(4)) {
                return 6;
            }
            return 5;
        }
        if (occurrences.contains(3)) {
            return 4;
        }
        long numberOfPairs = occurrences.stream().filter(it -> it == 2).count();
        if (numberOfPairs == 2) {
            return 3;
        }
        if (numberOfPairs == 1) {
            return 2;
        }
        return 1;
    }

    protected static int handStrengthUsingJokerRule(String rawHand) {
        int numberOfJokers = (int) rawHand.chars().filter(it -> it == 'J').count();
        if (numberOfJokers == 5) {
            return 7; // five of a kind
        }
        String hand = rawHand.replace("J", "");
        Collection<Integer> occurrences = hand.chars()
                .boxed()
                .collect(toMap(it -> it, _ -> 1, (existing, _) -> existing + 1))
                .values();
        int maxOccurrencesIncludingJokers = Collections.max(occurrences) + numberOfJokers;
        if (maxOccurrencesIncludingJokers == 5) {
            return 7; // five of a kind
        }
        if (maxOccurrencesIncludingJokers == 4) {
            return 6; // four of a kind
        }
        if (occurrences.size() == 2 && maxOccurrencesIncludingJokers == 3) {
            return 5; // full house
        }
        if (maxOccurrencesIncludingJokers == 3) {
            return 4; // three of a kind
        }
        long numberOfPairs = occurrences.stream().filter(it -> it == 2).count();
        if (numberOfPairs == 2) {
            return 3; // two pair
        }
        if (numberOfPairs == 1) {
            return 2; // one pair
        }
        if (maxOccurrencesIncludingJokers == 2) {
            return 2; // one pair
        }
        return 1; // high card
    }

    private static int cardStrength(char character) {
        return switch (character) {
            case 'A' -> 14;
            case 'K' -> 13;
            case 'Q' -> 12;
            case 'J' -> 11;
            case 'T' -> 10;
            default -> character - '0';
        };
    }

    private static int cardStrengthUsingJokerRule(char character) {
        return switch (character) {
            case 'A' -> 13;
            case 'K' -> 12;
            case 'Q' -> 11;
            case 'T' -> 10;
            case 'J' -> 1;
            default -> character - '0';
        };
    }

    private record HandBid(String hand, int bid) {
    }
}
