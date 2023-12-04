package day04;

import java.util.*;

import static java.lang.Math.round;

public class Day04 {
    public static int sumOfScratchCardPoints(List<String> scratchCards) {
        return scratchCards.stream().mapToInt(Day04::countPoints).sum();
    }

    private static int countPoints(String scratchCard) {
        String numbers = scratchCard.split(":")[1];
        String[] numberParts = numbers.split("\\|");
        Set<String> winningNumbers = new HashSet<>();
        Collections.addAll(winningNumbers, numberParts[0].trim().split(" "));
        String[] scratchedNumbers = numberParts[1].trim().split(" ");
        long numberOfScratchedWinningNumbers = Arrays.stream(scratchedNumbers)
                .filter(it -> !it.isEmpty())
                .filter(winningNumbers::contains)
                .count();
        return numberOfScratchedWinningNumbers == 0 ? 0 : Double.valueOf(round(Math.pow(2, numberOfScratchedWinningNumbers - 1))).intValue();
    }
}
