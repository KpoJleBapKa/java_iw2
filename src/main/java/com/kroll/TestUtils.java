package com.kroll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<String> captureOutputLines(Runnable code) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));

            code.run();

            // Розділяємо виведення на окремі рядки та зберігаємо їх у списку
            String[] lines = outputStream.toString().split(System.lineSeparator());
            List<String> outputLines = new ArrayList<>();
            for (String line : lines) {
                outputLines.add(line.trim());
            }
            return outputLines;
        } finally {
            System.setOut(originalOut);
        }
    }
}

