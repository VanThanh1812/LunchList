package com.lunchlist;

/**
 * Created by vanthanhbk on 14/02/2017.
 */

public class Items {
    private String name;
    private String number;
    private String unit_price;
    private String barCode;
    private String id_giamgia;

    public Items(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Items(String name, String number, String unit_price, String barCode, String id) {
        this.name = name;
        this.id_giamgia = id;
        this.number = number;
        this.unit_price = unit_price;
        this.barCode = barCode;
    }

    public Items(String name, String number, String unit_price) {
        this.name = name;
        this.number = number;
        this.unit_price = unit_price;
    }

    public Items(String name, String number, String unit_price, String barCode) {
        this.name = name;
        this.number = number;
        this.unit_price = unit_price;
        this.barCode = barCode;
    }

    public String getId_giamgia() {
        return id_giamgia;
    }

    public void setId_giamgia(String id_giamgia) {
        this.id_giamgia = id_giamgia;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Items{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
