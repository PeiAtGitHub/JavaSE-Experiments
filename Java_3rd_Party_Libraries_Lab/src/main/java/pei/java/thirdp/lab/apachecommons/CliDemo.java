package pei.java.thirdp.lab.apachecommons;


import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class CliDemo {
	
	private static Options options = null;
	
	private DefaultParser defaultParser = null;
	
	@Before
	public void onceBeforeMethod() {
		defaultParser = new DefaultParser();
	}
	
	@BeforeClass
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
		//
		assertTrue(cmd.hasOption("a"));
		assertNull(cmd.getOptionValue("a"));

		assertTrue(cmd.hasOption("b"));
		assertThat(cmd.getOptionValue("b"),is("A1"));
		assertThat(cmd.getOptionValues("b")[1],is("A2"));
		assertThat(cmd.getOptionValues("b").length, is(2));

		assertTrue(cmd.hasOption("f"));
		assertThat(cmd.getOptionValue("f"),is("A3"));
		assertThat(cmd.getOptionValues("f").length, is(1));
		
		assertTrue(cmd.hasOption("t"));
		assertThat(cmd.getOptionValue("t"),is("A5"));
		assertThat(cmd.getOptionValues("t").length,is(1));
		
		assertTrue(cmd.hasOption("T"));
		assertThat(cmd.getOptionValues("T")[0],is("A7"));
		assertThat(cmd.getOptionValues("T")[1],is("A8"));
		assertThat(cmd.getOptionValues("T").length,is(2));
		
		assertTrue(cmd.hasOption("h"));
		assertTrue(cmd.hasOption("help"));
		assertFalse(cmd.hasOption("H")); 
		assertThat(cmd.getOptionValue("h", "h default value"), is("h default value"));
				
		assertTrue(cmd.hasOption("r"));
		assertTrue(cmd.hasOption("required"));
		assertNull(cmd.getOptionValue("r"));
		
		//
		assertThat(cmd.getArgList().toString(), is("[A0, A4, A6, A9]"));

		//
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("ApacheCliDemo", options, true);
		System.out.println();
		helpFormatter.printHelp("ApacheCliDemo", options, false);
	}
	
	
	@Test(expected = UnrecognizedOptionException.class)
	public void testWrongOptions() throws ParseException {
		defaultParser.parse(options, commandlineToArray("-A -t hahaha -T HAHAHA -h --required"));
	}
	

	@Test(expected = MissingOptionException.class)
	public void testMissingRequiredOptions() throws ParseException {
		defaultParser.parse(options, commandlineToArray("-a -h"));
	}

	
	@Test(expected = MissingArgumentException.class)
	public void testMissingRequiredOptionArgs() throws ParseException {
//		defaultParser.parse(options, commandlineToArray("-t -r"));
		
//		defaultParser.parse(options, commandlineToArray("-f A1 -r"));
		
		defaultParser.parse(options, commandlineToArray("-T -r"));

	}
	
	
	/*
	 * This test util method mocks the OS platform functionality that 
	 * convert command line string into args arrays.
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