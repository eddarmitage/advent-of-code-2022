package com.eddarmitage.advent;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CampCleanupProblemTest {

    CampCleanupProblem problem = new CampCleanupProblem(Path.of("inputs/4-camp-cleanup"));

    @Test
    void solvePartOne() {
        assertEquals(485, problem.solvePartOne());
    }

    @Test
    void solvePartTwo() {
        assertEquals(857, problem.solvePartTwo());
    }
}