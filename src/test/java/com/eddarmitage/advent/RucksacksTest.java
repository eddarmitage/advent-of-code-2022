package com.eddarmitage.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.eddarmitage.advent.Rucksacks.calculatePriority;
import static com.eddarmitage.advent.Rucksacks.findItemInBothCompartments;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksacksTest {

    @ParameterizedTest
    @MethodSource("findItemSource")
    void testFindItemInBothCompartments(String input, Character expectedOutput) {
        assertEquals(expectedOutput, findItemInBothCompartments(input));
    }

    public static Stream<Arguments> findItemSource() {
        return Stream.of(
                Arguments.of("AA", 'A'),
                Arguments.of("ABCAbc", 'A'),
                Arguments.of("Abba", 'b')
        );
    }

    @ParameterizedTest
    @MethodSource("calculatePrioritySource")
    void testCalculatePriority(Character item, int expectedPriority) {
        assertEquals(expectedPriority, calculatePriority(item));
    }

    public static Stream<Arguments> calculatePrioritySource() {
        return Stream.of(
                Arguments.of('a', 1),
                Arguments.of('z', 26),
                Arguments.of('A', 27),
                Arguments.of('Z', 52)
        );
    }
}