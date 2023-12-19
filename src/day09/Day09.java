package day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09 {
    public static int sumOfExtrapolatedValues(List<String> valueHistories) {
        return valueHistories.stream()
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).mapToInt(Integer::parseInt).boxed().toList())
                .mapToInt(Day09::extrapolateNextValue)
                .sum();
    }

    public static int sumOfBackwardExtrapolatedValues(List<String> valueHistories) {
        return valueHistories.stream()
                .map(it -> it.split(" "))
                .map(it -> Arrays.stream(it).mapToInt(Integer::parseInt).boxed().toList())
                .mapToInt(Day09::extrapolateNextValueBackwards)
                .sum();
    }

    private static int extrapolateNextValue(List<Integer> values) {
        List<List<Integer>> differences = levelsOfDifference(values);
        return differences.stream().mapToInt(List::getLast).sum();
    }

    private static int extrapolateNextValueBackwards(List<Integer> values) {
        List<List<Integer>> differences = levelsOfDifference(values);
        int lastExtrapolated = 0;
        for (int i = differences.size() - 1; i >= 0; i--) {
            lastExtrapolated = differences.get(i).getFirst() - lastExtrapolated;
        }
        return lastExtrapolated;
    }

    private static List<List<Integer>> levelsOfDifference(List<Integer> values) {
        List<List<Integer>> differences = new ArrayList<>();
        differences.add(values);
        List<Integer> latest = values;
        boolean differencesRemaining = true;
        while (differencesRemaining) {
            differencesRemaining = false;
            List<Integer> newDifferences = new ArrayList<>();
            differences.add(newDifferences);
            for (int i = 0; i < latest.size() - 1; i++) {
                int difference = latest.get(i + 1) - latest.get(i);
                newDifferences.add(difference);
                if (difference != 0) {
                    differencesRemaining = true;
                }
            }
            latest = newDifferences;
        }
        return differences;
    }
}
