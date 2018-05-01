package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static pei.java.thirdp.lab.utils.SolarPlanet.EARTH;
import static pei.java.thirdp.lab.utils.SolarPlanet.JUPITER;
import static pei.java.thirdp.lab.utils.SolarPlanet.MARS;
import static pei.java.thirdp.lab.utils.SolarPlanet.MERCURY;
import static pei.java.thirdp.lab.utils.SolarPlanet.NEPTUNE;
import static pei.java.thirdp.lab.utils.SolarPlanet.SATURN;
import static pei.java.thirdp.lab.utils.SolarPlanet.URANUS;
import static pei.java.thirdp.lab.utils.SolarPlanet.VENUS;
import static pei.java.thirdp.lab.utils.Utils.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

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
 * 
 * @author pei
 *
 */
public class CollectionDemos {

    @Test
    public void testLists() throws Exception {
        ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3);
        assertThat(numbers.toString(),  is("[1, 2, 3]"));
        assertThat(Lists.reverse(numbers).toString(),  is("[3, 2, 1]"));
        assertThat(Lists.transform(numbers, x->2*x).toString(),  is("[2, 4, 6]"));
    }

    @Test
    public void testSets() throws Exception {
        
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 2, 6);
        
        assertThat(Sets.difference(set1, set2).toString(), is("[3]"));
        assertThat(Sets.difference(set2, set1).toString(), is("[6]"));
        assertThat(Sets.symmetricDifference(set1, set2).toString(), is("[3, 6]"));
        assertThat(Sets.intersection(set1, set2).toString(), is("[1, 2]"));
        assertThat(Sets.union(set1, set2).toString(), is("[1, 2, 3, 6]"));
        
        assertThat(Sets.filter(set1, x->x<5).toString(), is("[1, 2, 3]"));
        assertThat(Sets.filter(set2, x->x<5).toString(), is("[1, 2]"));

        assertThat(Sets.combinations(set1, 2), hasSize(3));
        //
        EnumSet<SolarPlanet> otherPlanets = Sets.complementOf(EnumSet.of(EARTH));
        assertThat(otherPlanets, hasSize(7));
        assertThat(otherPlanets, not(hasItem(EARTH)));
        assertThat(otherPlanets, hasItems(MARS, SATURN, VENUS, NEPTUNE, JUPITER, URANUS, MERCURY));

        //
        ImmutableSet<SolarPlanet> planets = ImmutableSet.of(MARS, SATURN, EARTH);
        assertThat(planets.toString(), is("[MARS, SATURN, EARTH]"));
        assertThat(ImmutableSortedSet.copyOf(planets).asList().toString(), 
                is("[EARTH, MARS, SATURN]"));
        
    }
    
    @Test
    public void testMap() throws Exception {

        assertThat(Maps.newHashMap().size(), is(0));
        assertThat(Maps.newEnumMap(SolarPlanet.class).size(), is(0));
        assertThat(Maps.asMap(TEST_SET_123, x->x*10).toString(), 
                is("{1=10, 2=20, 3=30}"));

        //
        ImmutableMap<String, Integer> immuMap = ImmutableMap.of(S1, 1, S2, 2);
        assertThat(immuMap.keySet().iterator().next(), is(S1));
        assertThat(ImmutableSortedMap.copyOf(immuMap).keySet().iterator().next(), is(S1));
    }


    @Test
    public void demoOfImmutable() throws Exception {// What is "immutable"?
        ImmutableList<Person> persons 
        = ImmutableList.of(Person.builder().firstName(FIRST_NAME).build());
        
        assertThat(catchException(() -> persons.add(new Person())),
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.set(0, new Person())), 
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.remove(0)), 
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.clear()), 
                instanceOf(UnsupportedOperationException.class));
        
        persons.get(0).setFirstName(STR);
        assertThat(persons.get(0).getFirstName(), is(STR));
    }

    @Test
    public void testMultiSet() throws Exception {
        Multiset<Integer> multiSet = HashMultiset.create(Sets.newHashSet(1, 2, 6));
        assertThat(multiSet, hasSize(3));
        assertThat(multiSet.count(6), is(1));
        multiSet.setCount(2, 3);
        multiSet.setCount(6, 2);
        assertThat(multiSet, hasSize(6));
        assertThat(multiSet.count(6), is(2));
        assertThat(multiSet.elementSet(), hasSize(3));
        assertThat(multiSet.entrySet().iterator().next().getElement(), is(1));
        assertThat(multiSet.entrySet().iterator().next().getCount(), is(1));

        //
        assertThat(ImmutableMultiset.of(1, 2, 2, 2, 6, 6).count(6), is(2));
    }
    
    @Test
    public void testMultimap() throws Exception {
        ListMultimap<String, Integer> listMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();
        listMultimap.put(S1, 1);
        listMultimap.put(S1, 1);
        listMultimap.put(S1, 1);
        listMultimap.put(S2, 2);
        listMultimap.put(S2, 2);
        listMultimap.put(S2, 2);
        listMultimap.put(S3, 3);
        listMultimap.put(S3, 3);
        listMultimap.put(S3, 3);
        assertThat(listMultimap.size(), is(9));
        assertThat(listMultimap.asMap().size(), is(3));
        assertThat(listMultimap.get(S1).toString(), is("[1, 1, 1]"));
        assertThat(listMultimap.asMap().get(S1).toString(), is("[1, 1, 1]"));
        
        // 
        SetMultimap<String, Integer> setMultimap =
                MultimapBuilder.hashKeys().hashSetValues().build();
        setMultimap.putAll(S1, Sets.newHashSet(1, 1, 1));
        setMultimap.putAll(S2, Sets.newHashSet(2, 2, 2));
        setMultimap.putAll(S3, Sets.newHashSet(3, 3, 3));
        assertThat(setMultimap.size(), is(3));
        assertThat(setMultimap.get(S1).toString(), is("[1]"));
        assertThat(setMultimap.asMap().size(), is(3));
        assertThat(setMultimap.asMap().get(S1).toString(), is("[1]"));
        setMultimap.clear();
        setMultimap.putAll(S1, Sets.newHashSet(1, 2, 3));
        setMultimap.putAll(S2, Sets.newHashSet(4, 5, 6));
        setMultimap.putAll(S3, Sets.newHashSet(7, 8, 9));
        assertThat(setMultimap.size(), is(9));
        assertThat(setMultimap.get(S1).toString(), is("[1, 2, 3]"));
        assertThat(setMultimap.asMap().size(), is(3));
        assertThat(setMultimap.asMap().get(S1).toString(), is("[1, 2, 3]"));
        
        //
        assertThat(ImmutableMultimap.of(S1, 1, S2, 2, S1, 3).get(S1), hasSize(2));
    }
    
    @Test
    public void testBimap() throws Exception {
        HashBiMap<String, Integer> biMap = HashBiMap.<String, Integer>create(TEST_MAP_123);
        assertThat(biMap.get(S1), is(1));
        assertThat(biMap.inverse().get(1), is(S1));
        
        assertThat(catchException(()->biMap.put(" ", 1))
                , instanceOf(IllegalArgumentException.class));
        
        biMap.forcePut(" ", 1);
        assertThat(biMap.get(" "), is(1));
        assertThat(biMap.keySet(), not(hasItem(S1)));
        
        //
        assertThat(catchException(() -> ImmutableBiMap.of(S1, 1, S2, 1)),
                instanceOf(IllegalArgumentException.class));
    }
    
    @Test
    public void testTable() throws Exception {// table: indexing on 2 keys, like a table
        Table<Integer, String, Integer> sales = HashBasedTable.create();
        sales.put(1, "Jan", 100);
        sales.put(1, "Feb", 50);
        sales.put(2, "Jan", 200);
        sales.put(2, "Feb", 100);
        sales.put(3, "Mar", 300);
        assertThat(sales.row(1).size(), is(2));
        assertThat(sales.cellSet().size(), is(5));

        //
        ImmutableTable<Integer, String, String> customersTable = ImmutableTable.<Integer, String, String>builder()
                .put(1, "Name", "Person One")
                .put(1, "City", "City One")
                .put(2, "Name", "Person Two")
                .put(2, "City", "City Two")
                .put(3, "Name", "Person Three")
                .put(3, "Country", "Country Three")
                .build();
        assertThat(customersTable.column("Name").get(1), is("Person One"));
        assertThat(customersTable.row(3).get("Country"), is("Country Three"));
    }
    
    @Test
    public void testPeekingIterator() throws Exception {
        Iterator<Integer> it = TEST_LIST_123.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        
        // Peek: to glance quickly.
        PeekingIterator<Integer> peekIt = Iterators.peekingIterator(TEST_LIST_123.iterator());
        assertThat(peekIt.peek(), is(1));
        assertThat(peekIt.peek(), is(1));
        assertThat(peekIt.next(), is(1));
        assertThat(peekIt.next(), is(2));
        assertThat(peekIt.peek(), is(3));
        assertThat(peekIt.next(), is(3));
        assertThat(peekIt.hasNext(), is(false));
        /*
         * Note:
         * PeekingIterator returned by Iterators.peekingIterator does not 
         * support remove() calls after a peek().
         */
    }
        
    @Test
    public void testMiscs() throws Exception {
        // RangeSet
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); 
        rangeSet.add(Range.closedOpen(11, 15)); 
        rangeSet.add(Range.closedOpen(15, 20)); // range merge with previous
        rangeSet.add(Range.openClosed(0, 0)); // empty, ignored
        rangeSet.remove(Range.open(5, 10)); // this removal will split range
        assertThat(rangeSet.toString(), is("[[1..5], [10..10], [11..20)]"));
    }
    
    @Test
	public void iterablesDemo() throws Exception {
    	
		Iterables.filter(
			ImmutableList.of(new NullPointerException(), new IllegalArgumentException(), new IllegalStateException())
			, NullPointerException.class)
		.forEach(e->assertThat(e, instanceOf(NullPointerException.class)));
		
		assertThat(Iterables.filter(TEST_LIST_123, Range.closed(-10, 2)).toString(), is("[1, 2]"));
		
		assertThat(Iterables.find(TEST_LIST_123, Range.closed(-10, 2)), is(1));
		
		assertThat(Iterables.frequency(TEST_LIST_123, 1), is(1));
		assertThat(Iterables.frequency(ImmutableList.of(1, 1, 1), 1), is(3));
		
	}
}
