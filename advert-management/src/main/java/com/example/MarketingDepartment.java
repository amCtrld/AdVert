package com.example;

import java.time.LocalDate;

public class MarketingDepartment {
    private AdvertManagementSystem adSystem;

    public MarketingDepartment(AdvertManagementSystem adSystem) {
        this.adSystem = adSystem;
    }

    public Advert receiveAdvertDetails(Advertiser advertiser, String content, int size, String position, LocalDate publicationDate) {
        if (advertiser == null || content == null || size <= 0 || position == null || publicationDate == null) {
            throw new IllegalArgumentException("Invalid advert details");
        }
        int newId = adSystem.getNextAdvertId();
        Advert newAdvert = new Advert(newId, advertiser, content, size, position, publicationDate);
        adSystem.addAdvert(newAdvert);
        return newAdvert;
    }

    public void sendAdvertToEditor(Advert advert) {
        if (advert == null) {
            throw new IllegalArgumentException("Advert cannot be null");
        }
        advert.setApproved(true);
    }
}

