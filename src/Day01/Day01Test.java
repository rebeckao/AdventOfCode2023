package Day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static common.Common.realData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @Test
    void sumOfCalibrationValues() {
        assertEquals(142, Day01.sumOfCalibrationValues(List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        )));
    }

    @Test
    void sumOfCalibrationValuesRealData() {
        assertEquals(55477, Day01.sumOfCalibrationValues(realData(1)));
    }

    @Test
    void sumOfCalibrationValuesWithSpelledDigits() {
        assertEquals(281, Day01.sumOfCalibrationValuesWithSpelledDigits(List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        )));
    }

    @Test
    void sumOfCalibrationValuesWithSpelledDigitsMoreExamples() {
        assertEquals(162, Day01.sumOfCalibrationValuesWithSpelledDigits(List.of(
                "eighthree",
                "sevenine"
        )));
    }

    @org.junit.jupiter.api.Test
    void sumOfCalibrationValuesWithSpelledDigitsRealData() {
        assertEquals(54431, Day01.sumOfCalibrationValuesWithSpelledDigits(realData(1)));
    }
}