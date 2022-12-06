package com.eddarmitage.advent;

import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.eddarmitage.advent.FileReading.readFile;

public class SupplyStacksProblem implements Problem {

    private static final Pattern MOVE_PATTERN = Pattern.compile("move (?<quantity>\\d+) from (?<source>\\d+) to (?<destination>\\d+)");

    private final List<String> preamble = new LinkedList<>();
    private final List<String> moveInstructions = new LinkedList<>();

    public SupplyStacksProblem(Path inputFile) {
        FileReading.CrazyFileReader reader = readFile(inputFile);
        boolean parsingPreamble = true;
        while (reader.hasMoreInput()) {
            String line = reader.readLine();
            if (line.isBlank()) {
                parsingPreamble = false;
                continue;
            }

            if (parsingPreamble) {
                preamble.add(line);
            } else {
                moveInstructions.add(line);
            }
        }
    }

    @Override
    public Object solvePartOne() {
        return simulateMoves(CraneType.CRATEMOVER_9000);
    }

    @Override
    public Object solvePartTwo() {
        return simulateMoves(CraneType.CRATEMOVER_9001);
    }

    private String simulateMoves(CraneType crane) {
        StackState stacks = StackState.create(new ArrayList<>(preamble));

        for (String moveInstruction : moveInstructions) {
            Matcher m = MOVE_PATTERN.matcher(moveInstruction);
            if (m.matches()) {
                int quantity = Integer.parseInt(m.group("quantity"));
                int source = Integer.parseInt(m.group("source")) - 1;
                int destination = Integer.parseInt(m.group("destination")) - 1;
                stacks.move(crane, quantity, source, destination);
            }
        }

        return stacks.getTopCrates();
    }

    public static void main(String[] args) {
        Problem problem = new SupplyStacksProblem(Path.of(args[0]));
        System.out.println(problem.solvePartOne());
        System.out.println(problem.solvePartTwo());
    }

    private record StackState(List<Stack<Character>> stacks) {

        private static StackState create(List<String> preamble) {
            Collections.reverse(preamble);
            List<Stack<Character>> result = Arrays.stream(preamble.get(0).split("\\w+"))
                    .map(s -> new Stack<Character>())
                    .toList();
            preamble.remove(0);

            for (String line : preamble) {
                for (int x = 0; x < result.size(); x++) {
                    int charIndex = 4 * x + 1;
                    if (charIndex >= line.length()) {
                        break;
                    }
                    char nextChar = line.charAt(charIndex);
                    if (!Character.isAlphabetic(nextChar)) {
                        continue;
                    }
                    result.get(x).add(nextChar);
                }
            }

            return new StackState(result);
        }

        private void move(int source, int destination) {
            Character crate = stacks.get(source).pop();
            stacks.get(destination).add(crate);
        }

        private void move(CraneType crane, int quantity, int source, int destination) {
            if (crane == CraneType.CRATEMOVER_9000) {
                for (int i = 0; i < quantity; i++) {
                    move(source, destination);
                }
            } else {
                Stack<Character> fakeStack = new Stack<>();
                for (int i = 0; i < quantity; i++) {
                    fakeStack.push(stacks.get(source).pop());
                }
                while (!fakeStack.isEmpty()) {
                    stacks.get(destination).push(fakeStack.pop());
                }
            }
        }

        private String getTopCrates() {
            return stacks.stream()
                    .map(Stack::peek)
                    .map(Object::toString)
                    .collect(Collectors.joining());
        }
    }

    private enum CraneType {
        CRATEMOVER_9000, CRATEMOVER_9001;
    }
}
