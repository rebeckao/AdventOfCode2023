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

    @Test
    void ghostStepsToEnd() {
        assertEquals(6, Day08.ghostStepsToEnd(List.of(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)"
        )));
    }

    @Test
    void ghostStepsToEndRealData() {
        assertEquals(9606140307013L, Day08.ghostStepsToEnd(realData(8)));
    }
}