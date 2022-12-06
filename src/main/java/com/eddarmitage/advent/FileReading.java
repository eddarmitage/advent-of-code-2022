package com.eddarmitage.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileReading {
    static String readSingleLine(Path inputFile) {
        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Sad Panda :(", e);
        }
    }

    static Stream<String> streamFile(Path inputFile) {
        try {
            return Files.lines(inputFile);
        } catch (IOException e) {
            throw new RuntimeException("Sad Panda :( ", e);
        }
    }

    static CrazyFileReader readFile(Path inputFile) {
        try {
            return new CrazyFileReader(Files.newBufferedReader(inputFile));
        } catch (IOException e) {
            throw new RuntimeException("Sad Panda :(", e);
        }
    }

    public static class CrazyFileReader {
        private final BufferedReader reader;

        private CrazyFileReader(BufferedReader reader) {
            this.reader = reader;
        }

        public boolean hasMoreInput() {
            try {
                return reader.ready();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
