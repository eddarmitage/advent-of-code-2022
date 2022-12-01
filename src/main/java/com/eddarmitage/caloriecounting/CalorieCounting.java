package com.eddarmitage.caloriecounting;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CalorieCounting {
    public static void main(String[] args) {
        Path inputFile = Path.of(args[0]);
        System.out.println(calculateCaloriesCarriedByTopElves(inputFile, 1));
        System.out.println(calculateCaloriesCarriedByTopElves(inputFile, 3));
    }

    private static int calculateCaloriesCarriedByTopElves(Path inputFile, int numberOfTopElves) {
        FixedCapacityBiggestValuesBuffer<Integer> topElves = new FixedCapacityBiggestValuesBuffer<>(numberOfTopElves);
        int currentElfCalorieCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            while (reader.ready()) {
                String nextLine = reader.readLine();

                if (nextLine.isBlank()) {
                    topElves.offer(currentElfCalorieCount);
                    currentElfCalorieCount = 0;
                    continue;
                }

                currentElfCalorieCount += Integer.parseInt(nextLine);
            }
        } catch (IOException e) {
            System.err.println("SadPanda: " + e);
        }
        return topElves.stream().mapToInt(i -> i).sum();
    }
}