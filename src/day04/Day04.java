package day04;

import java.util.*;
import java.util.function.Function;

import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

public class Day04 {
    public static int sumOfScratchCardPoints(List<String> scratchCards) {
        return scratchCards.stream().mapToInt(Day04::countPoints).sum();
    }

    private static int countPoints(String scratchCard) {
        long numberOfScratchedWinningNumbers = countNumberOfScratchedWinningNumbers(scratchCard);
        return numberOfScratchedWinningNumbers == 0 ? 0 : Double.valueOf(round(Math.pow(2, numberOfScratchedWinningNumbers - 1))).intValue();
    }

    private static long countNumberOfScratchedWinningNumbers(String scratchCard) {
        String numbers = scratchCard.split(":")[1];
        String[] numberParts = numbers.split("\\|");
        Set<String> winningNumbers = new HashSet<>();
        Collections.addAll(winningNumbers, numberParts[0].trim().split(" "));
        String[] scratchedNumbers = numberParts[1].trim().split(" ");
        return Arrays.stream(scratchedNumbers)
                .filter(it -> !it.isEmpty())
                .filter(winningNumbers::contains)
                .count();
    }

    public static int totalNumberOfScratchCards(List<String> scratchCards) {
        Map<Integer, Integer> cardMultipliers = range(0, scratchCards.size()).boxed().collect(toMap(Function.identity(), _ -> 1));
        int totalNumberOfCards = 0;
        for (int card = 0; card < scratchCards.size(); card++) {
            Integer instancesOfThisCard = cardMultipliers.get(card);
            totalNumberOfCards += instancesOfThisCard;
            int numberOfWinners = (int) countNumberOfScratchedWinningNumbers(scratchCards.get(card));
            for (int copy = card + 1; copy < min(card + numberOfWinners + 1, scratchCards.size()); copy++) {
                cardMultipliers.put(copy, cardMultipliers.getOrDefault(copy, 1) + instancesOfThisCard);
            }
        }
        return totalNumberOfCards;
    }
}
