package com.kroll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {

    private final TransactionAnalyzer analyzer;

    public TransactionReportGenerator(TransactionAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printTopExpenseCategories(Map<String, Double> topExpenseCategories) {
        System.out.println("Топ категорій з найбільшими витратами:");
        topExpenseCategories.forEach((category, amount) -> System.out.println(category + ": " + amount));
    }

    public void writeTotalExpensesReport(String fileName, Map<String, Double> totalExpensesByMonth, Map<String, Double> totalExpensesByCategory) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Сумарні витрати по місяцях та категоріях:\n");

            // Вивід сумарних витрат по місяцях
            for (String month : totalExpensesByMonth.keySet()) {
                writer.write(String.format("%s: %s\n", month, formatAmount(totalExpensesByMonth.get(month))));
            }

            // Вивід сумарних витрат по категоріях
            for (String category : totalExpensesByCategory.keySet()) {
                writer.write(String.format("%s: %s\n", category, formatAmount(totalExpensesByCategory.get(category))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatAmount(double amount) {
        int starsCount = (int) Math.abs(amount) / 1000; // Кількість зірочок
        return String.join("", Collections.nCopies(starsCount, "*")); // Повертаємо рядок з заданою кількістю символів "*"
    }

}
