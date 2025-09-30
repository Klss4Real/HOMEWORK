package ru.student.homework.task3_password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class PasswordGenerator {

    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:'<>,.?/";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Генератор безопасных паролей");
        int length = 0;

        while (length < 8 || length > 12) {
            System.out.print("Введите желаемую длину пароля (от 8 до 12): ");
            try {
                length = Integer.parseInt(scanner.nextLine());
                if (length < 8 || length > 12) {
                    System.out.println("Неверная длина. Пожалуйста, введите число от 8 до 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите корректное число.");
            }
        }

        String password = generatePassword(length);
        System.out.println("Ваш сгенерированный пароль: " + password);

        scanner.close();
    }

    public static String generatePassword(int length) {
        List<Character> passwordChars = new ArrayList<>();

        // 1. Гарантируем наличие как минимум одного символа каждого типа
        passwordChars.add(getRandomChar(LOWERCASE_CHARS));
        passwordChars.add(getRandomChar(UPPERCASE_CHARS));
        passwordChars.add(getRandomChar(DIGITS));
        passwordChars.add(getRandomChar(SPECIAL_CHARS));

        // 2. Заполняем оставшуюся часть пароля случайными символами из всех наборов
        String allChars = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;
        for (int i = 4; i < length; i++) {
            passwordChars.add(getRandomChar(allChars));
        }

        // 3. Перемешиваем символы, чтобы избежать предсказуемой структуры
        Collections.shuffle(passwordChars, random);

        // 4. Собираем символы в строку
        StringBuilder password = new StringBuilder(length);
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    private static char getRandomChar(String source) {
        return source.charAt(random.nextInt(source.length()));
    }
}