package com.unitedremote.store.service.util;

public class Location {
    private Double longitude;
    private Double latitude;

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double distanceTo(Location location) {
        if ((this.getLatitude() == location.getLatitude()) && (this.getLongitude() == location.getLongitude())) {
            return 0D;
        }
        else {
            Double theta = this.getLongitude() - location.getLongitude();
            Double dist = Math.sin(Math.toRadians(this.getLatitude()))
                * Math.sin(Math.toRadians(location.getLatitude()))
                + Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(location.getLatitude()))
                * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * ((2 * Math.PI * 6371) / 360);
            return (dist);
        }
    }
}
