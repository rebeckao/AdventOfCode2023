package day07;

import java.util.Collection;
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

    private static int handStrength(String hand) {
        Collection<Integer> occurences = hand.chars().boxed().collect(toMap(it -> it, _ -> 1, (existing, _) -> existing + 1)).values();
        if (occurences.size() == 1) {
            return 7;
        }
        if (occurences.size() == 2) {
            if (occurences.contains(4)) {
                return 6;
            }
            return 5;
        }
        if (occurences.contains(3)) {
            return 4;
        }
        long numberOfPairs = occurences.stream().filter(it -> it == 2).count();
        if (numberOfPairs == 2) {
            return 3;
        }
        if (numberOfPairs == 1) {
            return 2;
        }
        return 1;
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

    private record HandBid(String hand, int bid) {
    }
}
