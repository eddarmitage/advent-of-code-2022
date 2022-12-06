package com.eddarmitage.advent;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyStacksProblemTest {

    private final Problem problem = new SupplyStacksProblem(Path.of("inputs/5-supply-stacks"));

    @Test
    void solvePartOne() {
        assertEquals("CVCWCRTVQ", problem.solvePartOne());
    }

    @Test
    void solvePartTwo() {
        assertEquals("CNSCZWLVT", problem.solvePartTwo());
    }
}