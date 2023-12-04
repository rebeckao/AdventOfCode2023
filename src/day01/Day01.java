package day01;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day01 {

    private static final Pattern FIRST_DIGIT_PATTERN = Pattern.compile("[a-zA-Z]*(\\d).*");
    private static final Pattern LAST_DIGIT_PATTERN = Pattern.compile(".*(\\d)[a-zA-Z]*");
    private static Map<String, String> digitNames = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );
    private static final Pattern SPELLED_DIGIT_PATTERN = Pattern.compile("(\\d"
            + digitNames.keySet().stream().map(it -> STR. "|(\{ it })" ).collect(Collectors.joining())
            + ")");

    static int sumOfCalibrationValues(List<String> amendedCalibrationValues) {
        return amendedCalibrationValues.stream()
                .map(it -> findFirstMatch(it, FIRST_DIGIT_PATTERN) + findFirstMatch(it, LAST_DIGIT_PATTERN))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private static String findFirstMatch(String amendedValue, Pattern pattern) {
        Matcher matcher = pattern.matcher(amendedValue);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException(STR. "No match for \{ amendedValue } and \{ pattern }" );
    }

    static int sumOfCalibrationValuesWithSpelledDigits(List<String> amendedCalibrationValues) {
        return amendedCalibrationValues.stream()
                .map(it -> toDigitRepresentation(findFirstMatch(it, SPELLED_DIGIT_PATTERN)) + toDigitRepresentation(findLastMatch(it, SPELLED_DIGIT_PATTERN)))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private static String toDigitRepresentation(String digitString) {
        if (digitNames.containsKey(digitString)) {
            return digitNames.get(digitString);
        }
        return digitString;
    }

    private static String findLastMatch(String amendedValue, Pattern pattern) {
        Matcher matcher = pattern.matcher(amendedValue);
        if (matcher.find()) {
            String match;
            do {
                match = matcher.group();
            } while (matcher.find(matcher.start(1) + 1));
            return match;
        }
        throw new IllegalArgumentException(STR. "No match for \{ amendedValue } and \{ pattern }" );
    }
}
