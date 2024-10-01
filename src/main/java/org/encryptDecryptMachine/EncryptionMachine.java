package org.encryptDecryptMachine;

import java.util.Scanner;

/**
 * This class provides functionality for encrypting text using a Caesar cipher.
 *
 * As the class is certainly setup, user input has the following assumptions:
 * - the user will always enter a value of the correct type.
 * - the user will only ever enter 1 word when asked for the the key and the next word to encrypt.
 * - the user will only enter letters in the defined ALPHABET constant
 *   (lowercase english letters, by default).
 *
 * @todo - perform user input checking with warnings in the ui
 */
public class EncryptionMachine {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final int SHIFT = 3;

    /**
     * The main method of the program. It handles user interaction, encryption of the key,
     * and encryption of the message.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printIntro();

        encryptAndPrintKey(scanner);

        int numWords = getNumWordsFromUserInput(scanner);

        getAndEncryptWords(numWords, scanner);

        System.out.println("\nMessage fully encrypted. Happy secret messaging!");
    }

    /**
     * Encrypts a single letter using the Caesar cipher.
     *
     * @param letter The letter to encrypt.
     * @return The encrypted letter.
     */
    private static char encryptLetter(char letter) {
        int index = ALPHABET.indexOf(letter);
        int newIndex = (index + SHIFT) % ALPHABET.length();
        return ALPHABET.charAt(newIndex);
    }

    /**
     * Encrypts a word by encrypting each of its letters using the Caesar cipher.
     *
     * @param word The word to encrypt.
     * @return The encrypted word.
     */
    private static String encryptWord(String word) {
        StringBuilder encryptedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char encryptedLetter = encryptLetter(word.charAt(i));
            encryptedWord.append(encryptedLetter);
        }
        return encryptedWord.toString();
    }

    /**
     * Intro text for the encryption machine user input
     */
    private static void printIntro() {
        System.out.println("Welcome to the CSCI717 Encryption Machine Construction");
        System.out.println("The program lets you encrypt a message");
        System.out.println("with a key for your recipient to decrypt!\n");
    }

    /**
     * Gets the key from user, encrypts it, and prints it.
     *
     * @param scanner The Scanner object used for input
     */
    private static void encryptAndPrintKey(Scanner scanner) {
        System.out.print("Enter key: ");
        String key = scanner.next();
        String encryptedKey = encryptWord(key);
        System.out.println("\"" + key + "\" has been encrypted to: " + encryptedKey + "\n");
    }

    /**
     * Gets the number of words to be encrypted from user input.
     *
     * @param scanner The Scanner object used for input
     */
    private static int getNumWordsFromUserInput(Scanner scanner) {
        System.out.print("How many words is your message?: ");
        int numWords = scanner.nextInt();
        scanner.nextLine();
        return numWords;
    }

    /**
     * Takes user input for each word, encrypts it, and prints it.
     *
     * @param numWords How many words to encrypt
     * @param scanner The Scanner object used for input
     */
    private static void getAndEncryptWords(int numWords, Scanner scanner) {
        for (int i = 0; i < numWords; i++) {
            System.out.print("Next word: ");
            String word = scanner.next();
            String encryptedWord = encryptWord(word);
            System.out.println("\"" + word + "\" has been encrypted to: " + encryptedWord);
        }
    }
}