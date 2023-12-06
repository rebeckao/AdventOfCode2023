package day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    @Test
    void lowestLocationNumberForSeeds() {
        assertEquals(35, Day05.lowestLocationNumberForSeeds(List.of(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4"
        )));
    }

    @Test
    void lowestLocationNumberForSeedsRealData() {
        assertEquals(313045984, Day05.lowestLocationNumberForSeeds(realData(5)));
    }

    @Test
    void lowestLocationNumberForSeedRanges() {
        assertEquals(46, Day05.lowestLocationNumberForSeedRanges(List.of(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4"
        )));
    }

    @Test
    void lowestLocationNumberForSeedRangesRealData() {
        assertEquals(20283860, Day05.lowestLocationNumberForSeedRanges(realData(5)));
    }
}