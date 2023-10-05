package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.implementation.ContentTypeProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ContentTypeProcessorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final String fileName = "./data/testData.txt";
    private static final String separator = System.lineSeparator();
    private static final String expectedOutput =
            "application/pdf\t1" + separator +
                    "text/html\t5";


    @Before
    public void setUpStreams() {
        // Redirect System.out to outputStream to capture printed output
        System.setOut(new PrintStream(outContent));
    }

    // Process multiple lines of input and output content type counts in alphabetical order
    @Test
    public void test_process_multiple_lines_of_input_and_output_content_type_counts_in_alphabetical_order() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\n" +
                "http://example.org\tapplication/json\n" +
                "http://example.net\ttext/plain\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "application/json\t1\n" +
                "text/html\t1\n" +
                "text/plain\t1";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Process input with different content types and output correct counts
    @Test
    public void test_process_input_with_different_content_types_and_output_correct_counts() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\n" +
                "http://example.org\tapplication/json\n" +
                "http://example.net\ttext/html\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "application/json\t1\n" +
                "text/html\t2";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Process input with same content types and output correct counts
    @Test
    public void test_process_input_with_same_content_types_and_output_correct_counts() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\n" +
                "http://example.org\ttext/html\n" +
                "http://example.net\ttext/html\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "text/html\t3";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Process empty input and output empty content type counts
    @Test
    public void test_process_empty_input_and_output_empty_content_type_counts() throws InvalidFileFormatException {
        // Arrange
        String input = "";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Process input with invalid URL format and throw InvalidFileFormatException
    @Test
    public void test_process_input_with_invalid_URL_format_and_throw_InvalidFileFormatException() {
        // Arrange
        String input = "http://example.com\ttext/html\n" +
                "invalid_url\tapplication/json\n" +
                "http://example.net\ttext/plain\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act & Assert
        assertThrows(InvalidFileFormatException.class, () -> {
            processor.process();
        });
    }

    // Process input with missing content type and output correct counts with null content type
    @Test
    public void test_process_input_with_missing_content_type_and_output_correct_counts_with_null_content_type()
            throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\n" +
                "http://example.org\n" +
                "http://example.net\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ContentTypeProcessor processor = new ContentTypeProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "null\t3";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Clean up: Restore System.out to the original value
    @After
    public void tearDown() {
        System.setOut(System.out);
    }
}
