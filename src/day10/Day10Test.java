package day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void stepsToFarthestPipe() {
        assertEquals(4, Day10.stepsToFarthestPipe(List.of(
                ".....",
                ".S-7.",
                ".|.|.",
                ".L-J.",
                "....."
        )));
        assertEquals(8, Day10.stepsToFarthestPipe(List.of(
                "..F7.",
                ".FJ|.",
                "SJ.L7",
                "|F--J",
                "LJ..."
        )));
    }

    @Test
    void stepsToFarthestPipeRealData() {
        assertEquals(7097, Day10.stepsToFarthestPipe(realData(10)));
    }
}