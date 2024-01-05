package day12;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
    private static final Pattern damagedGroupPattern = Pattern.compile("#+");

    public static long sumOfArrangementCounts(List<String> springRecords) {
        return springRecords.stream()
                .mapToLong(Day12::numberOfDifferentArrangements)
                .sum();
    }

    private static long numberOfDifferentArrangements(String springString) {
        String[] parts = springString.split(" ");
        String operationStatuses = parts[0];
        String contiguousGroups = parts[1];
        List<String> differentVariants = allDifferentVariants(operationStatuses);
        return differentVariants.stream()
                .map(Day12::contiguousGroups)
                .filter(it -> it.equals(contiguousGroups))
                .count();
    }

    private static String contiguousGroups(String statuses) {
        Matcher matcher = damagedGroupPattern.matcher(statuses);
        return matcher.results()
                .map(it -> it.group().length())
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private static List<String> allDifferentVariants(String springStatuses) {
        int nextUnknown = springStatuses.indexOf('?');
        if (nextUnknown == -1) {
            return List.of(springStatuses);
        } else {
            String firstPart = springStatuses.substring(0, nextUnknown);
            List<String> variantsOfNextPart = allDifferentVariants(springStatuses.substring(nextUnknown + 1));
            return variantsOfNextPart.stream()
                    .flatMap(it -> Stream.of(
                            firstPart + "." + it,
                            firstPart + "#" + it
                    ))
                    .toList();
        }
    }
}
