package org.example.data;

import java.util.Objects;

import org.example.annotation.ExcludeFromGeneratedJacocoReport;
import org.example.geo.Coord;

/**
 * Airport data class.
 * <p>
 * Holds information about a single airport.
 */
public class Airport {
    /**
     * Converts a line of input data to airport object.
     *
     * @param line Input line from formatted data file
     * @return Airport object
     */
    public static Airport fromLine(int id, String line) {
        if (line == null) {
            return null;
        }

        // NOTE: This is a quick-n-dirty "parser" for formatted airport data lines.

        String[] parts = line.split(",");

        int offset = parts.length - 14;

        String type = clean(parts[12 + offset]);

        if (!type.equals("airport")) {
            return null;
        }

        int dataId = Integer.parseInt(parts[0]);

        String name;

        if (parts.length > 14) {
            name = clean(parts[1] + "," + parts[1 + offset]);
        } else {
            name = clean(parts[1]);
        }

        String city = clean(parts[2 + offset]);
        String country = clean(parts[3 + offset]);
        String icao = clean(parts[5 + offset]);
        Double latitude = Double.parseDouble(parts[6 + offset]);
        Double longitude = Double.parseDouble(parts[7 + offset]);

        return new Airport(id, dataId, name, city, country, icao, latitude, longitude);
    }

    /**
     * Function for cleaning airport data parts. Removes quotes.
     * @param part A part of the airport data line
     * @return Cleaned string
     */
    private static String clean(String part) {
        return part.replaceAll("\"", "");
    }

    private final int id;

    private final int dataId;

    private final String name;
    private final String city;
    private final String country;

    private final String icao;

    private final Double latitude;
    private final Double longitude;

    /**
     * @param id The airport ID
     * @param dataId The airport ID in the data set
     * @param name Name of the airport
     * @param city City of the airport
     * @param country Country of the airport
     * @param icao ICAO code of the airport
     * @param latitude Airport latitude in decimal degrees
     * @param longitude Airport longitude in decimal degrees
     */
    public Airport(
            int id,
            int dataId,
            String name,
            String city,
            String country,
            String icao,
            Double latitude,
            Double longitude) {
        this.id = id;
        this.dataId = dataId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Airport id
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Airport name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Airport city
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * Airport country
     * @return Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Airport ICAO code
     * @return ICAO code
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Airport latitude
     * @return Latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Airport longitude
     * @return Airport longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Generates a Coord object from airport latitude & longitude.
     * @return Coord object
     */
    public Coord getCoord() {
        return new Coord(latitude, longitude);
    }

    @ExcludeFromGeneratedJacocoReport
    @Override
    public String toString() {
        return name;
    }

    @ExcludeFromGeneratedJacocoReport
    @SuppressWarnings({"checkstyle:LineLength", "checkstyle:NeedBraces"})
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return getId() == airport.getId() && dataId == airport.dataId && Objects.equals(getName(), airport.getName()) && Objects.equals(getCity(), airport.getCity()) && Objects.equals(getCountry(), airport.getCountry()) && Objects.equals(getIcao(), airport.getIcao()) && Objects.equals(getLatitude(), airport.getLatitude()) && Objects.equals(getLongitude(), airport.getLongitude());
    }

    @ExcludeFromGeneratedJacocoReport
    @SuppressWarnings("checkstyle:LineLength")
    @Override
    public int hashCode() {
        return Objects.hash(getId(), dataId, getName(), getCity(), getCountry(), getIcao(), getLatitude(), getLongitude());
    }
}
