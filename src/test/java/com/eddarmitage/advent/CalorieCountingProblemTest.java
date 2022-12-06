package com.eddarmitage.advent;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CalorieCountingProblemTest {

    CalorieCountingProblem problem = new CalorieCountingProblem(Path.of("inputs/1-calorie-counting"));

    @Test
    void solvePartOne() {
        assertEquals(67016, problem.solvePartOne());
    }

    @Test
    void solvePartTwo() {
        assertEquals(200116, problem.solvePartTwo());
    }
}