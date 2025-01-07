package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertManagementSystem {
    private List<Advert> adverts;
    private List<Advertiser> advertisers;
    private int nextAdvertId = 1;

    public AdvertManagementSystem() {
        this.adverts = new ArrayList<>();
        this.advertisers = new ArrayList<>();
    }

    public void addAdvert(Advert advert) {
        adverts.add(advert);
    }

    public void updateAdvert(Advert advert) {
        for (int i = 0; i < adverts.size(); i++) {
            if (adverts.get(i).getId() == advert.getId()) {
                adverts.set(i, advert);
                break;
            }
        }
    }

    public void addAdvertiser(Advertiser advertiser) {
        advertisers.add(advertiser);
    }

    public List<Advert> getAdvertsForIssue(LocalDate issueDate) {
        return adverts.stream()
                .filter(advert -> advert.getPublicationDate().equals(issueDate))
                .collect(Collectors.toList());
    }

    public void markAdvertAsPaid(int advertId) {
        adverts.stream()
                .filter(advert -> advert.getId() == advertId)
                .findFirst()
                .ifPresent(advert -> advert.setPaid(true));
    }

    public List<Advert> getUnpaidAdverts() {
        return adverts.stream()
                .filter(advert -> !advert.isPaid())
                .collect(Collectors.toList());
    }

    public int getNextAdvertId() {
        return nextAdvertId++;
    }

    public static void main(String[] args) {
        AdvertManagementSystem system = new AdvertManagementSystem();

        // Adding advertisers
        Advertiser advertiser1 = new Advertiser(1, "Tech Corp", "contact@techcorp.com");
        Advertiser advertiser2 = new Advertiser(2, "Gadget Inc", "info@gadgetinc.com");
        system.addAdvertiser(advertiser1);
        system.addAdvertiser(advertiser2);
        System.out.println("Advertisers added: " + advertiser1.getName() + ", " + advertiser2.getName());

        // Adding adverts
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Advert advert1 = new Advert(system.getNextAdvertId(), advertiser1, "Latest Tech Gadget", 2, "Front Page", today);
        Advert advert2 = new Advert(system.getNextAdvertId(), advertiser2, "Revolutionary Smartphone", 1, "Back Page", today);
        Advert advert3 = new Advert(system.getNextAdvertId(), advertiser1, "Tech Workshop", 1, "Middle Page", tomorrow);
        system.addAdvert(advert1);
        system.addAdvert(advert2);
        system.addAdvert(advert3);
        System.out.println("Adverts added: " + advert1.getContent() + ", " + advert2.getContent() + ", " + advert3.getContent());

        // Retrieving adverts for a specific issue date
        System.out.println("\nAdverts for today's issue:");
        List<Advert> todayAdverts = system.getAdvertsForIssue(today);
        todayAdverts.forEach(advert -> System.out.println(advert.getAdvertiser().getName() + ": " + advert.getContent()));

        // Marking adverts as paid
        system.markAdvertAsPaid(1);
        System.out.println("\nMarked advert 1 as paid");

        // Retrieving unpaid adverts
        System.out.println("\nUnpaid adverts:");
        List<Advert> unpaidAdverts = system.getUnpaidAdverts();
        unpaidAdverts.forEach(advert -> System.out.println(advert.getAdvertiser().getName() + ": " + advert.getContent()));
    }
}

