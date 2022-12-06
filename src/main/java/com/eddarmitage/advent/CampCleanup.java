package com.eddarmitage.advent;

import java.nio.file.Path;

import static com.eddarmitage.advent.FileReading.streamFile;

public class CampCleanup {

    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        System.out.println(streamFile(inputFile).filter(CampCleanup::areaFullyContained).count());
        System.out.println(streamFile(inputFile).filter(CampCleanup::areasOverlap).count());
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
