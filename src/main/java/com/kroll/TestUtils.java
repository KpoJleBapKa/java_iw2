package com.kroll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtils {
    public static String captureOutput(Runnable code) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            code.run();
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }
}
