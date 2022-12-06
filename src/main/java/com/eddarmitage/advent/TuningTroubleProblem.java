package com.eddarmitage.advent;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class TuningTroubleProblem implements Problem {

    private final String input;

    public TuningTroubleProblem(Path inputFile) {
        this.input = FileReading.readSingleLine(inputFile);
    }

    @Override
    public Integer solvePartOne() {
        return findEndOfFirstMarker(input, 4);
    }

    @Override
    public Integer solvePartTwo() {
        return findEndOfFirstMarker(input, 14);
    }

    public static void main(String[] args) {
        Problem problem = new TuningTroubleProblem(Path.of(args[0]));
        System.out.println(problem.solvePartOne());
        System.out.println(problem.solvePartTwo());
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
