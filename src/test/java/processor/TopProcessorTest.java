package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.implementation.TopProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TopProcessorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final String separator = System.lineSeparator();
    private static final String expectedOutput =
            "http://hpbkcthejwyihslxtoqumdpzdcyfyhwdxrpflkpqacnoehrrphqpbioqwk.cz/dtyiwneumjrdmzxtnrip/tibyj/qzvnhgkwl\t4332" + separator +
                    "http://rhki.cz/cel/cumtyer/zrrvrcpeeivnsfgcr/fkcwdsigf/mgzyi/grkkueyszvugjux/qwcxugn/sj\t3585" + separator +
                    "http://uehjrxkbrhwndtm.cz/wvpc/bvkwivxrksccvepvdnf/dnaevzqggpvqlxuacrjaarc/znzeui/dzlb/iggwydd/udvnodunxgndrsch/vltfaofkoat/q/zg/vzhznltxkyj\t3225" + separator +
                    "http://pjhfqtd.cz/ldm/zsngcdyzh/on/alzwiurtewuoujhswlsrfnosjsrjfj/j\t2924" + separator +
                    "http://wosttwfeemijekgfmmwdjmz.cz/zfw/cdemmwvkvbeqgzb/vqlb/exaxhmha/l/x/gbjatjlrcw/snwui/smbyugemeebjwfsgcibp/dbzzf\t1591" + separator +
                    "http://abuswukxodhfxgypzxzj.cz/iht/tiolmzyprrif/lqlqmqptypbzfjmtuv\t810";


    @Before
    public void setUp() {
        // Redirect System.out to outputStream to capture printed output
        System.setOut(new PrintStream(outputStream));
    }

    // Process valid input lines and output top 10 URLs by click count
    @Test
    public void test_process_valid_input_lines_and_output_top_10_urls_by_click_count() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\t100\n" +
                "http://example.org\ttext/html\t200\n" +
                "http://example.net\ttext/html\t300\n" +
                "http://example.com\ttext/html\t400\n" +
                "http://example.org\ttext/html\t500\n" +
                "http://example.net\ttext/html\t600\n" +
                "http://example.com\ttext/html\t700\n" +
                "http://example.org\ttext/html\t800\n" +
                "http://example.net\ttext/html\t900\n" +
                "http://example.com\ttext/html\t1000\n" +
                "http://example.org\ttext/html\t1100\n" +
                "http://example.net\ttext/html\t1200\n" +
                "http://example.com\ttext/html\t1300\n" +
                "http://example.org\ttext/html\t1400\n" +
                "http://example.net\ttext/html\t1500\n" +
                "http://example.com\ttext/html\t1600\n" +
                "http://example.org\ttext/html\t1700\n" +
                "http://example.net\ttext/html\t1800\n" +
                "http://example.com\ttext/html\t1900\n" +
                "http://example.org\ttext/html\t2000\n" +
                "http://example.net\ttext/html\t2100\n" +
                "http://example.com\ttext/html\t2200\n" +
                "http://example.org\ttext/html\t2300\n" +
                "http://example.net\ttext/html\t2400\n" +
                "http://example.com\ttext/html\t2500\n" +
                "http://example.org\ttext/html\t2600\n" +
                "http://example.net\ttext/html\t2700\n" +
                "http://example.com\ttext/html\t2800\n" +
                "http://example.org\ttext/html\t2900\n" +
                "http://example.net\ttext/html\t3000\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        TopProcessor processor = new TopProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "http://example.com\t10000\n" +
                "http://example.org\t9500\n" +
                "http://example.net\t9000";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }    // Clean up: Restore System.out to the original value

    // Process input lines with URLs that have the same click count and output top 10 URLs by click count
    @Test
    public void test_process_input_lines_with_urls_that_have_the_same_click_count_and_output_top_10_urls_by_click_count() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\t100\n" +
                "http://example.org\ttext/html\t200\n" +
                "http://example.net\ttext/html\t300\n" +
                "http://example.com\ttext/html\t400\n" +
                "http://example.org\ttext/html\t500\n" +
                "http://example.net\ttext/html\t600\n" +
                "http://example.com\ttext/html\t700\n" +
                "http://example.org\ttext/html\t800\n" +
                "http://example.net\ttext/html\t900\n" +
                "http://example.com\ttext/html\t1000\n" +
                "http://example.org\ttext/html\t1100\n" +
                "http://example.net\ttext/html\t1200\n" +
                "http://example.com\ttext/html\t1300\n" +
                "http://example.org\ttext/html\t1400\n" +
                "http://example.net\ttext/html\t1500\n" +
                "http://example.com\ttext/html\t1600\n" +
                "http://example.org\ttext/html\t1700\n" +
                "http://example.net\ttext/html\t1800\n" +
                "http://example.com\ttext/html\t1900\n" +
                "http://example.org\ttext/html\t2000\n" +
                "http://example.net\ttext/html\t2100\n" +
                "http://example.com\ttext/html\t2200\n" +
                "http://example.org\ttext/html\t2300\n" +
                "http://example.net\ttext/html\t2400\n" +
                "http://example.com\ttext/html\t2500\n" +
                "http://example.org\ttext/html\t2600\n" +
                "http://example.net\ttext/html\t2700\n" +
                "http://example.com\ttext/html\t2800\n" +
                "http://example.org\ttext/html\t2900\n" +
                "http://example.net\ttext/html\t3000\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        TopProcessor processor = new TopProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "http://example.com\t10000\n" +
                "http://example.org\t9500\n" +
                "http://example.net\t9000";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }    // Clean up: Restore System.out to the original value

    // Process input lines with URLs that have less than 10 unique click counts and output top 10 URLs by click count
    @Test
    public void test_process_input_lines_with_urls_that_have_less_than_10_unique_click_counts_and_output_top_10_urls_by_click_count()
            throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\ttext/html\t100\n" +
                "http://example.org\ttext/html\t200\n" +
                "http://example.net\ttext/html\t300\n" +
                "http://example.com\ttext/html\t400\n" +
                "http://example.org\ttext/html\t500\n" +
                "http://example.net\ttext/html\t600\n" +
                "http://example.com\ttext/html\t700\n" +
                "http://example.org\ttext/html\t800\n" +
                "http://example.net\ttext/html\t900\n" +
                "http://example.com\ttext/html\t1000\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        TopProcessor processor = new TopProcessor();

        // Act
        processor.process();

        // Assert
        String expectedOutput = "http://example.com\t4000\n" +
                "http://example.org\t3500\n" +
                "http://example.net\t3000";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Clean up: Restore System.out to the original value
    @After
    public void tearDown() {
        System.setOut(System.out);
    }
}
