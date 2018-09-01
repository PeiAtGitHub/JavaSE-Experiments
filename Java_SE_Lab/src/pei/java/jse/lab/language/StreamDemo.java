package pei.java.jse.lab.language;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static com.github.peiatgithub.java.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author pei
 */
public class StreamDemo {

    private static final List<String> STR_LIST = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "", "jkl");

    @Test
    void stream() throws Exception {

        assertThat(STR_LIST.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList())).containsExactly("abc",
                "bc", "efg", "abcd", "jkl");

        // Random stream
        new Random().ints(3, 0, 100).forEach(i -> assertThat(i).isBetween(0, 99));
        // with limit
        new Random().ints(0, 100).limit(3).forEach(i -> assertThat(i).isGreaterThanOrEqualTo(0).isLessThan(100));

        System.out.println("Sorted randome number: ");
        new Random().ints(3, 0, 100).sorted().forEach(i -> System.out.print(i + COMMA));

        assertThat(Arrays.asList(3, -3, 6, 6, 5).stream().map(i -> i * i).distinct().collect(Collectors.toList()))
                .containsExactly(9, 36, 25);

        assertEquals(3, STR_LIST.parallelStream().filter(s -> s.isEmpty()).count());

        assertEquals("abc, bc, efg, abcd, jkl",
                STR_LIST.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(", ")));

        // stats
        IntSummaryStatistics stats = Arrays.asList(3, 2, 2, 3, 7, 3, 5).stream().mapToInt(x -> x).summaryStatistics();
        assertEquals(7, stats.getMax());
        assertEquals(2, stats.getMin());
        assertEquals(25, stats.getSum());
        assertEquals(3.5714285714285716, stats.getAverage());
    }
}
