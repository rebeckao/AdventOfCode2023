package day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    @Test
    void multipliedWaysToBeatRecord() {
        assertEquals(288, Day06.multipliedWaysToBeatRecord(List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        )));
    }

    @Test
    void multipliedWaysToBeatRecordRealData() {
        assertEquals(633080, Day06.multipliedWaysToBeatRecord(realData(6)));
    }

    @Test
    void waysToBeatRecordForSingleRace() {
        assertEquals(71503, Day06.waysToBeatRecordForSingleRace(List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        )));
    }

    @Test
    void waysToBeatRecordForSingleRaceRealData() {
        assertEquals(20048741, Day06.waysToBeatRecordForSingleRace(realData(6)));
    }
}