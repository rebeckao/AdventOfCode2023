package day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void sumOfGalaxyDistances() {
        assertEquals(374, Day11.sumOfGalaxyDistances(List.of(
                "...#......",
                ".......#..",
                "#.........",
                "..........",
                "......#...",
                ".#........",
                ".........#",
                "..........",
                ".......#..",
                "#...#....."
        ), 2));
    }

    @Test
    void sumOfGalaxyDistancesRealData() {
        assertEquals(10885634, Day11.sumOfGalaxyDistances(realData(11), 2));
    }

    @Test
    void sumOfOlderGalaxyDistances() {
        List<String> galaxyMap = List.of(
                "...#......",
                ".......#..",
                "#.........",
                "..........",
                "......#...",
                ".#........",
                ".........#",
                "..........",
                ".......#..",
                "#...#....."
        );
        assertEquals(1030, Day11.sumOfGalaxyDistances(galaxyMap, 10));
        assertEquals(8410, Day11.sumOfGalaxyDistances(galaxyMap, 100));
    }

    @Test
    void sumOfOlderGalaxyDistancesRealData() {
        assertEquals(707505470642L, Day11.sumOfGalaxyDistances(realData(11), 1000_000));
    }
}