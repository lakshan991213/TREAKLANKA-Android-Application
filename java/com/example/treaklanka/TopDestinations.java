package com.example.treaklanka;

public class TopDestinations {
    private String Name;
    private String Imgurl;
    private String City;

    public TopDestinations() {

    }

    public TopDestinations(String name, String imgurl,String city ) {
        Name = name;
        Imgurl = imgurl;
        City= city;
    }

    public String getName() {
        return Name;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public String getCity() {
        return City;
    }
}
