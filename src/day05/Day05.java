package day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day05 {

    static long lowestLocationNumberForSeeds(List<String> almanac) {
        List<Long> seeds = Arrays.stream(almanac.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::valueOf)
                .boxed()
                .toList();
        List<List<ConversionRange>> conversions = parseConversions(almanac);

        return seeds.stream().mapToLong(it -> toLocation(it, conversions)).min().orElseThrow();
    }

    static long lowestLocationNumberForSeedRanges(List<String> almanac) {
        List<Long> seeds = Arrays.stream(almanac.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::valueOf)
                .boxed()
                .toList();
        List<ValueRange> seedRanges = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i += 2) {
            seedRanges.add(new ValueRange(seeds.get(i), seeds.get(i) + seeds.get(i + 1)));
        }
        List<List<ConversionRange>> conversions = parseConversions(almanac);

        List<ValueRange> locationRanges = toLocationRanges(seedRanges, conversions);

        return locationRanges.stream().mapToLong(it -> it.startInclusive).min().orElseThrow();
    }

    private static List<List<ConversionRange>> parseConversions(List<String> almanac) {
        List<List<ConversionRange>> conversions = new ArrayList<>();
        List<ConversionRange> currentRanges = new ArrayList<>();
        for (String line : almanac.subList(1, almanac.size())) {
            if (line.matches("")) {
                continue;
            }
            if (line.matches("[a-z]+-[a-z]+-[a-z]+ map:")) {
                if (!currentRanges.isEmpty()) {
                    currentRanges.sort(Comparator.comparing(ConversionRange::sourceRangeStart));
                    conversions.add(currentRanges);
                }
                currentRanges = new ArrayList<>();
                continue;
            }
            List<Long> rawConversionRange = Arrays.stream(line.split(" ")).mapToLong(Long::valueOf).boxed().toList();
            currentRanges.add(new ConversionRange(rawConversionRange.get(0), rawConversionRange.get(1), rawConversionRange.get(2)));
        }
        currentRanges.sort(Comparator.comparing(ConversionRange::sourceRangeStart));
        conversions.add(currentRanges);
        return conversions;
    }

    private static long toLocation(long seed, List<List<ConversionRange>> conversions) {
        long currentValue = seed;
        for (List<ConversionRange> conversion : conversions) {
            currentValue = convertToValue(currentValue, conversion);
        }
        return currentValue;
    }

    private static List<ValueRange> toLocationRanges(List<ValueRange> seedRanges, List<List<ConversionRange>> conversions) {
        List<ValueRange> rangesForNextStage = seedRanges;
        for (List<ConversionRange> conversion : conversions) {
            rangesForNextStage = rangesForNextStage.stream().flatMap(it -> convertToRanges(it, conversion).stream()).collect(toList());
        }
        return rangesForNextStage;
    }

    private static long convertToValue(long sourceValue, List<ConversionRange> conversion) {
        for (ConversionRange range : conversion) {
            if (sourceValue >= range.sourceRangeStart && sourceValue < range.sourceRangeStart + range.rangeLength) {
                return convertToValue(sourceValue, range);
            }
        }
        return sourceValue;
    }

    private static long convertToValue(long sourceValue, ConversionRange range) {
        return range.destinationRangeStart + sourceValue - range.sourceRangeStart;
    }

    private static List<ValueRange> convertToRanges(ValueRange range, List<ConversionRange> conversion) {
        List<ValueRange> convertedRanges = new ArrayList<>();
        ValueRange remainingRange = range;
        for (ConversionRange conversionRange : conversion) {
            long conversionRangeEndExclusive = conversionRange.sourceRangeStart + conversionRange.rangeLength;
            if (remainingRange.startInclusive < conversionRange.sourceRangeStart) {
                if (remainingRange.endExclusive <= conversionRange.sourceRangeStart) {
                    // ---start_a===end_a---start_b---end_b---
                    convertedRanges.add(remainingRange);
                    return convertedRanges;
                } else if (remainingRange.endExclusive <= conversionRangeEndExclusive) {
                    // ---start_a===start_b###end_a---end_b---
                    ValueRange beforePart = new ValueRange(
                            remainingRange.startInclusive,
                            conversionRangeEndExclusive
                    );
                    ValueRange overlappingPart = new ValueRange(
                            conversionRange.destinationRangeStart,
                            convertToValue(remainingRange.endExclusive, conversionRange)
                    );
                    convertedRanges.add(beforePart);
                    convertedRanges.add(overlappingPart);
                    return convertedRanges;
                } else {
                    // ---start_a===start_b###end_b~~~end_a---
                    ValueRange beforePart = new ValueRange(
                            remainingRange.startInclusive,
                            conversionRangeEndExclusive
                    );
                    ValueRange overlappingPart = new ValueRange(
                            conversionRange.destinationRangeStart,
                            conversionRange.destinationRangeStart + conversionRange.rangeLength
                    );
                    convertedRanges.add(beforePart);
                    convertedRanges.add(overlappingPart);
                    remainingRange = new ValueRange(
                            conversionRange.sourceRangeStart + conversionRange.rangeLength,
                            remainingRange.endExclusive
                    );
                }
            } else if (remainingRange.startInclusive < conversionRangeEndExclusive) {
                if (remainingRange.endExclusive <= conversionRangeEndExclusive) {
                    // ---start_b---start_a###end_a---end_b---
                    ValueRange overlappingPart = new ValueRange(
                            convertToValue(remainingRange.startInclusive, conversionRange),
                            convertToValue(remainingRange.endExclusive, conversionRange)
                    );
                    convertedRanges.add(overlappingPart);
                    return convertedRanges;
                } else {
                    // ---start_b---start_a###end_b~~~end_a---
                    ValueRange overlappingPart = new ValueRange(
                            convertToValue(remainingRange.startInclusive, conversionRange),
                            conversionRange.destinationRangeStart + conversionRange.rangeLength
                    );
                    convertedRanges.add(overlappingPart);
                    remainingRange = new ValueRange(
                            conversionRange.sourceRangeStart + conversionRange.rangeLength,
                            remainingRange.endExclusive
                    );
                }
            }
            // ---start_b---end_b---start_a~~~end_b
        }
        convertedRanges.add(remainingRange);
        return convertedRanges;
    }

    private record ConversionRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
    }

    private record ValueRange(long startInclusive, long endExclusive) {

    }
}

