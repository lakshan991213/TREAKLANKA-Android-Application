package com.example.treaklanka;

public class LocationsByType_Model {
    String Name;
    String City;
    String Point;
    String ImageURL;

    public LocationsByType_Model() {
    }

    public LocationsByType_Model(String name, String city, String point, String imageURL) {
        Name = name;
        City = city;
        Point = point;
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getPoint() {
        return Point;
    }

    public String getImageURL() {
        return ImageURL;
    }
}
