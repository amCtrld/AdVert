package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AdvertManagementSystemTest {
    private AdvertManagementSystem system;
    private Advertiser advertiser1;
    private Advertiser advertiser2;

    @BeforeEach
    void setUp() {
        system = new AdvertManagementSystem();
        advertiser1 = new Advertiser(1, "Tech Corp", "contact@techcorp.com");
        advertiser2 = new Advertiser(2, "Gadget Inc", "info@gadgetinc.com");
        system.addAdvertiser(advertiser1);
        system.addAdvertiser(advertiser2);
    }

    @Test
    void testAddAdvert() {
        Advert advert = new Advert(system.getNextAdvertId(), advertiser1, "Test Advert", 1, "Front Page", LocalDate.now());
        system.addAdvert(advert);
        List<Advert> adverts = system.getAdvertsForIssue(LocalDate.now());
        assertTrue(adverts.contains(advert));
    }

    @Test
    void testUpdateAdvert() {
        Advert advert = new Advert(system.getNextAdvertId(), advertiser1, "Test Advert", 1, "Front Page", LocalDate.now());
        system.addAdvert(advert);
        advert.setApproved(true);
        system.updateAdvert(advert);
        List<Advert> adverts = system.getAdvertsForIssue(LocalDate.now());
        assertTrue(adverts.stream().anyMatch(a -> a.isApproved()));
    }

    @Test
    void testGetAdvertsForIssue() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        system.addAdvert(new Advert(system.getNextAdvertId(), advertiser1, "Today's Advert", 1, "Front Page", today));
        system.addAdvert(new Advert(system.getNextAdvertId(), advertiser2, "Tomorrow's Advert", 1, "Back Page", tomorrow));
        
        List<Advert> todayAdverts = system.getAdvertsForIssue(today);
        assertEquals(1, todayAdverts.size());
        assertEquals("Today's Advert", todayAdverts.get(0).getContent());
    }

    @Test
    void testMarkAdvertAsPaid() {
        Advert advert = new Advert(system.getNextAdvertId(), advertiser1, "Test Advert", 1, "Front Page", LocalDate.now());
        system.addAdvert(advert);
        system.markAdvertAsPaid(advert.getId());
        List<Advert> unpaidAdverts = system.getUnpaidAdverts();
        assertFalse(unpaidAdverts.contains(advert));
    }

    @Test
    void testGetUnpaidAdverts() {
        system.addAdvert(new Advert(system.getNextAdvertId(), advertiser1, "Paid Advert", 1, "Front Page", LocalDate.now()));
        Advert unpaidAdvert = new Advert(system.getNextAdvertId(), advertiser2, "Unpaid Advert", 1, "Back Page", LocalDate.now());
        system.addAdvert(unpaidAdvert);
        system.markAdvertAsPaid(1);
        List<Advert> unpaidAdverts = system.getUnpaidAdverts();
        assertEquals(1, unpaidAdverts.size());
        assertEquals("Unpaid Advert", unpaidAdverts.get(0).getContent());
    }
}

