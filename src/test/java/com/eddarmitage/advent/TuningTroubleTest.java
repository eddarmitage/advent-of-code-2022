package com.eddarmitage.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuningTroubleTest {

    @ParameterizedTest
    @MethodSource("endOfPacketMarkerExamples")
    void testFindEndOfFirstPacketMarker(String input, int expectedResult) {
        assertEquals(expectedResult, TuningTrouble.findEndOfFirstMarker(input, 4));
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
        assertEquals(expectedResult, TuningTrouble.findEndOfFirstMarker(input, 14));
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