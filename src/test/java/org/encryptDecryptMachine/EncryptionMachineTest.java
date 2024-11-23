package org.encryptDecryptMachine;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionMachineTest {
    // Static string for the introductory message
    private static final String INTRO_MESSAGE =
        "Welcome to the CSCI717 Encryption Machine Construction\n" +
        "The program lets you encrypt a message\n" +
        "with a key for your recipient to decrypt!\n\n";
    private final EncryptionMachine encryptionMachine = new EncryptionMachine();

    @Test
    void testMainZeroWordsToEncrypt() {
        String input = "test\n0\n";
        String expectedOutput =
            "Enter key: " +
            "\"test\" has been encrypted to: whvw\n\n" +
            "How many words is your message?: \n" +
            "Message fully encrypted. Happy secret messaging!\n";
        testMainWithInput( input, expectedOutput);
    }

    @Test
    void testMainSingleLetterKeySingleWordMessage() {
        String input = "a\n1\nhello\n";
        String expectedOutput =
            "Enter key: " +
            "\"a\" has been encrypted to: d\n\n" +
            "How many words is your message?: " +
            "Next word: \"hello\" has been encrypted to: khoor\n\n" +
            "Message fully encrypted. Happy secret messaging!\n";
        testMainWithInput(input, expectedOutput);
    }

    @Test
    void testMainMultiLetterKeyMultiWordMessage() {
        String input = "csci\n9\nthe\nquick\nbrown\nfox\njumps\nover\nthe\nlazy\ndog\n";
        String expectedOutput =
            "Enter key: " +
            "\"csci\" has been encrypted to: fvfl\n\n" +
            "How many words is your message?: " +
            "Next word: \"the\" has been encrypted to: wkh\n" +
            "Next word: \"quick\" has been encrypted to: txlfn\n" +
            "Next word: \"brown\" has been encrypted to: eurzq\n" +
            "Next word: \"fox\" has been encrypted to: ira\n" +
            "Next word: \"jumps\" has been encrypted to: mxpsv\n" +
            "Next word: \"over\" has been encrypted to: ryhu\n" +
            "Next word: \"the\" has been encrypted to: wkh\n" +
            "Next word: \"lazy\" has been encrypted to: odcb\n" +
            "Next word: \"dog\" has been encrypted to: grj\n\n" +
            "Message fully encrypted. Happy secret messaging!\n";
        testMainWithInput(input, expectedOutput);
    }

    @Test
    void testMainRepeatingLetters() {
        String input = "wxx\n3\naaa\nbbb\nccc\n";
        String expectedOutput =
            "Enter key: " +
            "\"wxx\" has been encrypted to: zaa\n\n" +
            "How many words is your message?: " +
            "Next word: \"aaa\" has been encrypted to: ddd\n" +
            "Next word: \"bbb\" has been encrypted to: eee\n" +
            "Next word: \"ccc\" has been encrypted to: fff\n\n" +
            "Message fully encrypted. Happy secret messaging!\n";
        testMainWithInput(input, expectedOutput);
    }

    @Test
    void testMainAllLetters() {
        String input = "xyzabcdefghijklmnopqrstuvw\n1\nzyxwvutsrqponmlkjihgfedcba\n";
        String expectedOutput =
            "Enter key: " +
            "\"xyzabcdefghijklmnopqrstuvw\" has been encrypted to: abcdefghijklmnopqrstuvwxyz\n\n" +
            "How many words is your message?: " +
            "Next word: \"zyxwvutsrqponmlkjihgfedcba\" has been encrypted to: cbazyxwvutsrqponmlkjihgfed\n\n" +
            "Message fully encrypted. Happy secret messaging!\n";
        testMainWithInput(input, expectedOutput);
    }

    /**
     * Helper method to run the main method with a given input and assert the output.
     *
     * @param input          The input string to provide to System.in
     * @param expectedOutput The expected output string from System.out
     */
    private void testMainWithInput(String input, String expectedOutput) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Run the main method
        this.encryptionMachine.main(new String[]{});

        // Restore System.out
        System.setOut(originalSystemOut);

        // Assert that the output matches the expected output
        assertEquals(INTRO_MESSAGE + expectedOutput, outContent.toString());
    }
}