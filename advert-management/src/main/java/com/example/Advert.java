package com.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Advert {
    private int id;
    private Advertiser advertiser;
    private String content;
    private int size;
    private String position;
    private LocalDate publicationDate;
    private boolean isPaid;
    private boolean isApproved;
    private double price;

    public Advert(int id, Advertiser advertiser, String content, int size, String position, LocalDate publicationDate) {
        this.id = id;
        this.advertiser = advertiser;
        this.content = content;
        this.size = size;
        this.position = position;
        this.publicationDate = publicationDate;
        this.isPaid = false;
        this.isApproved = false;
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        double basePrice = 100 * size;
        if ("Front Page".equals(position)) {
            basePrice *= 2;
        }
        return basePrice;
    }

    public int getId() { return id; }
    public Advertiser getAdvertiser() { return advertiser; }
    public String getContent() { return content; }
    public int getSize() { return size; }
    public String getPosition() { return position; }
    public LocalDate getPublicationDate() { return publicationDate; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { isApproved = approved; }
    public double getPrice() { return price; }

    public long getDaysSincePublication() {
        return ChronoUnit.DAYS.between(publicationDate, LocalDate.now());
    }
}

