package Day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    @Test
    void sumOfPartNumbers() {
        assertEquals(4361, Day03.sumOfPartNumbers(List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        )));
    }

    @Test
    void sumOfPartNumbersRealData() { // 551871 too high
        assertEquals(551094, Day03.sumOfPartNumbers(realData(3)));
    }

    @Test
    void sumOfGearRatios() {
        assertEquals(467835, Day03.sumOfGearRatios(List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        )));
    }

    @Test
    void sumOfGearRatiosRealData() { // 551871 too high
        assertEquals(80179647, Day03.sumOfGearRatios(realData(3)));
    }

}