package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static pei.java.thirdp.lab.utils.Utils.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

import com.google.common.collect.*;

import static pei.java.thirdp.lab.utils.SolarPlanet.*;

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

        assertThat(Sets.combinations(set1, 2).size(), is(3));
        //
        EnumSet<SolarPlanet> otherPlanets = Sets.complementOf(EnumSet.of(EARTH));
        assertThat(otherPlanets.size(), is(7));
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
        assertThat(Maps.asMap(Sets.newHashSet(1, 2, 3), x->x*10).toString(), 
                is("{1=10, 2=20, 3=30}"));

        //
        ImmutableMap<String, Integer> nameMap = ImmutableMap.of(TOM, 1, JERRY, 2);
        assertThat(nameMap.keySet().iterator().next(), is(TOM));
        assertThat(ImmutableSortedMap.copyOf(nameMap).keySet().iterator().next(), is(JERRY));
    }


    @Test
    public void demoOfImmutable() throws Exception {// What is "immutable"?
        ImmutableList<Person> persons = ImmutableList.of(new Person(TOM));
        
        assertThat(catchException(() -> persons.add(new Person(JERRY))),
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.set(0, new Person(JERRY))), 
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.remove(0)), 
                instanceOf(UnsupportedOperationException.class));
        
        assertThat(catchException(() -> persons.clear()), 
                instanceOf(UnsupportedOperationException.class));
        
        persons.get(0).setFirstName(JERRY);
        assertThat(persons.get(0).getFirstName(), is(JERRY));
    }

    @Test
    public void testMultiSet() throws Exception {
        Multiset<Integer> multiSet = HashMultiset.create();
        multiSet.add(1);
        multiSet.add(2);
        multiSet.add(6);
        assertThat(multiSet.size(), is(3));
        assertThat(multiSet.count(6), is(1));
        multiSet.setCount(2, 3);
        multiSet.setCount(6, 2);
        assertThat(multiSet.size(), is(6));
        assertThat(multiSet.count(6), is(2));
        assertThat(multiSet.elementSet().size(), is(3));
        assertThat(multiSet.entrySet().iterator().next().getElement(), is(1));
        assertThat(multiSet.entrySet().iterator().next().getCount(), is(1));

        //
        assertThat(ImmutableMultiset.of(1, 2, 2, 2, 6, 6).count(6), is(2));
    }
    
    @Test
    public void testMultimap() throws Exception {
        ListMultimap<String, Integer> listMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();
        listMultimap.put(ABC, 1);
        listMultimap.put(ABC, 1);
        listMultimap.put(ABC, 1);
        listMultimap.put(DEF, 2);
        listMultimap.put(DEF, 2);
        listMultimap.put(DEF, 2);
        listMultimap.put(GHI, 3);
        listMultimap.put(GHI, 3);
        listMultimap.put(GHI, 3);
        assertThat(listMultimap.size(), is(9));
        assertThat(listMultimap.asMap().size(), is(3));
        assertThat(listMultimap.get(ABC).toString(), is("[1, 1, 1]"));
        assertThat(listMultimap.asMap().get(ABC).toString(), is("[1, 1, 1]"));
        
        // 
        SetMultimap<String, Integer> setMultimap =
                MultimapBuilder.hashKeys().hashSetValues().build();
        setMultimap.putAll(ABC, Sets.newHashSet(1, 1, 1));
        setMultimap.putAll(DEF, Sets.newHashSet(2, 2, 2));
        setMultimap.putAll(GHI, Sets.newHashSet(3, 3, 3));
        assertThat(setMultimap.size(), is(3));
        assertThat(setMultimap.get(ABC).toString(), is("[1]"));
        assertThat(setMultimap.asMap().size(), is(3));
        assertThat(setMultimap.asMap().get(ABC).toString(), is("[1]"));
        setMultimap.clear();
        setMultimap.putAll(ABC, Sets.newHashSet(1, 2, 3));
        setMultimap.putAll(DEF, Sets.newHashSet(4, 5, 6));
        setMultimap.putAll(GHI, Sets.newHashSet(7, 8, 9));
        assertThat(setMultimap.size(), is(9));
        assertThat(setMultimap.get(ABC).toString(), is("[1, 2, 3]"));
        assertThat(setMultimap.asMap().size(), is(3));
        assertThat(setMultimap.asMap().get(ABC).toString(), is("[1, 2, 3]"));
        
        //
        assertThat(ImmutableMultimap.of(TOM, 1, JERRY, 2, TOM, 3).get(TOM).size(), is(2));
    }
    
    @Test
    public void testBimap() throws Exception {
        HashBiMap<Integer, String> biMap = HashBiMap.<Integer, String>create();
        biMap.put(1, TOM);
        biMap.put(2, JERRY);
        assertThat(biMap.get(1), is(TOM));
        assertThat(biMap.inverse().get(TOM), is(1));
        assertThat(catchException(()->biMap.put(3, TOM))
                , instanceOf(IllegalArgumentException.class));
        biMap.forcePut(3, TOM);
        assertThat(biMap.get(3), is(TOM));
        assertThat(biMap.keySet(), not(hasItem(1)));
        
        //
        assertThat(catchException(() -> ImmutableBiMap.of(TOM, 1, TOM, 2)),
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
        Iterator<String> it = Lists.newArrayList(TOM, JERRY, MICKEY).iterator();
        assertThat(it.next(), is(TOM));
        assertThat(it.next(), is(JERRY));
        assertThat(it.next(), is(MICKEY));
        assertThat(it.hasNext(), is(false));
        
        // Peek: to glance quickly.
        PeekingIterator<String> peekIt = 
                Iterators.peekingIterator(Lists.newArrayList(TOM, JERRY, MICKEY).iterator());
        assertThat(peekIt.peek(), is(TOM));
        assertThat(peekIt.peek(), is(TOM));
        assertThat(peekIt.next(), is(TOM));
        assertThat(peekIt.next(), is(JERRY));
        assertThat(peekIt.peek(), is(MICKEY));
        assertThat(peekIt.next(), is(MICKEY));
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
}
