package day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
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

    @ParameterizedTest()
    @CsvSource({
            "1," +
            ".....n" +
            ".S-7.n" +
            ".|.|.n" +
            ".L-J.n" +
            ".....",
            "1," +
            "..F7.n" +
            ".FJ|.n" +
            "SJ.L7n" +
            "|F--Jn" +
            "LJ...",
            "4," +
            "...........n" +
            ".S-------7.n" +
            ".|F-----7|.n" +
            ".||.....||.n" +
            ".||.....||.n" +
            ".|L-7.F-J|.n" +
            ".|..|.|..|.n" +
            ".L--J.L--J.n" +
            "...........",
            "8," +
            ".F----7F7F7F7F-7....n" +
            ".|F--7||||||||FJ....n" +
            ".||.FJ||||||||L7....n" +
            "FJL7L7LJLJ||LJ.L-7..n" +
            "L--J.L7...LJS7F-7L7.n" +
            "....F-J..F7FJ|L7L7L7n" +
            "....L7.F7||L7|.L7L7|n" +
            ".....|FJLJ|FJ|F7|.LJn" +
            "....FJL-7.||.||||...n" +
            "....L---J.LJ.LJLJ...",
            "10," +
            "FF7FSF7F7F7F7F7F---7n" +
            "L|LJ||||||||||||F--Jn" +
            "FL-7LJLJ||||||LJL-77n" +
            "F--JF--7||LJLJ7F7FJ-n" +
            "L---JF-JLJ.||-FJLJJ7n" +
            "|F|F-JF---7F7-L7L|7|n" +
            "|FFJF7L7F-JF7|JL---7n" +
            "7-L-JL7||F7|L7F-7F7|n" +
            "L.L7LFJ|||||FJL7||LJn" +
            "L7JLJL-JLJLJL--JLJ.L"
    })
    void tilesInLoop(int expected, String map) {
        List<String> pipeMap = Arrays.stream(map.split("n")).toList();
        assertEquals(expected, Day10.tilesInLoop(pipeMap));
    }

    @Test
    void tilesInLoopRealData() {
        assertEquals(355, Day10.tilesInLoop(realData(10)));
    }
}