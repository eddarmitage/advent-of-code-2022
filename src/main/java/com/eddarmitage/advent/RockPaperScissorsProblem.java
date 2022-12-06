package com.eddarmitage.advent;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.eddarmitage.advent.FileReading.streamFile;

public class RockPaperScissorsProblem implements Problem {
    private static final int WIN_SCORE = 6;
    private static final int LOSE_SCORE = 0;
    private static final int DRAW_SCORE = 3;

    private static final Map<String, Integer> SCORES = new HashMap<>();

    private static final Map<String, String> CHOSEN_SHAPES = new HashMap<>();

    static {
        {
            for (FirstColumn o : FirstColumn.values()) {
                for (SecondColumn y : SecondColumn.values()) {
                    String key = String.format("%s %s", o.name(), y.name());
                    SCORES.put(key, o.outcomeScore(y) + y.shapeScore);
                    CHOSEN_SHAPES.put(key, String.format("%s %s", o.name(), y.requiredGuess(o)));
                }
            }

        }
    }

    private final Path inputFile;

    public RockPaperScissorsProblem(Path inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public Integer solvePartOne() {
        return calculateScore(streamFile(inputFile));
    }

    @Override
    public Integer solvePartTwo() {
        return calculateUltraScore(streamFile(inputFile));
    }

    public static void main(String[] args) {
        Problem problem = new RockPaperScissorsProblem(Path.of(args[0]));
        System.out.println(problem.solvePartOne());
        System.out.println(problem.solvePartTwo());
    }

    static int calculateScore(Stream<String> lines) {
        return lines.mapToInt(SCORES::get).sum();
    }

    static int calculateUltraScore(Stream<String> lines) {
        return lines.map(CHOSEN_SHAPES::get).mapToInt(SCORES::get).sum();
    }

    private enum FirstColumn {
        A {
            @Override
            int outcomeScore(SecondColumn yourShape) {
                return switch (yourShape) {
                    case X -> DRAW_SCORE;
                    case Y -> WIN_SCORE;
                    case Z -> LOSE_SCORE;
                };
            }
        },
        B {
            @Override
            int outcomeScore(SecondColumn guess) {
                return switch (guess) {
                    case X -> LOSE_SCORE;
                    case Y -> DRAW_SCORE;
                    case Z -> WIN_SCORE;
                };
            }

        },
        C {
            @Override
            int outcomeScore(SecondColumn guess) {
                return switch (guess) {
                    case X -> WIN_SCORE;
                    case Y -> LOSE_SCORE;
                    case Z -> DRAW_SCORE;
                };
            }
        };

        abstract int outcomeScore(SecondColumn guess);
    }

    private enum SecondColumn {
        X(1) {
            @Override
            SecondColumn requiredGuess(FirstColumn firstColumn) {
                return switch (firstColumn) {
                    case A -> Z;
                    case B -> X;
                    case C -> Y;
                };
            }
        },
        Y(2) {
            @Override
            SecondColumn requiredGuess(FirstColumn firstColumn) {
                return switch (firstColumn) {
                    case A -> X;
                    case B -> Y;
                    case C -> Z;
                };
            }
        },
        Z(3) {
            @Override
            SecondColumn requiredGuess(FirstColumn firstColumn) {
                return switch (firstColumn) {
                    case A -> Y;
                    case B -> Z;
                    case C -> X;
                };
            }
        };
        private final int shapeScore;

        SecondColumn(int shapeScore) {
            this.shapeScore = shapeScore;
        }

        abstract SecondColumn requiredGuess(FirstColumn firstColumn);
    }
}
