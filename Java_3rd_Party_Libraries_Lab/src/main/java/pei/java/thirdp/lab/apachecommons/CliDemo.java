package pei.java.thirdp.lab.apachecommons;

import static org.apache.commons.lang3.StringUtils.SPACE;

import org.apache.commons.cli.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author pei
 */
public class CliDemo {

    private static Options options = null;

    private DefaultParser defaultParser = null;

    @BeforeEach
    public void onceBeforeMethod() {
        defaultParser = new DefaultParser();
    }

    @BeforeAll
    public static void onceBeforeClass() {

        options = new Options();

        Option optLowerA = new Option("a", false, "I am 'a' option, NO arg");
        optLowerA.setArgName("a Arg Name");
        options.addOption(optLowerA);

        Option optLowerB = new Option("b", true, "I am 'b' option, requires 2 arg");
        optLowerB.setArgs(2);
        options.addOption(optLowerB);

        Option optLowerF = new Option("f", true, "I am 'f' option, requires 2 args");
        optLowerF.setArgs(2);
        options.addOption(optLowerF);

        options.addOption("t", true, "I am 't' option, requires arg");

        Option optUpperT = new Option("T", true, "I am 'T' option, requires arg");
        optUpperT.setArgName("T Arg Name");
        optUpperT.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(optUpperT);

        options.addOption("h", "help", false, "I am 'help' option, NO arg");
        options.addRequiredOption("r", "required", false, "I am the ONLY REQUIRED OPTION here, NO arg");
    }

    @Test
    public void testAllOptions() throws Exception {
        CommandLine cmd = defaultParser.parse(options,
                commandlineToArray("-a A0 -b A1 A2 -fA3 A4 -t A5 A6 -T A7 A8 -h --required A9"));

        assertTrue(cmd.hasOption("a"));
        assertNull(cmd.getOptionValue("a"));

        assertTrue(cmd.hasOption("b"));
        assertEquals("A1", cmd.getOptionValue("b"));
        assertThat(cmd.getOptionValues("b")).hasSize(2).contains("A2", atIndex(1));

        assertTrue(cmd.hasOption("f"));
        assertEquals("A3", cmd.getOptionValue("f"));
        assertThat(cmd.getOptionValues("f")).hasSize(1);

        assertTrue(cmd.hasOption("t"));
        assertEquals("A5", cmd.getOptionValue("t"));
        assertThat(cmd.getOptionValues("t")).hasSize(1);

        assertTrue(cmd.hasOption("T"));
        assertThat(cmd.getOptionValues("T")).hasSize(2).contains("A7", atIndex(0)).contains("A8", atIndex(1));

        assertTrue(cmd.hasOption("h"));
        assertTrue(cmd.hasOption("help"));
        assertFalse(cmd.hasOption("H"));
        assertEquals("h default value", cmd.getOptionValue("h", "h default value"));

        assertTrue(cmd.hasOption("r"));
        assertTrue(cmd.hasOption("required"));
        assertNull(cmd.getOptionValue("r"));

        assertThat(cmd.getArgList()).containsExactly("A0", "A4", "A6", "A9");

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("ApacheCliDemo", options, true);
        System.out.println();
        helpFormatter.printHelp("ApacheCliDemo", options, false);
    }

    @Test
    public void testWrongOptions() throws ParseException {
        assertThatThrownBy(
                () -> defaultParser.parse(options, commandlineToArray("-A -t hahaha -T HAHAHA -h --required")))
                        .isInstanceOf(UnrecognizedOptionException.class);
    }

    @Test
    public void testMissingRequiredOptions() throws ParseException {
        assertThatThrownBy(() -> defaultParser.parse(options, commandlineToArray("-a -h")))
                .isInstanceOf(MissingOptionException.class);
    }

    @Test
    public void testMissingRequiredOptionArgs() throws ParseException {

        assertThatThrownBy(() -> defaultParser.parse(options, commandlineToArray("-t -r")))
                .isInstanceOf(MissingArgumentException.class);

        assertThatThrownBy(() -> defaultParser.parse(options, commandlineToArray("-f A1 -r")))
                .isInstanceOf(MissingArgumentException.class);

        assertThatThrownBy(() -> defaultParser.parse(options, commandlineToArray("-T -r")))
                .isInstanceOf(MissingArgumentException.class);

    }

    /*
     * This test util method mocks the OS platform functionality that convert
     * command line string into args arrays.
     * 
     * This mock for now is very basic that it just split the input string by space,
     * it does not handle quotes, glob, and etc like the OS platform does.
     * 
     * 
     */
    private String[] commandlineToArray(String cmdLineString) {
        return cmdLineString.split(SPACE);
    }

}