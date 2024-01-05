package day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void sumOfArrangementCounts() {
        assertEquals(21, Day12.sumOfArrangementCounts(List.of(
                "???.### 1,1,3",
                ".??..??...?##. 1,1,3",
                "?#?#?#?#?#?#?#? 1,3,1,6",
                "????.#...#... 4,1,1",
                "????.######..#####. 1,6,5",
                "?###???????? 3,2,1"
        )));
    }

    @Test
    void sumOfArrangementCountsRealData() {
        assertEquals(7344, Day12.sumOfArrangementCounts(realData(12)));
    }
}