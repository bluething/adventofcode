package io.github.bluething.java.adventofcode._2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

public class Day2_2Test {
    @Test
    void case01() {
        List<String> input = List.of("11-22","95-115","998-1012","1188511880-1188511890","222220-222224",
                "1698522-1698528","446443-446449","38593856-38593862","565653-565659",
                "824824821-824824827","2121212118-2121212124");
        Assertions.assertEquals(4174379265L, sumOfUnique(input));
    }
    @Test
    void case02() throws IOException {
        List<String> input = getResourceLines("day2/input");
        Assertions.assertEquals(1227775554L, sumOfUnique(List.of(input.getFirst().split(","))));
    }

    public boolean isInvalidId(String s) {
        // True if s is composed of repeating a smaller substring at least twice
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }

    long sumOfUnique(List<String> input) {
        long sum = 0L;
        for (String range : input) {
            String[] digits = range.split("-");
            long low = Long.parseLong(digits[0]);
            long high = Long.parseLong(digits[1]);
            while (low <= high) {
                if (isInvalidId(String.valueOf(low))) {
                    sum += low;
                }
                low++;
            }
        }

        return sum;
    }

    private List<String> getResourceLines(String resourcePath) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }
            return new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .toList();
        }
    }
}
