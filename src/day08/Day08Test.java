package day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void stepsToEnd() {
        assertEquals(6, Day08.stepsToEnd(List.of(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)"
        )));
    }

    @Test
    void stepsToEndRealData() {
        assertEquals(19241, Day08.stepsToEnd(realData(8)));
    }
}