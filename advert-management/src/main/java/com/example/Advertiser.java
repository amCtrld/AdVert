package com.example;

public class Advertiser {
    private int id;
    private String name;
    private String contactInfo;

    public Advertiser(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
}

