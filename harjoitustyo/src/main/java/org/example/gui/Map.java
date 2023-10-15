package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport

import java.awt.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import javax.swing.event.MouseInputListener;

import org.example.data.Airport;
import org.example.data.AirportData;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

import org.jxmapviewer.viewer.*;

//CHECKSTYLE.ON: AvoidStarImport

public class Map {
    // show the whole world at once
    private static final int DEFAULT_ZOOM_LEVEL = 16;

    // move the map center upwards to show the whole world better
    private static final double DEFAULT_MAP_LATITUDE = 20;

    // center on zero meridian
    private static final double DEFAULT_MAP_LONGITUDE = 0;
    private final AirportData airportData;
    private final ArrayList<Airport> route;
    private final Boolean displayAirports;

    private JXMapViewer mapViewer;
    private Set<Waypoint> waypoints;

    /**
     * Constructor for Map class.
     *
     * @param airportData The airport data
     * @param route The calculated route
     * @param displayAirports If set, display all airports instead of the route
     */
    public Map(AirportData airportData, ArrayList<Airport> route, Boolean displayAirports) {
        this.airportData = airportData;
        this.route = route;
        this.displayAirports = displayAirports;
    }

    /**
     * Initializes the map.
     */
    public void initMap() {
        initMapPanel();
        initMapTileset();
        initMapEvents();
        initMapPosition();

        if (displayAirports) {
            initAirports();
        } else {
            initRoute();
        }
    }

    /**
     * Initializes the route.
     * Converts the list of route airports to GeoPosition objects.
     */
    public void initRoute() {
        if (route == null) {
            return;
        }

        ArrayList<GeoPosition> positions = new ArrayList<>();

        for (Airport airport : route) {
            positions.add(getAirportGeoPosition(airport));
        }

        Set<Waypoint> waypoints = new HashSet<>();

        positions.forEach(geoPosition -> {
            Waypoint waypoint = new DefaultWaypoint(geoPosition);
            waypoints.add(waypoint);
        });

        this.waypoints = waypoints;

        restoreWaypoints();
    }

    /**
     * Redisplays the list of waypoints on the map.
     */
    private void restoreWaypoints() {
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);
    }

    /**
     * Initializes the Map position to the default values.
     * Centers the Map to show most of the Earth with airports.
     */
    private void initMapPosition() {
        GeoPosition defaultMapCenter = new GeoPosition(DEFAULT_MAP_LATITUDE, DEFAULT_MAP_LONGITUDE);
        mapViewer.setZoom(DEFAULT_ZOOM_LEVEL);
        mapViewer.setAddressLocation(defaultMapCenter);
    }

    /**
     * Initializes the map to use OpenStreetMaps tiles.
     */
    private void initMapTileset() {
        TileFactoryInfo osmInfo = new OSMTileFactoryInfo();
        TileFactory osmInfoFactory = new DefaultTileFactory(osmInfo);

        mapViewer.setLayout(new BorderLayout());

        mapViewer.setTileFactory(osmInfoFactory);
    }

    /**
     * Initializes the Map panel using the jxmapviewer2 object.
     */
    private void initMapPanel() {
        //noinspection UnnecessaryLocalVariable
        JXMapViewer mapViewer = new JXMapViewer();

        this.mapViewer = mapViewer;
    }

    /**
     * Initializes the map events for zooming with the mouse wheel etc.
     */
    private void initMapEvents() {
        MouseInputListener mia = new PanMouseInputListener(mapViewer);

        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
    }

    /**
     * Initializes the map using the airport data.
     * Shows all the airports.
     */
    private void initAirports() {
        ArrayList<GeoPosition> airportGeoPositions = new ArrayList<>();

        for (Airport airport : airportData.getAirports()) {
            GeoPosition airportGeoPosition = getAirportGeoPosition(airport);
            airportGeoPositions.add(airportGeoPosition);
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();

        Set<Waypoint> waypoints = new HashSet<>();

        airportGeoPositions.forEach(geoPosition -> {
            Waypoint waypoint = new DefaultWaypoint(geoPosition);
            waypoints.add(waypoint);
        });

        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);
    }

    /**
     * @return The map object. It is a subclass of JPanel.
     */
    public JPanel getPanel() {
        return this.mapViewer;
    }

    /**
     * Converts an Airport object to a GeoPosition object used by the map.
     * @param airport The airport to be converted
     * @return GeoPosition object.
     */
    private GeoPosition getAirportGeoPosition(Airport airport) {
        Double latitude = airport.getLatitude();
        Double longitude = airport.getLongitude();

        //noinspection UnnecessaryLocalVariable
        GeoPosition geoPosition = new GeoPosition(latitude, longitude);

        return geoPosition;
    }
}
