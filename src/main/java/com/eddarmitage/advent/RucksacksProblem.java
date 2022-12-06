package com.eddarmitage.advent;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eddarmitage.advent.FileReading.readFile;
import static com.eddarmitage.advent.FileReading.streamFile;

public class RucksacksProblem implements Problem {

    private final Path inputFile;

    public RucksacksProblem(Path inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public Object solvePartOne() {
        return streamFile(inputFile)
                .map(RucksacksProblem::findItemInBothCompartments)
                .mapToInt(RucksacksProblem::calculatePriority)
                .sum();
    }

    @Override
    public Object solvePartTwo() {
        int score = 0;

        FileReading.CrazyFileReader reader = readFile(inputFile);
        while (reader.hasMoreInput()) {
            Set<Character> firstRucksack = getSetOfCharacters(reader.readLine());
            Set<Character> secondRucksack = getSetOfCharacters(reader.readLine());
            Set<Character> thirdRucksack = getSetOfCharacters(reader.readLine());

            score += findCommonItems(firstRucksack, secondRucksack).filter(thirdRucksack::contains).mapToInt(RucksacksProblem::calculatePriority).findFirst().orElseThrow();
        }
        return score;
    }

    public static void main(String[] args) {
        Problem problem = new RucksacksProblem(Path.of(args[0]));
        System.out.println(problem.solvePartOne());
        System.out.println(problem.solvePartTwo());
    }

    static Character findItemInBothCompartments(String rucksackContents) {
        var midpoint = rucksackContents.length() / 2;
        Set<Character> firstCompartment = getSetOfCharacters(rucksackContents.substring(0, midpoint));
        Set<Character> secondCompartment = getSetOfCharacters(rucksackContents.substring(midpoint));

        return findCommonItems(firstCompartment, secondCompartment).findFirst().orElseThrow();
    }

    private static Stream<Character> findCommonItems(Set<Character> firstItemSet, Set<Character> secondItemSet) {
        return firstItemSet.stream().filter(secondItemSet::contains);
    }

    static int calculatePriority(Character item) {
        if (item > 90) {
            return item - 'a' + 1;
        } else {
            return item - 'A' + 27;
        }
    }

    private static Set<Character> getSetOfCharacters(String string) {
        return string.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    }
}
