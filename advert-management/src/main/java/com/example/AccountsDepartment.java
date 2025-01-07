package com.example;

import java.util.List;

public class AccountsDepartment {
    private AdvertManagementSystem adSystem;

    public AccountsDepartment(AdvertManagementSystem adSystem) {
        this.adSystem = adSystem;
    }

    public double invoiceAdvertiser(Advertiser advertiser) {
        if (advertiser == null) {
            throw new IllegalArgumentException("Advertiser cannot be null");
        }
        List<Advert> unpaidAdverts = adSystem.getUnpaidAdverts().stream()
                .filter(advert -> advert.getAdvertiser().equals(advertiser))
                .toList();
        double totalAmount = unpaidAdverts.stream().mapToDouble(Advert::getPrice).sum();
        System.out.println("Invoice created for " + advertiser.getName() + ": $" + totalAmount);
        return totalAmount;
    }

    public void initiateDebtCollection(Advertiser advertiser) {
        if (advertiser == null) {
            throw new IllegalArgumentException("Advertiser cannot be null");
        }
        List<Advert> overdueAdverts = adSystem.getUnpaidAdverts().stream()
                .filter(advert -> advert.getAdvertiser().equals(advertiser) && advert.getDaysSincePublication() > 60)
                .toList();
        if (!overdueAdverts.isEmpty()) {
            System.out.println("Initiating debt collection for " + advertiser.getName());
            overdueAdverts.forEach(advert -> System.out.println("Overdue advert: " + advert.getId()));
        }
    }
}

