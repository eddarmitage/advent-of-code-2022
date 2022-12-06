package com.eddarmitage.advent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuningTroubleProblemTest {

    Problem problem = new TuningTroubleProblem(Path.of("inputs/6-tuning-trouble"));

    @Test
    void solvePartOne() {
        assertEquals(1723, problem.solvePartOne());
    }

    @Test
    void solvePartTwo() {
        assertEquals(3708, problem.solvePartTwo());
    }

    @ParameterizedTest
    @MethodSource("endOfPacketMarkerExamples")
    void testFindEndOfFirstPacketMarker(String input, int expectedResult) {
        assertEquals(expectedResult, TuningTroubleProblem.findEndOfFirstMarker(input, 4));
    }

    private static Stream<Arguments> endOfPacketMarkerExamples() {
        return Stream.of(
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 6),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
        );
    }

    @ParameterizedTest
    @MethodSource("endOfMessageMarkerExamples")
    void testFindEndOfFirstMessageMarker(String input, int expectedResult) {
        assertEquals(expectedResult, TuningTroubleProblem.findEndOfFirstMarker(input, 14));
    }

    private static Stream<Arguments> endOfMessageMarkerExamples() {
        return Stream.of(
                Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 23),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26)
        );
    }
}