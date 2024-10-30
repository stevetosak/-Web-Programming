package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEventsByText(String text);
    List<Event> searchEventsByRating(String rating);
    List<Event> dynamicSearch(String text, String rating);
}
