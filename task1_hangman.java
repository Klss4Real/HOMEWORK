package ru.student.homework.task1_hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class HangmanGame {

    private static final String[] WORDS = {"робототехника","колайдер", "майнкрафт","эндермэн"};
    private static final int MAX_LIVES = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String secretWord = WORDS[random.nextInt(WORDS.length)];
        StringBuilder guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        int lives = MAX_LIVES;
        Set<Character> usedLetters = new HashSet<>();

        System.out.println("Добро пожаловать в игру 'Виселица'!");

        while (lives > 0 && guessedWord.toString().contains("_")) {
            System.out.println("\n=============================================");
            printGallows(lives);
            System.out.println("Загаданное слово: " + guessedWord);
            System.out.println("Жизней осталось: " + lives);
            System.out.print("Использованные буквы: ");
            usedLetters.forEach(letter -> System.out.print(letter + " "));
            System.out.println();

            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Введите одну букву.");
                continue;
            }

            char letter = input.charAt(0);

            if (usedLetters.contains(letter)) {
                System.out.println("Вы уже вводили эту букву ранее. Попробуйте другую букву.");
                continue;
            }

            usedLetters.add(letter);

            if (secretWord.indexOf(letter) != -1) {
                System.out.println("Такая буква есть в слове!");
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == letter) {
                        guessedWord.setCharAt(i, letter);
                    }
                }
            } else {
                System.out.println("Такой буквы нет. Вы теряете жизнь.");
                lives--;
            }
        }

        System.out.println("\n=============================================");
        if (guessedWord.toString().equals(secretWord)) {
            System.out.println("Поздравляем! Вы победили! Загаданное слово: " + secretWord);
        } else {
            printGallows(lives);
            System.out.println("Вы проиграли! Загаданное слово: " + secretWord);
        }

        scanner.close();
    }

    private static void printGallows(int lives) {
        String[] gallows = {
                // 0 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========",
                // 1 life

                "  +---+\n" +
                        "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\ |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========",
                // 2 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                // 3 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                // 4 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                // 5 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                // 6 lives
                "  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                // 7 lives (start)

                "  +---+\n" +
                        "  +---+\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
        };

        if (lives >= 0 && lives < gallows.length) {
            System.out.println(gallows[MAX_LIVES - lives]);
        }
    }
}
