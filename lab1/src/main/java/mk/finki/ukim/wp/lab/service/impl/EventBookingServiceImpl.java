package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.entity.EventBooking;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return null;
    }
}
