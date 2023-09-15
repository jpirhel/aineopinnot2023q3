package org.example.geo;

import java.util.Objects;

import org.example.annotation.ExcludeFromGeneratedJacocoReport;

/**
 * A single coordinate (lat, lon) in decimal format.
 */
public class Coord {
    private final Double lat;
    private final Double lon;

    /**
     * @param lat
     * @param lon
     */
    public Coord(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * @return Latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @return Longitude
     */
    public Double getLon() {
        return lon;
    }

    @ExcludeFromGeneratedJacocoReport
    @Override
    public String toString() {
        return "(" + lat + ", " + lon + ")";
    }

    @ExcludeFromGeneratedJacocoReport
    @SuppressWarnings("checkstyle:NeedBraces")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Objects.equals(getLat(), coord.getLat()) && Objects.equals(getLon(), coord.getLon());
    }

    @ExcludeFromGeneratedJacocoReport
    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLon());
    }
}
