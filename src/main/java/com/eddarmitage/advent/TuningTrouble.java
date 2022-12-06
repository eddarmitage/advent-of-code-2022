package com.eddarmitage.advent;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class TuningTrouble {

    public static void main(String[] args) {
        String input = FileReading.readSingleLine(Path.of(args[0]));

        System.out.println(findEndOfFirstMarker(input, 4));
        System.out.println(findEndOfFirstMarker(input, 14));
    }

    static int findEndOfFirstMarker(String input, int markerLength) {
        for (int i = 0; i < input.length() - markerLength; i++) {
            int endOfMarker = i + markerLength;
            if (allUniqueChars(input.substring(i, endOfMarker))) {
                return endOfMarker;
            }
        }
        return -1;
    }

    private static boolean allUniqueChars(String substring) {
        Set<Integer> charSet = substring.chars().boxed().collect(Collectors.toSet());
        return charSet.size() == substring.length();
    }
}
