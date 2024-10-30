package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.EventBooking;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return null;
    }
}
