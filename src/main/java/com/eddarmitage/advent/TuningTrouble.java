package com.eddarmitage.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TuningTrouble {

    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        String input = null;
        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            input = reader.readLine();
        } catch (IOException e) {
            System.err.println("Sad Panda :(" + e.getLocalizedMessage());
        }

        Objects.requireNonNull(input);
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
