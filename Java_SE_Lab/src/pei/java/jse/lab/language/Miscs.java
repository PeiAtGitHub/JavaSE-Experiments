package pei.java.jse.lab.language;

import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

import com.github.peiatgithub.java.utils.RunFlag;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class Miscs {

	@Test
	public void switchCaseFallThru() throws Exception {
		
		final int key = 3;
		RunFlag.reset();
		
		switch (key) {
		case 1:
			fail(CODE_SHOULD_NOT_REACH_HERE);
		case 2:
			fail(CODE_SHOULD_NOT_REACH_HERE);
		case 3:
		    assertEquals(0, RunFlag.runTimes());
			RunFlag.run();
		case 4:
			RunFlag.run();
		case 5:
			RunFlag.run();
		case 6:
			RunFlag.run();
		default:
			RunFlag.run();
		}
		
		assertEquals(5, RunFlag.runTimes());
		
	}

}
