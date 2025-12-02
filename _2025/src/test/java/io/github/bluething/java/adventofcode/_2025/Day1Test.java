package io.github.bluething.java.adventofcode._2025;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Day1Test {
    @Test
    void case01() {
        List<String> input = List.of("L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82");
        Assertions.assertEquals(3, crackingThePassword(input));
    }

    @Test
    void case02() throws IOException {
        List<String> input = getResourceLines("day1/input");
        Assertions.assertEquals(3, crackingThePassword(input));
    }
    int crackingThePassword(List<String> input) {
        int direction = 1;
        int password = 0;
        int currentPos = 50;
        int distance = 0;
        for (String rotation : input) {
            direction = rotation.charAt(0) == 'L' ? -1 : 1;
            distance = Integer.parseInt(rotation.substring(1));
            currentPos = (currentPos + (direction * distance)) % 100;
            if (currentPos == 0) {
                password++;
            }
        }
        return password;
    }

    private List<String> getResourceLines(String resourcePath) throws IOException {
        // Load file from test resources
        Path path = Path.of(
                getClass().getClassLoader().getResource(resourcePath).getPath()
        );
        return Files.readAllLines(path);
    }
}
