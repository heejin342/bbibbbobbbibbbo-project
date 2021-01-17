package com.example.finalapplicaiton;

public class Beacon {
    private String address;
    private int rssi;
    private int txPower;
    private double distance;
    private String now;
    private String name;

    public Beacon(String address, int rssi, String now, String name, int txPower, double distance){
        this.address=address;
        this.rssi=rssi;
        this.now=now;
        this.name=name;
        this.txPower=txPower;
        this.distance=distance;
    }

    public String getAddress() {
        return address;
    }

    public int getRssi() {
        return rssi;
    }

    public String getNow() {
        return now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setNow(String now) {
        this.now = now;
    }


    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}