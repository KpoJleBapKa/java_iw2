package com.kroll;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() throws IOException {
        TransactionCSVReader csvReader = new TransactionCSVReader();
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = csvReader.readTransactions(filePath);
        assertEquals(26, transactions.size());
    }
}


