package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.entity.EventBooking;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
