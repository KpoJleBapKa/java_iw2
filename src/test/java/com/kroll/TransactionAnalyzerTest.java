package com.kroll;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionAnalyzerTest {

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("01-01-2023", -1000, "Expense 1"));
        transactions.add(new Transaction("15-01-2023", -500, "Expense 2"));
        transactions.add(new Transaction("28-02-2023", -300, "Expense 3"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        List<Transaction> topExpenses = analyzer.findTopExpenses();

        assertEquals(3, topExpenses.size());
        assertEquals("Expense 1", topExpenses.get(0).getDescription());
        assertEquals(-1000.0, topExpenses.get(0).getAmount());
        assertEquals("Expense 2", topExpenses.get(1).getDescription());
        assertEquals(-500.0, topExpenses.get(1).getAmount());
        assertEquals("Expense 3", topExpenses.get(2).getDescription());
        assertEquals(-300.0, topExpenses.get(2).getAmount());
    }

}

