package org.example.geo;

import org.example.data.Airport;

import java.util.ArrayList;

/**
 * Geographic utility functions
 */
public class GeoUtil {
    // needed for Jacoco coverage
    private GeoUtil() {}

    private static final Long EARTH_RADIUS = 6371000L; // meters

    /**
     * Calculates the great circle distance from start coordinate
     * to end coordinate using the Haversine formula [1].
     * <p>
     * [1] <a href="https://en.wikipedia.org/wiki/Haversine_formula">https://en.wikipedia.org/wiki/Haversine_formula</a>
     *
     * @param from Start coordinate
     * @param to   End coordinate
     * @return Geoutil from start coordinate to end coordinate in kilometers
     */
    @SuppressWarnings("ExtractMethodRecommender")
    public static Integer distanceInKm(Coord from, Coord to) {
        // Haversine formula re-implemented in Java, original:
        // https://community.esri.com/t5/coordinate-reference-systems-blog/distance-on-a-sphere-the-haversine-formula/ba-p/902128

        double lon1 = from.getLon();
        double lat1 = from.getLat();

        double lon2 = to.getLon();
        double lat2 = to.getLat();

        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);

        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double deltaPhiSin = Math.pow(Math.sin(deltaPhi / 2.0), 2);
        double deltaLambdaSin = Math.pow(Math.sin(deltaLambda / 2.0), 2);

        double a = deltaPhiSin + Math.cos(phi1) * Math.cos(phi2) * deltaLambdaSin;

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double meters = EARTH_RADIUS * c; // distance in meters
        double kilometers = Math.round(meters / 1000.0);

        return (int) kilometers;
    }

    public static int routeTotalDistance(ArrayList<Airport> route, boolean output) {
        int totalDistance = 0;

        Airport destAirport = route.get(route.size() - 1);

        for (int i = 0; i < route.size() - 1; i++) {
            Airport curr = route.get(i);
            Airport next = route.get(i + 1);

            int distance = GeoUtil.distanceInKm(curr.getCoord(), next.getCoord());

            if (output) {
                System.out.println("ICAO: " + curr.getIcao() + ", " + curr.getName() + ", " + curr.getCity() + ", " + curr.getCountry() + ", coordinates: " + curr.getCoord());
                System.out.println("-> distance to next hop: " + distance + " km");
            }

            totalDistance += distance;
        }

        if (output) {
            System.out.println("ICAO: " + destAirport.getIcao() + ", " + destAirport.getName() + ", " + destAirport.getCity() + ", " + destAirport.getCountry() + ", coordinates: " + destAirport.getCoord());
            System.out.println();
            System.out.println("total distance: " + totalDistance + " km" + ", number of hops: " + route.size());
        }

        return totalDistance;
    }
}
