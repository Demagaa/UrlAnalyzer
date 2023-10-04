package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.GrepProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GrepProcessorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final String separator = System.lineSeparator();
    private static final String fileName = "./data/testData.txt";
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

//    @Test
//    public void testRunGrepProcessor() throws InvalidFileFormatException {
//
//        // Define the regular expression to search for
//        String regex = "^http://[a-zA-Z0-9.-]+"; // Example regex
//
//        // Call the function to be tested
//        GrepProcessor.runGrepProcessor(regex);
//
//        assertEquals(expectedOutput, outContent.toString().trim());
//    }

    // Clean up: Restore System.out to the original value
    @After
    public void tearDown() {
        System.setOut(System.out);
    }

}

