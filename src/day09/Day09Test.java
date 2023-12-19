package day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {

    @Test
    void sumOfExtrapolatedValues() {
        assertEquals(114, Day09.sumOfExtrapolatedValues(List.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45"
        )));
    }

    @Test
    void sumOfExtrapolatedValuesRealData() {
        assertEquals(1955513104, Day09.sumOfExtrapolatedValues(realData(9)));
    }

    @Test
    void sumOfBackwardExtrapolatedValues() {
        assertEquals(2, Day09.sumOfBackwardExtrapolatedValues(List.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45"
        )));
    }

    @Test
    void sumOfBackwardExtrapolatedValuesRealData() {
        assertEquals(1131, Day09.sumOfBackwardExtrapolatedValues(realData(9)));
    }
}