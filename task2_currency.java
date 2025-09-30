package ru.student.homework.task2_currency;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CurrencyConverter {

    // Курсы заданы относительно базовой валюты - RUB
    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();

    static {
        EXCHANGE_RATES.put("RUB", 1.0);      // Российский рубль
        EXCHANGE_RATES.put("USD", 91.5);     // Доллар США
        EXCHANGE_RATES.put("EUR", 99.8);     // Евро
        EXCHANGE_RATES.put("ISO", 24.7);     // Новый израильский ше́кель
        EXCHANGE_RATES.put("TRY", 1.98);     // Турецкая лира
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в конвертер валют!");
        System.out.print("Доступные валюты: ");
        EXCHANGE_RATES.keySet().forEach(currency -> System.out.print(currency + " "));
        System.out.println();

        System.out.print("Введите сумму для конвертации: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.out.println("Сумма не может быть отрицательной.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Введите корректное число.");
            return;
        }

        System.out.print("Введите исходную валюту (например, USD): ");
        String sourceCurrency = scanner.nextLine().toUpperCase();

        if (!EXCHANGE_RATES.containsKey(sourceCurrency)) {
            System.out.println("Неизвестная валюта: " + sourceCurrency);
            return;
        }

        // Конвертируем исходную сумму в базовую валюту (RUB)
        double amountInRub = amount * EXCHANGE_RATES.get(sourceCurrency);

        System.out.println("\n--- Результаты конвертации ---");
        System.out.printf("%.2f %s эквивалентно:\n", amount, sourceCurrency);

        // Конвертируем из базовой валюты во все остальные
        for (Map.Entry<String, Double> entry : EXCHANGE_RATES.entrySet()) {
            String targetCurrency = entry.getKey();
            if (!targetCurrency.equals(sourceCurrency)) {
                double targetRate = entry.getValue();
                double convertedAmount = amountInRub / targetRate;
                System.out.printf(" - %.2f %s\n", convertedAmount, targetCurrency);
            }
        }

        scanner.close();
    }
}