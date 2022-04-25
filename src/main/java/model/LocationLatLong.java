package model;



public class LocationLatLong {
    double lat;
    double longitude;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocationLatLong() {}

    public LocationLatLong(double latitude, double longtitude) {
        this.lat = latitude;
        this.longitude = longtitude;
    }
}
