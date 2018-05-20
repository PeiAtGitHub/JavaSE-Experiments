package pei.java.jse.lab.language;

import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

import org.junit.Test;

import com.github.peiatgithub.java.utils.RunFlag;

import static org.assertj.core.api.Assertions.*;

/**
 * 
 * @author pei
 *
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
			assertThat(RunFlag.runTimes()).isEqualTo(0);
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
		
		assertThat(RunFlag.runTimes()).isEqualTo(5);
		
	}

}
