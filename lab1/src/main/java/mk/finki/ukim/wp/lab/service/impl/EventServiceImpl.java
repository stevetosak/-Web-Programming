package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Location;
import mk.finki.ukim.wp.lab.repository.EventRepositoryDB;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.LocationService;
import mk.finki.ukim.wp.lab.util.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {
    //private final EventRepository eventRepository;
    private final EventRepositoryDB eventRepository;
    private final LocationService locationService;

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Autowired
    public EventServiceImpl(LocationService locationService,EventRepositoryDB eventRepository) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEventsByText(String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }

        Optional<List<Event>> result = eventRepository.findAllByNameContainsOrDescriptionContainsIgnoreCase(text);
        return result.orElseGet(ArrayList::new);
    }

    @Override
    public List<Event> searchEventsByRating(String rating) {
        if (rating == null || rating.isEmpty() || !isNumber(rating)) {
            return new ArrayList<>();
        }

        Optional<List<Event>> result = eventRepository.findAllByPopularityScoreIsGreaterThanEqual(Double.parseDouble(rating));
        return result.orElseGet(ArrayList::new);
    }

    @Override
    public List<Event> dynamicSearch(String text, String rating, String locationName) {
        if ((text == null || text.isEmpty()) && (rating == null || rating.isEmpty()) && (locationName == null || locationName.isEmpty())) {
            return new ArrayList<>();
        }

        List<List<Event>> pool = new ArrayList<>();

        long start = System.nanoTime();
        pool.add(searchEventsByText(text));
        long end = System.nanoTime();

        System.out.println("Time for search query event text: " + ((end - start) / (Math.pow(10,6))) + "ms");

        start = System.nanoTime();
        pool.add(searchEventsByRating(rating));
        end = System.nanoTime();

        System.out.println("Time for search query event rating: " + ((end - start) / (Math.pow(10,6))) + "ms");


        start = System.nanoTime();
        pool.add(locationService.searchEventsByLocation(locationName));
        end = System.nanoTime();

        System.out.println("Time for search query location text: " + ((end - start) / (Math.pow(10,6))) + "ms");

        pool.removeIf(List::isEmpty);

        if(pool.isEmpty()){
            return new ArrayList<>();
        }

        return SearchQuery.joinH(pool);

    }


    @Override
    public Event getEventById(Long id) {
      Optional<Event> event = eventRepository.findById(id);
      return event.orElseThrow(() -> new IllegalArgumentException("Could not find event with id: " + id));
    }

    @Override
    public void update(Long eventId, String name, String description, double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event newEvent = new Event(Math.toIntExact(eventId),name,description,popularityScore,location);
        eventRepository.save(newEvent);
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void add(String name, String description, double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event event = new Event(name,description,popularityScore,location);
        eventRepository.save(event);
    }


}
