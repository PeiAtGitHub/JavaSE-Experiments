package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static pei.java.jse.lab.utils.Utils.*;

/**
 * 
 * @author pei
 *
 */
public class CollectionTests {
    

	@Test
	public void testIterators() throws Exception {
		assertNotSame(TEST_LIST_123.iterator(), TEST_LIST_123.iterator());
	}
	
    @Test
    public void someCollectionsUtils() {
        List<Integer> aList = Arrays.asList(5,2,1,4,3);

        assertThat(Collections.max(aList), is(5));
        assertThat(Collections.min(aList), is(1));
        
        assertThat(aList.toString(), is("[5, 2, 1, 4, 3]"));
        Collections.reverse(aList);
        assertThat(aList.toString(), is("[3, 4, 1, 2, 5]"));
        Collections.sort(aList);
        assertThat(aList.toString(), is("[1, 2, 3, 4, 5]")); 
        
        // unmodifiable view
        List<Integer> unmodifiableList = Collections.unmodifiableList(aList);
        assertThat(catchException(()->unmodifiableList.set(0, 1))  
                        ,instanceOf(UnsupportedOperationException.class));
        
        /*
         *  empty list, always the same immutable & singleton instance.
         *  faster and safer than "new" an new list object.
         *  a better return value than returning null
         */
        assertSame(Collections.<String> emptyList(), Collections.<String> emptyList()); 
        
        assertThat(catchException(()->Collections.<String> emptyList().add(STR)),
                instanceOf(UnsupportedOperationException.class));

    }
    
    
    @Test
    public void someArraysUtils() {
        
        List<Integer> list = Arrays.asList(6,2,1); 
        assertThat(list.toString(), is("[6, 2, 1]")); 
        
        // list --> array
        Integer[] arr = list.toArray(new Integer[list.size()]);
        
        System.out.println(arr.toString()); // An array's toString() is unreadable for human
        assertThat(Arrays.toString(arr), is("[6, 2, 1]")); // converted to human readable string
        
        // 
        Arrays.sort(arr);
        assertThat(Arrays.toString(arr), is("[1, 2, 6]")); 
    }
    
    @Test
    public void constructCollection() {
        ArrayList<Integer> newList = new ArrayList<>(TEST_LIST_123);
        
        assertNotSame(TEST_LIST_123, newList); 
        assertThat(TEST_LIST_123, is(newList)); // object.equals()
        assertEquals(TEST_LIST_123, newList); // object.equals()
        
        // they don't share the common base
        newList.remove(2);
        assertNotEquals(TEST_LIST_123, newList);
    }

    @Test
    public void listFeatures() {
        ArrayList<String> emptyList = new ArrayList<String>(8); // with initial capacity
        assertThat(catchException(()->emptyList.get(0)),
                instanceOf(IndexOutOfBoundsException.class));
    }
    
    @Test
    public void mapFeatures() {
        Map<String, Integer> theMap = new HashMap<>(TEST_MAP_123);
        
        assertThat(theMap.toString() // the order is not guaranteed
        		, allOf(containsString("S1=1"), containsString("S2=2"), containsString("S3=3")));
        
        // non-existing key 
        assertNull(theMap.get("XXX"));
        assertNull(theMap.remove("XXX"));
        // map.remove() returns the Value 
        assertThat(theMap.remove(S1), is(1));
        // set.remove() returns boolean
        assertFalse(theMap.keySet().remove(S1)); 
        assertTrue(theMap.keySet().remove(S2)); 
        assertThat(theMap.size(), is(1));
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
