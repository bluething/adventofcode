package io.github.bluething.java.adventofcode._2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Day1_2Test {
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
        Assertions.assertEquals(6L, crackingThePassword(input));
    }

    @Test
    void case02() throws IOException {
        List<String> input = getResourceLines("day1/input");
        Assertions.assertEquals(6379, crackingThePassword(input));
    }

    private long countZeroClicks(long startPosition, char direction, long stepsToRotate) {

        // No movement = no zero clicks
        if (stepsToRotate <= 0) {
            return 0;
        }

        // Direction multiplier: Right = +1, Left = -1
        int stepDirection = (direction == 'L') ? -1 : 1;

        // Determine after how many steps the dial FIRST reaches zero
        int firstZeroStep = (int)(((- (long) stepDirection * startPosition) % 100 + 100) % 100);

        // If firstZeroStep = 0, it actually means step 100
        if (firstZeroStep == 0) firstZeroStep = 100;

        // If we can't reach 0 within this rotation, no hits
        if (stepsToRotate < firstZeroStep) return 0;

        // Otherwise: 1 for first hit + extra hits every full 100 steps
        return 1 + (stepsToRotate - firstZeroStep) / 100;
    }

    private int moveDial(int currentPosition, char direction, long stepsToRotate) {

        int stepDirection = (direction == 'R') ? 1 : -1;

        // Only remainder matters for final stop position
        long effectiveSteps = stepsToRotate % 100;

        long finalPosition = (currentPosition + stepDirection * effectiveSteps) % 100;

        if (finalPosition < 0) finalPosition += 100;

        return (int) finalPosition;
    }

    long crackingThePassword(List<String> input) {
        long numOfClick = 0L;
        int pos = 50;
        for (String move : input) {
            char dir = move.charAt(0);
            long k = Long.parseLong(move.substring(1));
            long cnt = countZeroClicks(pos, dir, k);
            numOfClick += cnt;
            pos = moveDial(pos, dir, k);
        }
        return numOfClick;
    }

    private List<String> getResourceLines(String resourcePath) throws IOException {
        // Load file from test resources
        Path path = Path.of(
                getClass().getClassLoader().getResource(resourcePath).getPath()
        );
        return Files.readAllLines(path);
    }
}
