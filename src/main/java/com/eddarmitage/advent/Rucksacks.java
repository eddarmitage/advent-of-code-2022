package com.eddarmitage.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eddarmitage.advent.FileReading.streamFile;

public class Rucksacks {

    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        findIncorrectItems(inputFile);
        findBadges(inputFile);

    }

    private static void findIncorrectItems(Path inputFile) {
        int score = streamFile(inputFile).map(Rucksacks::findItemInBothCompartments)
                .mapToInt(Rucksacks::calculatePriority)
                .sum();
        System.out.println(score);
    }

    private static void findBadges(Path inputFile) {
        int score = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            while (reader.ready()) {
                Set<Character> firstRucksack = getSetOfCharacters(reader.readLine());
                Set<Character> secondRucksack = getSetOfCharacters(reader.readLine());
                Set<Character> thirdRucksack = getSetOfCharacters(reader.readLine());

                score += findCommonItems(firstRucksack, secondRucksack)
                        .filter(thirdRucksack::contains)
                        .mapToInt(Rucksacks::calculatePriority)
                        .findFirst()
                        .orElseThrow();
            }
        } catch (IOException e) {
            System.err.println("SadPanda: " + e);
        }

        System.out.println(score);
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
