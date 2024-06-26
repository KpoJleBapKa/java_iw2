package com.kroll;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransactionCSVReader csvReader = new TransactionCSVReader();
        List<Transaction> transactions = csvReader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator(analyzer);

        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);

        Map<String, Integer> transactionsByMonth = analyzer.countTransactionsByMonth();
        for (String monthYear : transactionsByMonth.keySet()) {
            int transactionsCount = transactionsByMonth.get(monthYear);
            reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        }

        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        Map<String, Double> topExpenseCategories = analyzer.calculateTopExpenseCategories();
        reportGenerator.printTopExpenseCategories(topExpenseCategories);


        reportGenerator.writeTotalExpensesReport("total_expenses_report.txt", analyzer.calculateTotalExpensesByMonth(), analyzer.calculateTotalExpensesByCategory());
        System.out.println("Звіт збережено у файлі total_expenses_report.txt");
    }
}




