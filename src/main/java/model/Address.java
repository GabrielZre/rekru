package model;

public class Address {
    LocationLatLong geolocation;
    String city;
    String street;
    int number;
    String zipcode;

    public LocationLatLong getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(LocationLatLong geolocation) {
        this.geolocation = geolocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Address() {}

    public Address(LocationLatLong geolocation, String city, String street, int number, String zipcode) {
        this.geolocation = geolocation;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }
}
