package processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.TopProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TopProcessorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final String fileName = "./data/testData.txt";
    private static final String separator = System.lineSeparator();
    private static final String expectedOutput =
            "http://hpbkcthejwyihslxtoqumdpzdcyfyhwdxrpflkpqacnoehrrphqpbioqwk.cz/dtyiwneumjrdmzxtnrip/tibyj/qzvnhgkwl\t4332" + separator +
                    "http://rhki.cz/cel/cumtyer/zrrvrcpeeivnsfgcr/fkcwdsigf/mgzyi/grkkueyszvugjux/qwcxugn/sj\t3585" + separator +
                    "http://uehjrxkbrhwndtm.cz/wvpc/bvkwivxrksccvepvdnf/dnaevzqggpvqlxuacrjaarc/znzeui/dzlb/iggwydd/udvnodunxgndrsch/vltfaofkoat/q/zg/vzhznltxkyj\t3225"+ separator +
                    "http://pjhfqtd.cz/ldm/zsngcdyzh/on/alzwiurtewuoujhswlsrfnosjsrjfj/j\t2924" + separator +
                    "http://wosttwfeemijekgfmmwdjmz.cz/zfw/cdemmwvkvbeqgzb/vqlb/exaxhmha/l/x/gbjatjlrcw/snwui/smbyugemeebjwfsgcibp/dbzzf\t1591" + separator +
                    "http://abuswukxodhfxgypzxzj.cz/iht/tiolmzyprrif/lqlqmqptypbzfjmtuv\t810";


    @Before
    public void setUp() {
        // Redirect System.out to outputStream to capture printed output
        System.setOut(new PrintStream(outputStream));
    }

//    @Test
//    public void testRunTopProcessor() throws InvalidFileFormatException {
//        // Run the processor
//        TopProcessor.runTopProcessor();
//
//        // Capture the printed output
//        String printedOutput = outputStream.toString();
//
//        // Assert the expected output
//        assertEquals("Expected top 10 URLs output", expectedOutput, printedOutput.trim());
//    }

    // Clean up: Restore System.out to the original value
    @After
    public void tearDown() {
        System.setOut(System.out);
    }
}
