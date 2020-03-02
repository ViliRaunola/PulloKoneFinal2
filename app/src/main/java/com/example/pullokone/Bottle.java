package com.example.pullokone;

public class Bottle {
    private String name;

    private String manufacturer;

    private double size;

    private double price;

    private int id;


    public Bottle(){

    }

    public Bottle(String nam, String manuf, double siz, double pri, int i){
        name = nam;
        manufacturer = manuf;
        size = siz;
        price = pri;
        id = i;
    }



    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getPrice(){
        return price;
    }

    public double getSize() {
        return size;
    }
    @Override
    public String toString(){
        String temp2 = String.valueOf(size);
        String temp = name + " " + temp2;
        return temp;
    }

}
