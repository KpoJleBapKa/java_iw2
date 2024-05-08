package com.kroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;

public class TransactionReportGeneratorTest {

    @Test
    public void testPrintBalanceReport() {
        TransactionAnalyzer analyzer = new TransactionAnalyzer(new ArrayList<>());
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator(analyzer);
        String expectedReport = "Загальний баланс: 1500.0";
        String actualReport = String.join("\n", TestUtils.captureOutputLines(() -> reportGenerator.printBalanceReport(1500.0)));
        assertEquals(expectedReport, actualReport.trim());
    }

    @Test
    public void testPrintTransactionsCountByMonth() {
        TransactionAnalyzer analyzer = new TransactionAnalyzer(new ArrayList<>());
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator(analyzer);
        String expectedReport = "Кількість транзакцій за 01-2023: 3";
        String actualReport = String.join("\n", TestUtils.captureOutputLines(() -> reportGenerator.printTransactionsCountByMonth("01-2023", 3)));
        assertEquals(expectedReport, actualReport.trim());
    }

    @Test
    public void testPrintTopExpensesReport() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("01-01-2023", -1000, "Expense 1"));
        transactions.add(new Transaction("15-01-2023", -500, "Expense 2"));
        transactions.add(new Transaction("28-02-2023", -300, "Expense 3"));
        transactions.add(new Transaction("10-03-2023", -200, "Expense 4"));
        transactions.add(new Transaction("20-04-2023", -100, "Expense 5"));
        transactions.add(new Transaction("25-05-2023", -50, "Expense 6"));
        transactions.add(new Transaction("15-06-2023", -40, "Expense 7"));
        transactions.add(new Transaction("30-07-2023", -30, "Expense 8"));
        transactions.add(new Transaction("05-08-2023", -20, "Expense 9"));
        transactions.add(new Transaction("20-09-2023", -10, "Expense 10"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator(analyzer);

        String[] expectedReport = {
                "10 найбільших витрат:",
                "Expense 1: -1000.0",
                "Expense 2: -500.0",
                "Expense 3: -300.0",
                "Expense 4: -200.0",
                "Expense 5: -100.0",
                "Expense 6: -50.0",
                "Expense 7: -40.0",
                "Expense 8: -30.0",
                "Expense 9: -20.0",
                "Expense 10: -10.0"
        };

        List<String> actualReportList = TestUtils.captureOutputLines(() -> reportGenerator.printTopExpensesReport(transactions));

        assertArrayEquals(expectedReport, actualReportList.toArray(new String[0]));
    }
}


