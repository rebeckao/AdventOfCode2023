package day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void totalWinningsJokerRule() {
        assertEquals(5905, Day07.totalWinningsUsingJokerRule(List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483"
        )));
    }

    @Test
    void totalWinningsJokerRuleRealData() {
        assertEquals(249356515, Day07.totalWinningsUsingJokerRule(realData(7)));
    }

    @ParameterizedTest
    @CsvSource({
            "1234J, 2",
            "1233J, 4",
            "1222J, 6",
            "1111J, 7",
            "1122J, 5",
            "123JJ, 4",
            "122JJ, 6",
            "111JJ, 7",
            "12JJJ, 6",
            "11JJJ, 7",
            "1JJJJ, 7",
            "JJJJJ, 7"
    })
    void handStrengthUsingJokerRule(String hand, int expectedStrength) {
        assertEquals(expectedStrength, Day07.handStrengthUsingJokerRule(hand));
    }
}