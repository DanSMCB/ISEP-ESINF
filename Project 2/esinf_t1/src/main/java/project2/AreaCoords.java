package project2;

import java.util.Objects;

public class AreaCoords {

    private String countryCode;
    private String areaCoords;
    private double latitude;
    private double longitude;

    public AreaCoords(String country, String area, double latitude, double longitude) {
        this.countryCode = country;
        this.areaCoords = area;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String country) {
        this.countryCode = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAreaCoords() {
        return areaCoords;
    }

    public void setAreaCoords(String area) {
        this.areaCoords = area;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaCoords)) return false;
        AreaCoords that = (AreaCoords) o;
        return Objects.equals(areaCoords, that.areaCoords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, areaCoords, latitude, longitude);
    }
}
