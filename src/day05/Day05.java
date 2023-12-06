package day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 {

    static long lowestLocationNumberForSeeds(List<String> almanac) {
        List<Long> seeds = Arrays.stream(almanac.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::valueOf)
                .boxed()
                .toList();
        List<List<ConversionRange>> conversions = new ArrayList<>();
        List<ConversionRange> currentRanges = new ArrayList<>();
        for (String line : almanac.subList(1, almanac.size())) {
            if (line.matches("")) {
                continue;
            }
            if (line.matches("[a-z]+-[a-z]+-[a-z]+ map:")) {
                if (!currentRanges.isEmpty()) {
                    conversions.add(currentRanges);
                }
                currentRanges = new ArrayList<>();
                continue;
            }
            List<Long> rawConversionRange = Arrays.stream(line.split(" ")).mapToLong(Long::valueOf).boxed().toList();
            currentRanges.add(new ConversionRange(rawConversionRange.get(0), rawConversionRange.get(1), rawConversionRange.get(2)));
        }
        conversions.add(currentRanges);

        return seeds.stream().mapToLong(it -> toLocation(it, conversions)).min().orElseThrow();
    }

    private record ConversionRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    }

    private static long toLocation(long seed, List<List<ConversionRange>> conversions) {
        long currentValue = seed;
        for (List<ConversionRange> conversion : conversions) {
            currentValue = convert(currentValue, conversion);
        }
        return currentValue;
    }

    private static long convert(long sourceValue, List<ConversionRange> conversion) {
        for (ConversionRange range : conversion) {
            if (sourceValue >= range.sourceRangeStart && sourceValue < range.sourceRangeStart + range.rangeLength) {
                return range.destinationRangeStart + sourceValue - range.sourceRangeStart;
            }
        }
        return sourceValue;
    }
}

