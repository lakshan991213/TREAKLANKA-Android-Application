package com.example.treaklanka;

public class PlaceByCity {
    private String Name;
    private String Point;
    private String Description1;
    private String Description2;
    private String ImageURL;

    public PlaceByCity() {
    }


    public PlaceByCity(String name, String point, String description1, String description2, String imageURL) {
        Name = name;
        Point = Point;
        Description1 = description1;
        Description2 = description2;
        ImageURL = imageURL;
    }


    public String getName() {
        return Name;
    }

    public String getPoint() {
        return Point;
    }

    public String getDescription1() {
        return Description1;
    }

    public String getDescription2() {
        return Description2;
    }

    public String getImageURL() {
        return ImageURL;
    }
}
