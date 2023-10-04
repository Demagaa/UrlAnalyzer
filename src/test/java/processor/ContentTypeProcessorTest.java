package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.ContentTypeProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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

//    @Test
//    public void testRunContentTypeProcessor() throws InvalidFileFormatException {
//
//        // Call the function to be tested
//        ContentTypeProcessor.runContentTypeProcessor();
//
//        assertEquals(expectedOutput, outContent.toString().trim());
//    }

    // Clean up: Restore System.out to the original value
    @After
    public void tearDown() {
        System.setOut(System.out);
    }
}
