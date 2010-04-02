package org.gtug.karlsruhe.bunnycacher.server.domain;

/**
 * Geohash value object.
 */
public class Geohash {

    private double latitude;
    private double longitude;

    public Geohash(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
