package com.kitkoch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ShippingCostCalculatorTest {

    private String runMain(String input) throws Exception {
        InputStream oldInput = System.in; //remember real keyboard
        PrintStream oldOutput = System.out; //remember real console
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream(); //capture everything the program prints
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes())); //plug in fake keyboard
            System.setOut(new PrintStream(outBytes)); //plug in fake console (bucket)
            com.kitkoch.ShippingCostCalculator.main(new String[0]); //run program (using fakes)
        } finally {
            System.setIn(oldInput); //put real keyboard back
            System.setOut(oldOutput); //put real console back
        }
        return outBytes.toString();
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 3.50",
            "1.0, 3.50",
            "1.5, 5.50",
            "3.0, 5.50",
            "3.1, 8.50",
            "10.0, 8.50",
            "10.1, 10.50",
            "20.0, 10.50"
    })
    void printsCorrectCostForValidWeights(double weight, String expectedCost) throws Exception {
        String output = runMain(weight + "\n");
        assertTrue(output.contains("Please enter weight: "));
        assertTrue(output.contains("The shipping cost is " + expectedCost), () -> "Output was: " + output);
    }

    @Test
    void weightOverMaxThrowsException()  {
        Scanner sc = new Scanner("25\n");
        assertThrows(com.kitkoch.OverMaxWeightException.class,
                () -> com.kitkoch.ShippingCostCalculator.weightInputDouble(sc, "msg"));
    }
}