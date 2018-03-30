package pei.java.thirdp.lab.guava;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;

import lombok.extern.slf4j.Slf4j;
import pei.java.thirdp.lab.utils.NanoStopWatch;
import pei.java.thirdp.lab.utils.US_CITY;

/**
 *  
 * "if cached, return; otherwise create, cache and return"
 * 
 * @author pei
 *
 */
@Slf4j
public class CacheDemo {
    
    private static final long  HEAVEY_COMPUTING_MILLIS = 3000L;

    @Test
    public void testUsingCacheLoader() throws Exception {

        LoadingCache<Integer, Graph<US_CITY>> graphsCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .recordStats()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoaderImpl());

        graphsCache.put(1, GraphBuilder.undirected().build());
        
        NanoStopWatch.begin();
        Graph<US_CITY> g1 = graphsCache.get(1);
        assertTrue(NanoStopWatch.stopAndGetElapsedMillis() <= 1);
        
        assertSame(g1, graphsCache.get(1));
        //
        NanoStopWatch.begin();
        Graph<US_CITY> g2 = graphsCache.get(2);
        assertTrue(NanoStopWatch.stopAndGetElapsedMillis() >= HEAVEY_COMPUTING_MILLIS );
        assertSame(g2, graphsCache.get(2));
        //
        assertNotSame(g2, g1);
        
        CacheStats cacheStats = graphsCache.stats();
        assertThat(cacheStats.hitCount(), is(3L));
        assertThat(cacheStats.loadCount(), is(1L));
        assertThat(cacheStats.missCount(), is(1L));
        assertThat(cacheStats.requestCount(), is(4L));
    }
    
    @Test
    public void testUsingCallable() throws Exception {
        
        Cache<Integer, Graph<US_CITY>> graphsCache 
            = CacheBuilder.newBuilder().maximumSize(1000).recordStats().build(); 
        
        NanoStopWatch.begin();
        Graph<US_CITY> g1 = graphsCache.get(1, new CallableImpl());
        assertTrue(NanoStopWatch.stopAndGetElapsedMillis() >= HEAVEY_COMPUTING_MILLIS );

        NanoStopWatch.begin();
        assertSame(g1, graphsCache.get(1, new CallableImpl()));
        assertTrue(NanoStopWatch.stopAndGetElapsedMillis() <= 1);
        
        CacheStats cacheStats = graphsCache.stats();
        assertThat(cacheStats.hitCount(), is(1L));
        assertThat(cacheStats.loadCount(), is(1L));
        assertThat(cacheStats.missCount(), is(1L));
        assertThat(cacheStats.requestCount(), is(2L));    
    }

    @Test
    public void testCacheEviction() throws Exception {

        LoadingCache<Integer, Graph<US_CITY>> graphsCache = CacheBuilder.newBuilder().maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .removalListener(new RemovalListenerImpl())
                .build(new CacheLoaderImpl());
        
        graphsCache.put(1, GraphBuilder.undirected().build());
        graphsCache.put(2, GraphBuilder.directed().build());
        
        assertThat(graphsCache.size(), is(2L));
        graphsCache.invalidate(2); // manual remove does not affect eviction count
        assertThat(graphsCache.size(), is(1L));
        assertThat(graphsCache.stats().evictionCount(), is(0L));
    }

    
    /*
     * 
     */
    class CacheLoaderImpl extends CacheLoader<Integer, Graph<US_CITY>> {

        public Graph<US_CITY> load(Integer key) throws Exception {
            Thread.sleep(HEAVEY_COMPUTING_MILLIS);
            return GraphBuilder.undirected().build();
        }
    }
    
    
    class CallableImpl implements Callable<Graph<US_CITY>>{

        public Graph<US_CITY> call() throws Exception {
            Thread.sleep(HEAVEY_COMPUTING_MILLIS);
            return GraphBuilder.undirected().build();
        }
    
    }
    
    class RemovalListenerImpl implements RemovalListener<Integer, Graph<US_CITY>>{
        
        public void onRemoval(RemovalNotification<Integer, Graph<US_CITY>> notification) {
            log.info("Got notification on removal of Key: {}, Value: {}."
                    ,notification.getKey(), notification.getValue());
        }
    }
    
}

