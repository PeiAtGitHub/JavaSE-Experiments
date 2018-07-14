package pei.java.thirdp.lab.guava;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pei.java.thirdp.lab.utils.SolarPlanet.EARTH;
import static pei.java.thirdp.lab.utils.SolarPlanet.JUPITER;
import static pei.java.thirdp.lab.utils.SolarPlanet.MARS;
import static pei.java.thirdp.lab.utils.SolarPlanet.MERCURY;
import static pei.java.thirdp.lab.utils.SolarPlanet.NEPTUNE;
import static pei.java.thirdp.lab.utils.SolarPlanet.SATURN;
import static pei.java.thirdp.lab.utils.SolarPlanet.URANUS;
import static pei.java.thirdp.lab.utils.SolarPlanet.VENUS;
import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeSet;

import pei.java.thirdp.lab.utils.Person;
import pei.java.thirdp.lab.utils.SolarPlanet;

/**
 * @author pei
 */
public class CollectionDemos {

    @Test
    public void testLists() throws Exception {
        ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3);
        assertThat(numbers).containsExactly(1, 2, 3);
        assertThat(Lists.reverse(numbers)).containsExactly(3, 2, 1);
        assertThat(Lists.transform(numbers, x->2*x)).containsExactly(2, 4, 6);
    }

    @Test
    public void testSets() throws Exception {
        
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 2, 6);
        
        assertThat(Sets.difference(set1, set2)).containsExactly(3);
        assertThat(Sets.difference(set2, set1)).containsExactly(6);
        assertThat(Sets.symmetricDifference(set1, set2)).containsExactly(3, 6);
        assertThat(Sets.intersection(set1, set2)).containsExactly(1, 2);
        assertThat(Sets.union(set1, set2)).containsExactly(1, 2, 3, 6);
        
        assertThat(Sets.filter(set1, x->x<5)).containsExactly(1, 2, 3);
        assertThat(Sets.filter(set2, x->x<5)).containsExactly(1, 2);

        assertThat(Sets.combinations(set1, 2)).hasSize(3);
        
        EnumSet<SolarPlanet> otherPlanets = Sets.complementOf(EnumSet.of(EARTH));
        assertThat(otherPlanets).hasSize(7).doesNotContain(EARTH)
        	.contains(MARS, SATURN, VENUS, NEPTUNE, JUPITER, URANUS, MERCURY);

        ImmutableSet<SolarPlanet> planets = ImmutableSet.of(MARS, SATURN, EARTH);
        assertThat(planets).containsExactly(MARS, SATURN, EARTH);
        assertThat(ImmutableSortedSet.copyOf(planets).asList()).containsExactly(EARTH, MARS, SATURN);
        
    }
    
    @Test
    public void testMap() throws Exception {

        assertThat(Maps.newHashMap()).hasSize(0);
        assertThat(Maps.newEnumMap(SolarPlanet.class)).hasSize(0);
        assertThat(Maps.asMap(TEST_SET_123, x->x*10)).contains(entry(1, 10), entry(2, 20), entry(3, 30));

        ImmutableMap<String, Integer> immuMap = ImmutableMap.of(S1, 1, S2, 2);
        assertEquals(S1, immuMap.keySet().iterator().next());
        assertEquals(S1, ImmutableSortedMap.copyOf(immuMap).keySet().iterator().next());
        
    }


    @Test
    public void demoOfImmutable() throws Exception {// What is "immutable"?
        ImmutableList<Person> persons = ImmutableList.of(Person.builder().firstName(FIRST_NAME).build());
        
        assertThatThrownBy(() -> persons.add(new Person())).isInstanceOf(UOE);
        assertThatThrownBy(() -> persons.set(0, new Person())).isInstanceOf(UOE);
        assertThatThrownBy(() -> persons.remove(0)).isInstanceOf(UOE);
        assertThatThrownBy(() -> persons.clear()).isInstanceOf(UOE);
        
        persons.get(0).setFirstName(STR);
        assertEquals(STR, persons.get(0).getFirstName());
    }

    @Test
    public void testMultiSet() throws Exception {
        Multiset<Integer> multiSet = HashMultiset.create(Sets.newHashSet(1, 2, 6));
        assertThat(multiSet).hasSize(3);
        assertEquals(1, multiSet.count(6));
        multiSet.setCount(2, 3);
        multiSet.setCount(6, 2);
        assertThat(multiSet).hasSize(6);
        assertEquals(2, multiSet.count(6));
        assertThat(multiSet.elementSet()).hasSize(3);
        assertEquals(Integer.valueOf(1), multiSet.entrySet().iterator().next().getElement());
        assertEquals(1, multiSet.entrySet().iterator().next().getCount());

        assertEquals(2, ImmutableMultiset.of(1, 2, 2, 2, 6, 6).count(6));
    }
    
    @Test
    public void testMultimap() throws Exception {
        ListMultimap<String, Integer> listMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
        listMultimap.put(S1, 1);
        listMultimap.put(S1, 1);
        listMultimap.put(S1, 1);
        listMultimap.put(S2, 2);
        listMultimap.put(S2, 2);
        listMultimap.put(S2, 2);
        listMultimap.put(S3, 3);
        listMultimap.put(S3, 3);
        listMultimap.put(S3, 3);
        assertEquals(9, listMultimap.size());
        assertThat(listMultimap.asMap()).hasSize(3);
        assertThat(listMultimap.get(S1)).containsExactly(1, 1, 1);
        assertThat(listMultimap.asMap().get(S1)).containsExactly(1, 1, 1);
        
        SetMultimap<String, Integer> setMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        setMultimap.putAll(S1, Sets.newHashSet(1, 1, 1));
        setMultimap.putAll(S2, Sets.newHashSet(2, 2, 2));
        setMultimap.putAll(S3, Sets.newHashSet(3, 3, 3));
        assertEquals(3, setMultimap.size());
        assertThat(setMultimap.get(S1)).containsExactly(1);
        assertThat(setMultimap.asMap()).hasSize(3);
        assertThat(setMultimap.asMap().get(S1)).containsExactly(1);
        setMultimap.clear();
        setMultimap.putAll(S1, Sets.newHashSet(1, 2, 3));
        setMultimap.putAll(S2, Sets.newHashSet(4, 5, 6));
        setMultimap.putAll(S3, Sets.newHashSet(7, 8, 9));
        assertEquals(9, setMultimap.size());
        assertThat(setMultimap.get(S1)).containsExactlyInAnyOrder(1, 2, 3);
        assertThat(setMultimap.asMap()).hasSize(3);
        assertThat(setMultimap.asMap().get(S1)).containsExactlyInAnyOrder(1, 2, 3);
        
        assertThat(ImmutableMultimap.of(S1, 1, S2, 2, S1, 3).get(S1)).hasSize(2);
        
    }
    
    @Test
    public void testBimap() throws Exception {
    	
        HashBiMap<String, Integer> biMap = HashBiMap.<String, Integer>create(TEST_MAP_123);
        assertEquals(Integer.valueOf(1), biMap.get(S1));
        assertEquals(S1, biMap.inverse().get(1));
        
        assertThatThrownBy(()->biMap.put(" ", 1)).isInstanceOf(IAE);
        
        biMap.forcePut(" ", 1);
        assertEquals(Integer.valueOf(1), biMap.get(" "));
        assertThat(biMap.keySet()).doesNotContain(S1);
        
        assertThatThrownBy(() -> ImmutableBiMap.of(S1, 1, S2, 1)).isInstanceOf(IAE);
        
    }
    
    @Test
    public void testTable() throws Exception {// table: indexing on 2 keys, like a table
        Table<Integer, String, Integer> sales = HashBasedTable.create();
        sales.put(1, "Jan", 100);
        sales.put(1, "Feb", 50);
        sales.put(2, "Jan", 200);
        sales.put(2, "Feb", 100);
        sales.put(3, "Mar", 300);
        assertThat(sales.row(1)).hasSize(2);
        assertThat(sales.cellSet()).hasSize(5);

        ImmutableTable<Integer, String, String> customersTable = ImmutableTable.<Integer, String, String>builder()
                .put(1, "Name", "Person One")
                .put(1, "City", "City One")
                .put(2, "Name", "Person Two")
                .put(2, "City", "City Two")
                .put(3, "Name", "Person Three")
                .put(3, "Country", "Country Three")
                .build();
        assertEquals("Person One", customersTable.column("Name").get(1));
        assertEquals("Country Three", customersTable.row(3).get("Country"));
    }
    
    @Test
    public void testPeekingIterator() throws Exception {
        Iterator<Integer> it = TEST_LIST_123.iterator();
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertFalse(it.hasNext());
        
        // Peek: to glance quickly.
        PeekingIterator<Integer> peekIt = Iterators.peekingIterator(TEST_LIST_123.iterator());
        assertEquals(Integer.valueOf(1), peekIt.peek());
        assertEquals(Integer.valueOf(1), peekIt.peek());
        assertEquals(Integer.valueOf(1), peekIt.next());
        assertEquals(Integer.valueOf(2), peekIt.next());
        assertEquals(Integer.valueOf(3), peekIt.peek());
        assertEquals(Integer.valueOf(3), peekIt.next());
        assertFalse(peekIt.hasNext());
        /*
         * Note:
         * PeekingIterator returned by Iterators.peekingIterator does not 
         * support remove() calls after a peek().
         */
    }
        
    @Test
    public void testMiscs() throws Exception {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); 
        rangeSet.add(Range.closedOpen(11, 15)); 
        rangeSet.add(Range.closedOpen(15, 20)); // range merge with previous
        rangeSet.add(Range.openClosed(0, 0)); // empty, ignored
        rangeSet.remove(Range.open(5, 10)); // this removal will split range
        assertEquals("[[1..5], [10..10], [11..20)]", rangeSet.toString());
    }
    
    @Test
	public void iterablesDemo() throws Exception {
    	
		assertThat(Iterables.filter(
			ImmutableList.of(new NullPointerException(), new IllegalArgumentException(), new IllegalStateException())
			, NPE)).allMatch(e -> e instanceof NullPointerException);

		assertEquals("[1, 2]", Iterables.filter(TEST_LIST_123, Range.closed(-10, 2)).toString());
		
		assertEquals(Integer.valueOf(1), Iterables.find(TEST_LIST_123, Range.closed(-10, 2)));
		
		assertEquals(1, Iterables.frequency(TEST_LIST_123, 1));
		assertEquals(3, Iterables.frequency(ImmutableList.of(1, 1, 1), 1));
		
	}
}
