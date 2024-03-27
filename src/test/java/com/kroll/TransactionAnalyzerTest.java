package com.kroll;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("01-01-2023", -1000, "Expense 1"));
        transactions.add(new Transaction("15-01-2023", -500, "Expense 2"));
        transactions.add(new Transaction("28-02-2023", -300, "Expense 3"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        List<Transaction> topExpenses = analyzer.findTopExpenses();
    }

}