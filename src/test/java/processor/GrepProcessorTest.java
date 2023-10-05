package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.implementation.GrepProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class GrepProcessorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final String separator = System.lineSeparator();
    private static final String expectedOutput = "http://rhki.cz/cel/cumtyer/zrrvrcpeeivnsfgcr/fkcwdsigf/mgzyi/grkkueyszvugjux/qwcxugn/sj\ttext/html\t3585" + separator +
            "http://hpbkcthejwyihslxtoqumdpzdcyfyhwdxrpflkpqacnoehrrphqpbioqwk.cz/dtyiwneumjrdmzxtnrip/tibyj/qzvnhgkwl\ttext/html\t4332" + separator +
            "http://abuswukxodhfxgypzxzj.cz/iht/tiolmzyprrif/lqlqmqptypbzfjmtuv\tapplication/pdf\t810" + separator +
            "http://wosttwfeemijekgfmmwdjmz.cz/zfw/cdemmwvkvbeqgzb/vqlb/exaxhmha/l/x/gbjatjlrcw/snwui/smbyugemeebjwfsgcibp/dbzzf\ttext/html\t1591" + separator +
            "http://uehjrxkbrhwndtm.cz/wvpc/bvkwivxrksccvepvdnf/dnaevzqggpvqlxuacrjaarc/znzeui/dzlb/iggwydd/udvnodunxgndrsch/vltfaofkoat/q/zg/vzhznltxkyj\ttext/html\t3225" + separator +
            "http://pjhfqtd.cz/ldm/zsngcdyzh/on/alzwiurtewuoujhswlsrfnosjsrjfj/j\ttext/html\t2924";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    private Pattern pattern;

    @Test
    public void test_process_valid_urls_with_pattern_matches() throws InvalidFileFormatException {
        // Arrange
        String input = "http://rhki.cz/cel/cumtyer/zrrvrcpeeivnsfgcr/fkcwdsigf/mgzyi/grkkueyszvugjux/qwcxugn/sj\ttext/html\t3585\n" +
                "http://hpbkcthejwyihslxtoqumdpzdcyfyhwdxrpflkpqacnoehrrphqpbioqwk.cz/dtyiwneumjrdmzxtnrip/tibyj/qzvnhgkwl\ttext/html\t4332\n" +
                "http://abuswukxodhfxgypzxzj.cz/iht/tiolmzyprrif/lqlqmqptypbzfjmtuv\tapplication/pdf\t810\n" +
                "http://wosttwfeemijekgfmmwdjmz.cz/zfw/cdemmwvkvbeqgzb/vqlb/exaxhmha/l/x/gbjatjlrcw/snwui/smbyugemeebjwfsgcibp/dbzzf\ttext/html\t1591\n" +
                "http://uehjrxkbrhwndtm.cz/wvpc/bvkwivxrksccvepvdnf/dnaevzqggpvqlxuacrjaarc/znzeui/dzlb/iggwydd/udvnodunxgndrsch/vltfaofkoat/q/zg/vzhznltxkyj\ttext/html\t3225\n" +
                "http://pjhfqtd.cz/ldm/zsngcdyzh/on/alzwiurtewuoujhswlsrfnosjsrjfj/j\ttext/html\t2924";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String regex = "^http://[a-zA-Z0-9.-]+";
        GrepProcessor processor = new GrepProcessor(regex);

        // Act
        processor.process();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void test_process_valid_urls_no_pattern_matches() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\n" +
                "http://google.com\n" +
                "http://stackoverflow.com";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String regex = "invalid_regex";
        GrepProcessor processor = new GrepProcessor(regex);

        // Act
        processor.process();

        // Assert
        assertEquals("", outputStream.toString().trim());
    }

    @Test
    public void test_process_invalid_urls_catch_exceptions() throws InvalidFileFormatException {
        // Arrange
        String input = "http://example.com\n" +
                "invalid_url\n" +
                "http://google.com";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        String regex = "^http://[a-zA-Z0-9.-]+";
        GrepProcessor processor = new GrepProcessor(regex);

        // Act
        processor.process();

        // Assert
        assertEquals("Invalid input line: http://example.com" + separator +
                "Invalid input line: invalid_url" + separator +
                "Invalid input line: http://google.com", outputStream.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

}

