package cz.seznam.fulltext.robot;


import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.exception.UnsupportedProcessingTypeException;
import cz.seznam.fulltext.robot.processor.implementation.ContentTypeProcessor;
import cz.seznam.fulltext.robot.processor.implementation.GrepProcessor;
import cz.seznam.fulltext.robot.processor.enums.ProcessorType;
import cz.seznam.fulltext.robot.processor.implementation.TopProcessor;
import cz.seznam.fulltext.robot.processor.api.Processor;

/**
 * Your task is to implement the following application to the best of your abilities:
 *
 * <p>|The application reads lines from standard input, processes them using one of the supported
 * processors, and writes the results to standard output.
 *
 * <p>Lines read from standard input must have the following format to be considered valid:
 *
 * <pre>
 * &lt;url&gt;\t&lt;content-type&gt;\t&lt;click count&gt;
 * </pre>
 *
 * <p>That is, a valid input line contains three values separated by a TAB character, where
 *
 * <ul>
 *   <li><code>url</code> is a string representing a URL (no need to validate whether the string is
 *       actually a valid URL for the sake of this exercise),
 *   <li><code>content-type</code> is a string representing a HTTP content type (again, no need to
 *       validate whether the string represents a valid content type),
 *   <li><code>click-count</code> is a positive integer representing number of times a particular
 *       URL was clicked on (e.g. in search engine results).
 * </ul>
 *
 * <p>An example of one input line:
 *
 * <pre>
 * https://seznam.cz\ttext/html\t100
 * </pre>
 *
 * <p>You can assume that no URL will appear more than once in the input.
 *
 * <p>The application should be invoked as follows (this example assumes unix-like shell is used):
 *
 * <pre>
 * $ cat input.txt | java -classpath . cz.seznam.fulltext.robot.Runner &lt;className&gt; [&lt;processor parameters&gt;]
 * </pre>
 *
 * <p>Structure of the application's output depends on the processor <code>className</code> used:
 *
 * <ul>
 *   <li><code>Top</code> - outputs top 10 URLs with highest click count, in the form
 *       &lt;url&gt;\t&lt;click-count&gt;</code> (where click-count is a positive integer), sorted
 *       from highest click count to lowest click count
 *   <li><code>ContentType</code> - for each content type mentioned in the input, outputs a number
 *       of URLs having that content type, using the form <code>
 *       &lt;content-type&gt;\t&lt;url-count&gt;</code> (where url-count is a positive integer),
 *       sorted alphabetically by content type
 *   <li><code>Grep</code> - outputs all lines in the input that match given regular expression
 *       (this processor requires one parameter on the command line - a regular expression)
 * </ul>
 *
 * <p>The skeleton code below is here to get you started. Feel free to re-design the code as you see
 * fit.
 *
 * <p>Make sure the resulting code is easily testable, and if time permits, write a couple of tests
 * using a test automation tool of your choice.
 *
 * <p>Try to minimize the application's memory footprint and CPU usage.
 */

/**
 * Command used for test on Windows environment:
 * Get-Content .\data\10000.txt | java -classpath ".\target\classes" cz.seznam.fulltext.robot.Runner
 */
public class Runner {
    private static String regex;

    public static void main(String[] args) throws UnsupportedProcessingTypeException, InvalidFileFormatException {
        checkArgs(args);
        String className = args[0];
        String processorTypeStr = className.toUpperCase();
        ProcessorType processorType = ProcessorType.valueOf(processorTypeStr);
        process(processorType);
    }

    static void checkArgs(String[] args) throws UnsupportedProcessingTypeException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please specify mod type as parameter");
        } else if (args[0].equals("Grep")) {
            if (args.length < 2) {
                throw new IllegalArgumentException("Please specify regular expression");
            } else {
                regex = args[1];
            }
        } else if (!(args[0].equals("Top") || args[0].equals("ContentType"))) {
            throw new UnsupportedProcessingTypeException("Unsupported processing type: " + args[0]);
        }
    }

    /**
     * Read lines from standard input and process each using given processor.
     */
    static void process(ProcessorType processorType) throws InvalidFileFormatException {
        Processor processor;

        switch (processorType) {
            case TOP:
                processor = new TopProcessor();
                break;
            case CONTENT_TYPE:
                processor = new ContentTypeProcessor();
                break;
            case GREP:
                processor = new GrepProcessor(regex);
                break;
            default:
                System.out.println("Invalid processor type.");
                return;
        }

        // Process the data
        processor.process();
    }
}

