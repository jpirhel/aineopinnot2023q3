package org.example.data;

import org.example.annotation.ExcludeFromGeneratedJacocoReport;

/**
 * Holds distance information between two airports.
 */
public class AirportDistance implements Comparable<AirportDistance> {
    private final Airport fromAirport;
    private final Airport toAirport;
    private final Integer distance;

    /**
     * Constructor for AirportDistance
     * @param fromAirport
     * @param toAirport
     * @param distance
     */
    public AirportDistance(Airport fromAirport, Airport toAirport, Integer distance) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.distance = distance;
    }

    /**
     * "From" airport
     * @return Airport
     */
    public Airport getFromAirport() {
        return fromAirport;
    }

    /**
     * "To" Airport
     * @return Airport
     */
    public Airport getToAirport() {
        return toAirport;
    }

    /**
     * Distance between airports
     * @return Distance
     */
    public Integer getDistance() {
        return distance;
    }

    @ExcludeFromGeneratedJacocoReport
    @Override
    public String toString() {
        return "AirportDistance{"
                + "from="
                + fromAirport.getName()
                + ", to="
                + toAirport.getName()
                + ", distance="
                + distance
                + '}';
    }

    @Override
    public int compareTo(AirportDistance o) {
        if (o.getDistance().equals(getDistance())) {
            return 0;
        }

        if (o.getDistance() < getDistance()) {
            return 1;
        }

        return -1;
    }
}
