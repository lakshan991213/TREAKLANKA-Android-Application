package com.example.treaklanka;

public class FavoriteModel {
    public String name;
    public String district;

    public FavoriteModel() {
    }

    public FavoriteModel(String name, String district) {
        this.name = name;
        this.district = district;
    }

    public  String getName() {
        return name;
    }

    public  String getDistrict() {
        return district;
    }
}
