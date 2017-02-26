package com.lunchlist;

/**
 * Created by vanthanhbk on 14/02/2017.
 */

public class Restaurent {
    private String name;
    private String address;
    private String type;
    private String notes;

    public Restaurent(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Restaurent(String name, String address, String type, String notes) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.notes = notes;
    }

    public Restaurent(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurent{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
