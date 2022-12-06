package com.eddarmitage.advent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsProblemTest {

    Problem problem = new RockPaperScissorsProblem(Path.of("inputs/2-rock-paper-scissors"));

    @Test
    void solvePartOne() {
        assertEquals(11150, problem.solvePartOne());
    }

    @Test
    void solvePartTwo() {
        assertEquals(8295, problem.solvePartTwo());
    }

    @ParameterizedTest
    @MethodSource("testScoreSource")
    void calculateScore(Stream<String> inputLines, int expectedScore) {
        assertEquals(expectedScore, RockPaperScissorsProblem.calculateScore(inputLines));
    }

    @ParameterizedTest
    @MethodSource("testUltraScoreSource")
    void calculateUltraScore(Stream<String> inputLines, int expectedScore) {
        assertEquals(expectedScore, RockPaperScissorsProblem.calculateUltraScore(inputLines));
    }

    private static Stream<Arguments> testScoreSource() {
        return Stream.of(
                Arguments.of(Stream.of("A Y"), 8),
                Arguments.of(Stream.of("B X"), 1),
                Arguments.of(Stream.of("C Z"), 6),
                Arguments.of(Stream.of("A Y", "B X", "C Z"), 15)
        );
    }
    private static Stream<Arguments> testUltraScoreSource() {
        return Stream.of(
                Arguments.of(Stream.of("A Y"), 4),
                Arguments.of(Stream.of("B X"), 1),
                Arguments.of(Stream.of("C Z"), 7),
                Arguments.of(Stream.of("A Y", "B X", "C Z"), 12)
        );
    }
}