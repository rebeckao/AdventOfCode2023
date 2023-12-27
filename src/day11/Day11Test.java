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
        )));
    }

    @Test
    void sumOfGalaxyDistancesRealData() {
        assertEquals(10885634, Day11.sumOfGalaxyDistances(realData(11)));
    }
}