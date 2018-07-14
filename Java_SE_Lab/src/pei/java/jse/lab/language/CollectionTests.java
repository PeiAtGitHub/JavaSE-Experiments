package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.github.peiatgithub.java.utils.collections.MapBuilder;
import com.google.common.collect.ImmutableMap;

import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * @author pei
 */
public class CollectionTests {

    // An array's toString() is unreadable for human

    @Test
    public void testIterators() throws Exception {
        assertNotSame(TEST_LIST_123.iterator(), TEST_LIST_123.iterator());
    }

    @Test
    public void someCollectionsUtils() {
        List<Integer> aList = Arrays.asList(5, 2, 1, 4, 3);

        assertThat(Collections.max(aList)).isEqualTo(5);
        assertThat(Collections.min(aList)).isEqualTo(1);

        assertThat(aList).containsExactly(5, 2, 1, 4, 3);
        Collections.reverse(aList);
        assertThat(aList).containsExactly(3, 4, 1, 2, 5);
        Collections.sort(aList);
        assertThat(aList).containsExactly(1, 2, 3, 4, 5);

        // unmodifiable view
        List<Integer> unmodifiableList = Collections.unmodifiableList(aList);
        assertThatThrownBy(() -> unmodifiableList.set(0, 1)).isInstanceOf(UOE);

        /*
         * empty list, always the same immutable & singleton instance. faster and safer
         * than "new" an new list object. a better return value than returning null
         */
        assertSame(Collections.<String>emptyList(), Collections.<String>emptyList());

        assertThatThrownBy(() -> Collections.<String>emptyList().add(STR)).isInstanceOf(UOE);

        assertThat(Collections.singletonList(STR)).containsExactly("STR");
        assertThat(Collections.singletonMap("ABC", "XYZ")).hasSize(1).contains(entry("ABC", "XYZ"));
        
        assertThat(Collections.singleton(STR)).contains(STR);

    }

    @Test
    public void someArraysUtils() {

        List<Integer> list = Arrays.asList(6, 2, 1);
        assertThat(list).containsExactly(6, 2, 1);

        // list --> array
        Integer[] arr = list.toArray(new Integer[list.size()]);
        assertThat(arr).containsExactly(6, 2, 1);

        Arrays.sort(arr);
        assertThat(arr).containsExactly(1, 2, 6);

    }

    @Test
    public void constructCollection() {
        ArrayList<Integer> newList = new ArrayList<>(TEST_LIST_123);

        assertNotSame(TEST_LIST_123, newList);
        assertThat(TEST_LIST_123).isEqualTo(newList);

        // they don't share the common base
        newList.remove(2);
        assertThat(TEST_LIST_123).isNotEqualTo(newList);
    }

    @Test
    public void listFeatures() {
        ArrayList<String> emptyList = new ArrayList<String>(8); // with initial capacity
        assertThatThrownBy(() -> emptyList.get(0)).isInstanceOf(IOBE);
    }

    @Test
    public void mapFeatures() {
        Map<String, Integer> theMap = new HashMap<>(TEST_MAP_123);
        assertThat(theMap).containsAllEntriesOf(TEST_MAP_123);

        // non-existing key
        assertNull(theMap.get("XXX"));
        assertNull(theMap.remove("XXX"));
        // map.remove() returns the Value
        assertThat(theMap.remove(S1)).isEqualTo(1);
        // set.remove() returns boolean
        assertFalse(theMap.keySet().remove(S1));
        assertTrue(theMap.keySet().remove(S2));
        assertThat(theMap).hasSize(1);
    }

    @Test
    public void linkedHashMapKeepsInsertionOrder() throws Exception {

        LinkedHashMap<Integer, String> lhm1 = new LinkedHashMap<Integer, String>(ImmutableMap.of(1, S1, 3, S3, 2, S2));
        assertThat(lhm1.keySet()).containsExactly(1, 3, 2);
        assertThat(lhm1.values()).containsExactly(S1, S3, S2);
        
        // another approach test
        LinkedHashMap<Integer, String> lhm2 = MapBuilder.linkedHashMap(3, S3).put(2, S2).put(1, S1).build();
        assertThat(lhm2.keySet()).containsExactly(3, 2, 1);
        assertThat(lhm2.values()).containsExactly(S3, S2, S1);
    }

    @Test
    public void arrayDefaults() {
        int n = 3;

        long[] longArr = new long[n];
        boolean[] booleanArr = new boolean[n];
        String[] strArr = new String[n];

        // default initial values
        for (int i = 0; i < n; i++) {
            assertEquals(0, longArr[i]);
            assertNull(strArr[i]);
            assertFalse(booleanArr[i]);
        }

    }

}
