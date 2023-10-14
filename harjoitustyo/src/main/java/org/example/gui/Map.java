package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//CHECKSTYLE.ON: AvoidStarImport

import org.example.data.Airport;
import org.example.data.AirportData;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.*;

public class Map {
    // show the whole world at once
    private static final int DEFAULT_ZOOM_LEVEL = 16;

    // move the map center upwards to show the whole world better
    private static final double DEFAULT_MAP_LATITUDE = 20;

    // center on zero meridian
    private static final double DEFAULT_MAP_LONGITUDE = 0;
    private final AirportData airportData;

    private JXMapViewer mapViewer;
    private Set<Waypoint> waypoints;

    public Map(AirportData airportData) {
        this.airportData = airportData;
    }

    public void initMap() {
        initMapPanel();
        initMapTileset();
        initMapEvents();
        initMapPosition();

        // addAirports(); // FIXME add back
    }

    public void setRoute(ArrayList<Airport> route) {
        ArrayList<GeoPosition> positions = new ArrayList<>();

        for (Airport airport : route) {
            positions.add(getAirportGeoPosition(airport));
        }

        System.out.println(positions);

        Set<Waypoint> waypoints = new HashSet<>();

        positions.forEach(geoPosition -> {
            Waypoint waypoint = new DefaultWaypoint(geoPosition);
            waypoints.add(waypoint);
        });

        this.waypoints = waypoints;

        restoreWaypoints();
    }

    private void restoreWaypoints() {
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);

    }

    private void addAirports() {
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

//        ArrayList<Painter> painters = new ArrayList<>();
//        painters.add((Painter) waypointPainter);
//
////        ArrayList<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
////        painters.add(waypointPainter);
//        Painter compoundPainter = (Painter) new CompoundPainter(waypointPainter);
//        //noinspection unchecked
//        compoundPainter.setPainters(waypointPainter);

        // mapViewer.setOverlayPainter(waypointPainter);
    }

    private void initMapPosition() {
        // GeoPosition helsinki = new GeoPosition(60.182243, 24.952940);
        GeoPosition defaultMapCenter = new GeoPosition(DEFAULT_MAP_LATITUDE, DEFAULT_MAP_LONGITUDE);
        mapViewer.setZoom(DEFAULT_ZOOM_LEVEL);
        mapViewer.setAddressLocation(defaultMapCenter);
    }

    private void initMapTileset() {
        TileFactoryInfo osmInfo = new OSMTileFactoryInfo();
        TileFactory osmInfoFactory = new DefaultTileFactory(osmInfo);

        mapViewer.setLayout(new BorderLayout());

//        JLabel labelAttr = new JLabel("FOO123");
//        mapViewer.add(labelAttr, BorderLayout.SOUTH);

        mapViewer.setTileFactory(osmInfoFactory);

    }

    private void initMapPanel() {
        //noinspection UnnecessaryLocalVariable
        JXMapViewer mapViewer = new JXMapViewer();

        this.mapViewer = mapViewer;
    }

    private void initMapEvents() {
        MouseInputListener mia = new PanMouseInputListener(mapViewer);

        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
    }

    public JPanel getPanel() {
        return this.mapViewer;
    }

    private GeoPosition getAirportGeoPosition(Airport airport) {
        Double latitude = airport.getLatitude();
        Double longitude = airport.getLongitude();

        //noinspection UnnecessaryLocalVariable
        GeoPosition geoPosition = new GeoPosition(latitude, longitude);

        return geoPosition;
    }
}
