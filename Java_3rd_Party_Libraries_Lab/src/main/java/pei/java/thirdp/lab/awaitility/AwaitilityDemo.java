package pei.java.thirdp.lab.awaitility;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.reflect.exception.FieldNotFoundException;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.*;

import static org.awaitility.Duration.*;

import static org.assertj.core.api.Assertions.*;

/**
 * @author pei
 */
public class AwaitilityDemo {

    @Test
    public void testAvailability() throws Exception {

        assertThatThrownBy(() -> await().atMost(ONE_SECOND).untilTrue(new AtomicBoolean(false)))
                .isInstanceOf((ConditionTimeoutException.class));

        assertThatThrownBy(() -> await().atMost(ONE_SECOND).until(() -> 0 == 1))
                .isInstanceOf(ConditionTimeoutException.class);

        await().atMost(ONE_SECOND).until(fieldIn(DataClass.class).ofType(int.class).andWithName("classField"),
                equalTo(0));
        assertThatThrownBy(() -> await().atMost(ONE_SECOND)
                .until(fieldIn(DataClass.class).ofType(int.class).andWithName("classField"), equalTo(1)))
                        .isInstanceOf((ConditionTimeoutException.class));

        await().atMost(ONE_SECOND).until(fieldIn(new DataClass()).ofType(int.class).andWithName("instanceField"),
                equalTo(0));
        assertThatThrownBy(() -> await().atMost(ONE_SECOND)
                .until(fieldIn(new DataClass()).ofType(int.class).andWithName("instanceField"), equalTo(1)))
                        .isInstanceOf(ConditionTimeoutException.class);

        assertThatThrownBy(() -> await().atMost(ONE_SECOND)
                .until(fieldIn(new DataClass()).ofType(int.class).andWithName("notExistField"), equalTo(1)))
                        .isInstanceOf((FieldNotFoundException.class));

    }

    public class DataClass {

        static final int classField = 0;
        int instanceField;
    }

}
