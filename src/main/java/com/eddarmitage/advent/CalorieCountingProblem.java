package com.eddarmitage.advent;

import java.nio.file.Path;

public class CalorieCountingProblem implements Problem {
    FixedCapacityBiggestValuesBuffer<Integer> partOneBuffer = new FixedCapacityBiggestValuesBuffer<>(1);
    FixedCapacityBiggestValuesBuffer<Integer> partTwoBuffer = new FixedCapacityBiggestValuesBuffer<>(3);

    public CalorieCountingProblem(Path inputFile) {
        processInputFile(inputFile);
    }

    private void processInputFile(Path inputFile) {
        FileReading.CrazyFileReader reader = FileReading.readFile(inputFile);
        int currentElfCalorieCount = 0;

        while (reader.hasMoreInput()) {
            String nextLine = reader.readLine();

            if (nextLine.isBlank()) {
                partOneBuffer.offer(currentElfCalorieCount);
                partTwoBuffer.offer(currentElfCalorieCount);
                currentElfCalorieCount = 0;
                continue;
            }

            currentElfCalorieCount += Integer.parseInt(nextLine);
        }
    }

    @Override
    public Integer solvePartOne() {
        return partOneBuffer.stream().mapToInt(i -> i).sum();
    }

    @Override
    public Integer solvePartTwo() {
        return partTwoBuffer.stream().mapToInt(i -> i).sum();
    }

    public static void main(String[] args) {
        Problem problem = new CalorieCountingProblem(Path.of(args[0]));
        System.out.println(problem.solvePartOne());
        System.out.println(problem.solvePartTwo());
    }
}