package com.kroll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private final List<Transaction> transactions;
    private final DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
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
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public Map<String, Double> calculateTopExpenseCategories() {
        Map<String, Double> expensesByCategory = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                double amount = transaction.getAmount();
                expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + amount);
            }
        }

        return expensesByCategory.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    public Map<String, Double> calculateTotalExpensesByMonth() {
        Map<String, Double> totalExpensesByMonth = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                totalExpensesByMonth.put(monthYear, totalExpensesByMonth.getOrDefault(monthYear, 0.0) + transaction.getAmount());
            }
        }
        return totalExpensesByMonth;
    }

    public Map<String, Double> calculateTotalExpensesByCategory() {
        Map<String, Double> totalExpensesByCategory = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                totalExpensesByCategory.put(category, totalExpensesByCategory.getOrDefault(category, 0.0) + transaction.getAmount());
            }
        }
        return totalExpensesByCategory;
    }
}
