package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEventsByText(String text);
    List<Event> searchEventsByRating(String rating);
    List<Event> dynamicSearch(String text, String rating, String locationName);

    Event getEventById(Long id);
    void update(Long eventId, String name, String description, double popularityScore, Long locationId);
    void delete(Long eventId);
    void add(String name, String description, double popularityScore, Long locationId);
}
