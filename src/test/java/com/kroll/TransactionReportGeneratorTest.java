package com.kroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TransactionReportGeneratorTest {

    @Test
    public void testPrintBalanceReport() {
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();
        String expectedReport = "Загальний баланс: 1500.0";
        String actualReport = TestUtils.captureOutput(() -> reportGenerator.printBalanceReport(1500.0));
        assertEquals(expectedReport, actualReport.trim());
    }

    @Test
    public void testPrintTransactionsCountByMonth() {
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();
        String expectedReport = "Кількість транзакцій за 01-2023: 3";
        String actualReport = TestUtils.captureOutput(() -> reportGenerator.printTransactionsCountByMonth("01-2023", 3));
        assertEquals(expectedReport, actualReport.trim());
    }

    @Test
    public void testPrintTopExpensesReport() {
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();
        List<Transaction> topExpenses = new ArrayList<>();
        topExpenses.add(new Transaction("01-01-2023", -1000, "Expense 1"));
        topExpenses.add(new Transaction("15-01-2023", -500, "Expense 2"));
        topExpenses.add(new Transaction("28-02-2023", -300, "Expense 3"));

        String expectedReport = "10 найбільших витрат:\n" +
                "Expense 1: -1000.0\n" +
                "Expense 2: -500.0\n" +
                "Expense 3: -300.0\n";
        String actualReport = TestUtils.captureOutput(() -> reportGenerator.printTopExpensesReport(topExpenses));

        assertEquals(expectedReport, actualReport);
    } // Я не розумію, чому не проходиться цей тест, коли як у "Comparison Failure" не показує різницю

}