package com.example.myapplication.Mesh;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoutingTable {

    public List<RoutingEntry> entries;

    public RoutingEntry getRouteShort(MeshProfile destination) {
        List<RoutingEntry> possibleEntries = entries.stream().filter(entry -> entry.destination.equals(destination)).collect(Collectors.toList());
        if (!possibleEntries.isEmpty()){
            possibleEntries.sort((a, b) -> Integer.compare(a.jumps.size(), b.jumps.size()));
            return possibleEntries.get(0);

        }
        return null;
    }
    public List<RoutingEntry> getRouteAll(MeshProfile destination) {
        return entries.stream().filter(entry -> entry.destination.equals(destination)).collect(Collectors.toList());
    }

    // Add Routing

    /**
     * Adds a new route to the routing table. If a route to the same destination already exists, it will be replaced if the new route has fewer jumps.
     *
     * @param destination The destination MeshProfile for the route.
     * @param nextHop The next hop UUID for the route.
     * @param jumps The list of MeshProfile IDs representing the path to the destination. With the first element being the next hop and the last element being the destination.
     */
    public void addRoute(MeshProfile destination, String nextHop, List<String> jumps) {
        entries.add(new RoutingEntry(destination, nextHop, jumps));
    }
    public void addRoute(RoutingEntry entry) {
        entries.add(entry);
    }
    // Remove Routing
    public void removeRoute(MeshProfile destination) {
        entries.removeIf(entry -> entry.destination.equals(destination));
    }

    /**
        * Compares this routing table with another routing table and updates this routing table with better routes from the other routing table.
        *
        *  For each entry in the other routing table, if the destination is the same and the number of jumps is less than the existing route, we add the new route to our routing table. If we have more than 2 routes to the same destination, we remove the worst one.
     */
    public void compareAndUpdateTable(RoutingTable other) {
        for (RoutingEntry entry : other.entries) {
            RoutingEntry existingEntry = getRouteShort(entry.destination);
            if (existingEntry == null) continue;
            if (entry.jumps.size() < existingEntry.jumps.size()) {
                // check if this route is better than our existing one, if it is we add it to our table
                addRoute(entry);
                List<RoutingEntry> knownRoutes = getRouteAll(entry.destination);
                if (knownRoutes.size() > 2) {
                    // if we have more than 2 routes to the destination we remove the worst one
                    // we keep 2 routes to the same destination to have a backup route in case one of them fails, but we remove the worst one to save space in our routing table
                    knownRoutes.sort((a, b) -> Integer.compare(a.jumps.size(), b.jumps.size()));
                    for (int i = 2; i < knownRoutes.size(); i++) {
                        removeRoute(knownRoutes.get(i).destination);
                    }
                }
            }
        }
    }


    /**
     * Represents a routing entry in the routing table, containing the destination MeshProfile, the next hop ID, and the list of jumps (path) to reach the destination.
     */
    public static class RoutingEntry {
        public MeshProfile destination;
        public String nextHop;
        public List<String> jumps; // List of UUIDs representing the path to the destination
        public RoutingEntry(MeshProfile destination, String nextHop, List<String> jumps) {
            this.destination = destination;
            this.nextHop = nextHop;
            this.jumps = jumps;
        }
    }
}
