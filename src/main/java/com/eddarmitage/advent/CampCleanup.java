package com.eddarmitage.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CampCleanup {

    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        try (Stream<String> lines = Files.lines(inputFile)) {
            System.out.println(lines.filter(CampCleanup::areaFullyContained).count());
        } catch (IOException e) {
            System.err.println("SadPanda: " + e);
        }
        try (Stream<String> lines = Files.lines(inputFile)) {
            System.out.println(lines.filter(CampCleanup::areasOverlap).count());
        } catch (IOException e) {
            System.err.println("SadPanda: " + e);
        }


    }

    private static boolean areasOverlap(String input) {
        String[] elfInputs = input.split(",");
        AreaRange firstRange = new AreaRange(elfInputs[0]);
        AreaRange secondRange = new AreaRange(elfInputs[1]);

        return  (firstRange.start() <= secondRange.start() && secondRange.start() <= firstRange.finish())
                || (secondRange.start() <= firstRange.start() && firstRange.start() <= secondRange.finish());
    }

    private static boolean areaFullyContained(String input) {
        String[] elfInputs = input.split(",");
        AreaRange firstRange = new AreaRange(elfInputs[0]);
        AreaRange secondRange = new AreaRange(elfInputs[1]);

        return  (firstRange.start() <= secondRange.start() && firstRange.finish() >= secondRange.finish())
                || (secondRange.start() <= firstRange.start() && secondRange.finish() >= firstRange.finish());
    }

    private record AreaRange(int start, int finish) {
        private AreaRange(String descriptionString) {
            this(
                    Integer.parseInt(descriptionString.split("-")[0]),
                    Integer.parseInt(descriptionString.split("-")[1]))
            ;
        }
    }
}
