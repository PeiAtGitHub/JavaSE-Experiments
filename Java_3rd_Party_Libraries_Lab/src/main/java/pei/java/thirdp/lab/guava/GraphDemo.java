package pei.java.thirdp.lab.guava;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.graph.ImmutableNetwork;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;
import static pei.java.thirdp.lab.utils.US_CITY.*;
import pei.java.thirdp.lab.utils.US_CITY;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class GraphDemo {

    @Test
    public void testNetwork() throws Exception {

        MutableNetwork<US_CITY, DirectConnection> usCities = NetworkBuilder.undirected()
                .allowsParallelEdges(true).build();

        usCities.addEdge(NEW_YORK, BALTIMORE, new CarRide());
        usCities.addEdge(NEW_YORK, LOS_ANGELES, new Flight());
        usCities.addEdge(NEW_YORK, SEATTLE, new Flight());
        usCities.addEdge(LOS_ANGELES, LAS_VEGAS, new CarRide(5));
        usCities.addEdge(LOS_ANGELES, SACRAMENTO, new CarRide(5));
        usCities.addEdge(BALTIMORE, ATLANTA, new CarRide());
        usCities.addEdge(ATLANTA, AUSTIN, new CarRide());
        usCities.addEdge(AUSTIN, LITTLE_ROCK, new CarRide());
        usCities.addEdge(LITTLE_ROCK, DENVER, new CarRide());
        usCities.addEdge(DENVER, SALT_LAKE_CITY, new CarRide());
        usCities.addEdge(SALT_LAKE_CITY, PORTLAND, new CarRide());
        usCities.addEdge(PORTLAND, SEATTLE, new CarRide());
        usCities.addEdge(CHICAGO, ST_LOUIS, new CarRide());
        usCities.addEdge(ST_LOUIS, LITTLE_ROCK, new CarRide());

        log.info("New York adjacent cities: {}", usCities.adjacentNodes(NEW_YORK));
        log.info("New York Degree: {}", usCities.degree(NEW_YORK));
        assertTrue(usCities.edgesConnecting(BALTIMORE, PORTLAND).isEmpty());
        assertFalse(usCities.hasEdgeConnecting(BALTIMORE, SEATTLE));
        log.info("St_Louis 2-hop neighers: {}", getTwoHopNeighbors(usCities, ST_LOUIS));
        
        //
        ImmutableNetwork<US_CITY, DirectConnection> immutableCityNetwork = ImmutableNetwork.copyOf(usCities);
        assertFalse(immutableCityNetwork.asGraph().hasEdgeConnecting(NEW_YORK, ATLANTA));

    }

    /**
     * Traversing an undirected graph node-wise. Return all nodes reachable by
     * traversing 2 edges starting from "node"(Ignoring edge direction and edge
     * weights, if any, and not including "node").
     * 
     * @param network
     * @param city
     * @return
     */

    static Set<US_CITY> getTwoHopNeighbors(Network<US_CITY, DirectConnection> network, US_CITY city) {
        Set<US_CITY> twoHopNeighbors = new HashSet<>();
        for (US_CITY neighbor : network.adjacentNodes(city)) {
            twoHopNeighbors.addAll(network.adjacentNodes(neighbor));
        }
        twoHopNeighbors.remove(city);
        return twoHopNeighbors;
    }

}

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
class DirectConnection {
    int hours;
    int cost;
}

@NoArgsConstructor
class CarRide extends DirectConnection {
	
	public CarRide(int hours) {
		super.setHours(hours);
	}
}

@NoArgsConstructor @AllArgsConstructor
class Flight extends DirectConnection {
    String flightNumber;
}
