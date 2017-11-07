package pei.java.jse.experiments.language;

import java.util.Objects;

/**
 * 
 * @author Pei
 *
 */
public class ObjectsUtils {

	public static void main(String[] args) {

		Object sth = null;
		// Null checking made one liner.
		Objects.requireNonNull(sth, "Sth is null!");

	}

}
