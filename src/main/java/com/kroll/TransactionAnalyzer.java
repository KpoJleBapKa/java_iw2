package com.kroll;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionAnalyzer {
    private final List<Transaction> transactions;
    private final DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public Map<String, Integer> countTransactionsByMonth() {
        Map<String, Integer> transactionsByMonth = new HashMap<>();
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            transactionsByMonth.put(monthYear, transactionsByMonth.getOrDefault(monthYear, 0) + 1);
        }
        return transactionsByMonth;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()))
                .limit(10)
                .toList();
    }
}
