package edu.gatech.seclass.adjusttxt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.io.TempDir;

@Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class MyMainTest {
    // Place all of your tests in this class, optionally using MainTest.java as an
    // example
    private final String usageStr = "Usage: adjusttxt [ -s number | -w spacing | -x | -r target | -p prefix ] FILE"
            + System.lineSeparator();

    @TempDir
    Path tempDirectory;

    @RegisterExtension
    OutputCapture capture = new OutputCapture();

    /* ----------------------------- Test Utilities ----------------------------- */

    /**
     * Returns path of a new "input.txt" file with specified contents written into
     * it. The file will
     * be created using {@link TempDir TempDir}, so it is automatically deleted
     * after test
     * execution.
     *
     * @param contents the text to include in the file
     * @return a Path to the newly written file, or null if there was an issue
     *         creating the file
     */
    private Path createFile(String contents) {
        return createFile(contents, "input.txt");
    }

    /**
     * Returns path to newly created file with specified contents written into it.
     * The file will be
     * created using {@link TempDir TempDir}, so it is automatically deleted after
     * test execution.
     *
     * @param contents the text to include in the file
     * @param fileName the desired name for the file to be created
     * @return a Path to the newly written file, or null if there was an issue
     *         creating the file
     */
    private Path createFile(String contents, String fileName) {
        Path file = tempDirectory.resolve(fileName);
        try {
            Files.writeString(file, contents);
        } catch (IOException e) {
            return null;
        }

        return file;
    }

    /**
     * Takes the path to some file and returns the contents within.
     *
     * @param file the path to some file
     * @return the contents of the file as a String, or null if there was an issue
     *         reading the file
     */
    private String getFileContent(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ------------------------------- Test Cases ------------------------------- */
    // Frame #: 1
    @Test
    public void adjusttxtTest1() {
        String input = "Contents";
        String nonValidParam = "its-non-valid";

        Path inputFile = createFile(input);
        String[] args = { "-s", nonValidParam, inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 2
    @Test
    public void adjusttxtTest2() {
        String input = "Contents";

        Path inputFile = createFile(input);
        String[] args = { "-s", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 3
    @Test
    public void adjusttxtTest3() {
        String input = "Contents";
        String nonValidParam = "its-non-valid";

        Path inputFile = createFile(input);
        String[] args = { "-w", nonValidParam, inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 4
    @Test
    public void adjusttxtTest4() {
        String input = "Contents";

        Path inputFile = createFile(input);
        String[] args = { "-w", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 5
    @Test
    public void adjusttxtTest5() {
        String input = "Contents";

        Path inputFile = createFile(input);
        String[] args = { "-w", "leading", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 6
    @Test
    public void adjusttxtTest6() {
        String input = "Contents";
        String nonValidParam = "its-non-valid";

        Path inputFile = createFile(input);
        String[] args = { "-r", nonValidParam, inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 7
    @Test
    public void adjusttxtTest7() {
        String input = "Contents";

        Path inputFile = createFile(input);
        String[] args = { "-r", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 8
    @Test
    public void adjusttxtTest8() {
        String input = "Contents";

        Path inputFile = createFile(input);
        String[] args = { "-p", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 9
    @Test
    public void adjusttxtTest9() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 10
    @Test
    public void adjusttxtTest10() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-w", "leading", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 11
    @Test
    public void adjusttxtTest11() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-s", "1", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 12
    @Test
    public void adjusttxtTest12() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 13
    @Test
    public void adjusttxtTest13() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-p", "prefix", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 14
    @Test
    public void adjusttxtTest14() {
        String inputWithoutLines = "";

        Path inputFile = createFile(inputWithoutLines);
        String[] args = { "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(inputWithoutLines, getFileContent(inputFile));
    }

    // Frame #: 15
    @Test
    public void adjusttxtTest15() {
        String input = "This is the single line for skip line option";

        Path inputFile = createFile(input);
        String[] args = { "-s", "1", inputFile.toString() };
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 16
    @Test
    public void adjusttxtTest16() {
        String input = """
                This is the first line of the text.
                And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> And this is the second line of the same text.
                prefix> A third line it is of same very text.
                """;
        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 17
    @Test
    public void adjusttxtTest17() {
        String input = """
                This is the first line of the text.
                And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. same the of line second the is this And
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 18
    @Test
    public void adjusttxtTest18() {
        String input = """
                This is the first line of the text.
                And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 19
    @Test
    public void adjusttxtTest19() {
        String input = """
                This is the first line of the text.

                And this is the second line of the same text.

                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> And this is the second line of the same text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 20
    @Test
    public void adjusttxtTest20() {
        String input = """
                This is the first line of the text.

                And this is the second line of the same text.

                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. same the of line second the is this And
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-r", "words", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 21
    @Test
    public void adjusttxtTest21() {
        String input = """
                This is the first line of the text.

                And this is the second line of the same text.

                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-r", "text", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 22
    @Test
    public void adjusttxtTest22() {
        String input = """
                This is the first line of the text.
                    And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> And this is the second line of the same text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "leading", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 23
    @Test
    public void adjusttxtTest23() {
        String input = """
                This is the first line of the text.
                    And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. same the of line second the is this And
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "leading", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 24
    @Test
    public void adjusttxtTest24() {
        String input = """
                This is the first line of the text.
                    And this is the second line of the same text.
                A third line it is of same very text.
                """;

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "leading", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 25
    @Test
    public void adjusttxtTest25() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> And this is the second line of the same text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "trailing", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 26
    @Test
    public void adjusttxtTest26() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. same the of line second the is this And
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "trailing", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 27
    @Test
    public void adjusttxtTest27() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "trailing", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 28
    @Test
    public void adjusttxtTest28() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "   A third line it is of same very text.";

        String expectedOutput = """
                prefix> Thisisthefirstlineofthetext.
                prefix> Andthisisthesecondlineofthesametext.
                prefix> Athirdlineitisofsameverytext.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "all", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 29
    @Test
    public void adjusttxtTest29() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "   A third line it is of same very text.";

        String expectedOutput = """
                prefix> text.theoflinefirsttheisThis
                prefix> text.sametheoflinesecondtheisthisAnd
                prefix> text.verysameofisitlinethirdA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "all", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 30
    @Test
    public void adjusttxtTest30() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "   A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txetehtfoeniltsrifehtsisihT
                prefix> .txetemasehtfoenildnocesehtsisihtdnA
                prefix> .txetyrevemasfositienildrihtA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-w", "all", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 31
    @Test
    public void adjusttxtTest31() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 32
    @Test
    public void adjusttxtTest32() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 33
    @Test
    public void adjusttxtTest33() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 34
    @Test
    public void adjusttxtTest34() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> And this is the second line of the same text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 35
    @Test
    public void adjusttxtTest35() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. same the of line second the is this And
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-x", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 36
    @Test
    public void adjusttxtTest36() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-x", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 37
    @Test
    public void adjusttxtTest37() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "leading", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 38
    @Test
    public void adjusttxtTest38() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "leading", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 39
    @Test
    public void adjusttxtTest39() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "leading", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 40
    @Test
    public void adjusttxtTest40() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> This is the first line of the text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "trailing", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 41
    @Test
    public void adjusttxtTest41() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. the of line first the is This
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "trailing", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 42
    @Test
    public void adjusttxtTest42() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet eht fo enil tsrif eht si sihT
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "trailing", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 43
    @Test
    public void adjusttxtTest43() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                " And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> Thisisthefirstlineofthetext.
                prefix> Athirdlineitisofsameverytext.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "all", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 44
    @Test
    public void adjusttxtTest44() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                " And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text.theoflinefirsttheisThis
                prefix> text.verysameofisitlinethirdA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "all", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 45
    @Test
    public void adjusttxtTest45() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                " And this is the second line of the same text.  " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txetehtfoeniltsrifehtsisihT
                prefix> .txetyrevemasfositienildrihtA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "0", "-w", "all", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 46
    @Test
    public void adjusttxtTest46() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> And this is the second line of the same text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 47
    @Test
    public void adjusttxtTest47() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. same the of line second the is this And
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 48
    @Test
    public void adjusttxtTest48() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 49
    @Test
    public void adjusttxtTest49() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> And this is the second line of the same text.
                prefix> A third line it is of same very text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-x", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 50
    @Test
    public void adjusttxtTest50() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. same the of line second the is this And
                prefix> text. very same of is it line third A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-x", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 51
    @Test
    public void adjusttxtTest51() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text." + System.lineSeparator() +
                System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                prefix> .txet yrev emas fo si ti enil driht A
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-x", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 52
    @Test
    public void adjusttxtTest52() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> And this is the second line of the same text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "leading", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 53
    @Test
    public void adjusttxtTest53() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. same the of line second the is this And
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "leading", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 54
    @Test
    public void adjusttxtTest54() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text." + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "leading", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 55
    @Test
    public void adjusttxtTest55() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> And this is the second line of the same text.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "trailing", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 56
    @Test
    public void adjusttxtTest56() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text. same the of line second the is this And
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "trailing", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 57
    @Test
    public void adjusttxtTest57() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txet emas eht fo enil dnoces eht si siht dnA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "trailing", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 58
    @Test
    public void adjusttxtTest58() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> Andthisisthesecondlineofthesametext.
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "all", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 59
    @Test
    public void adjusttxtTest59() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> text.sametheoflinesecondtheisthisAnd
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "all", "-r", "words", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 60
    @Test
    public void adjusttxtTest60() {
        String input = "This is the first line of the text." + System.lineSeparator() +
                "   And this is the second line of the same text.   " + System.lineSeparator() +
                "A third line it is of same very text.";

        String expectedOutput = """
                prefix> .txetemasehtfoenildnocesehtsisihtdnA
                """;

        Path inputFile = createFile(input);
        String[] args = { "-p", "prefix> ", "-s", "1", "-w", "all", "-r", "text", inputFile.toString() };
        Main.main(args);

        Assertions.assertEquals(expectedOutput, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }
}
