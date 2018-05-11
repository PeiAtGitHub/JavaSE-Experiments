package pei.java.jse.lab.java8new;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class StreamDemo {

    static final List<String> strList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "", "jkl");

    public static void main(String[] args) {
        //
        System.out.println("Filtered Strs: " + strList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList()));

        // Random stream
        System.out.print("Print 3 random integers:");
        new Random().ints(3, 0, 100).forEach(i -> System.out.print(i + SEMICOLON));
        System.out.println();
        // limit
        System.out.print("Print another 3 random integers:");
        new Random().ints(0, 100).limit(3).forEach(i -> System.out.print(i + SEMICOLON));
        System.out.println();
        // sort
        System.out.print("Print yet another 3 random integers, sorted:");
        new Random().ints(3, 0, 100).sorted().forEach(i -> System.out.print(i + SEMICOLON));
        System.out.println();

        // map
        System.out.println("Unique squares: "
                + Arrays.asList(3, -3, 6, 6, 5).stream().map(i -> i * i).distinct().collect(Collectors.toList()));

        // get count of empty string
        System.out.format("Empty string count: %d%n", strList.parallelStream().filter(s -> s.isEmpty()).count());

        // collectors
        System.out.println(
                "Merged String: " + strList.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(", ")));

        // stats
        IntSummaryStatistics stats = Arrays.asList(3, 2, 2, 3, 7, 3, 5).stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Avg: " + stats.getAverage());
    }

}
