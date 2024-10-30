package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll(){
        return EventList.events;
    }

    public List<Event> searchEvents(String text){
        return EventList.events.stream()
                .filter(e -> e.getName().toLowerCase().contains(text) || e.getDescription().toLowerCase().contains(text))
                .collect(Collectors.toList());
    }


    public List<Event> getEventsByRatingGTE(int rating) {
        return EventList.events.stream()
                .filter(e -> e.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }
}
