package day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    @Test
    void totalWinnings() {
        assertEquals(6440, Day07.totalWinnings(List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483"
        )));
    }

    @Test
    void totalWinningsRealData() {
        assertEquals(246795406, Day07.totalWinnings(realData(7)));
    }
}