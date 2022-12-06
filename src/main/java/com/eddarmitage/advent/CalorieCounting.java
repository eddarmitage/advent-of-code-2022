package com.eddarmitage.advent;

import java.nio.file.Path;

import static com.eddarmitage.advent.FileReading.readFile;

public class CalorieCounting {
    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        System.out.println(calculateCaloriesCarriedByTopElves(inputFile, 1));
        System.out.println(calculateCaloriesCarriedByTopElves(inputFile, 3));
    }

    private static int calculateCaloriesCarriedByTopElves(Path inputFile, int numberOfTopElves) {
        FixedCapacityBiggestValuesBuffer<Integer> topElves = new FixedCapacityBiggestValuesBuffer<>(numberOfTopElves);
        FileReading.CrazyFileReader reader = readFile(inputFile);
        int currentElfCalorieCount = 0;

        while (reader.hasMoreInput()) {
            String nextLine = reader.readLine();

            if (nextLine.isBlank()) {
                topElves.offer(currentElfCalorieCount);
                currentElfCalorieCount = 0;
                continue;
            }

            currentElfCalorieCount += Integer.parseInt(nextLine);
        }
        return topElves.stream().mapToInt(i -> i).sum();
    }
}