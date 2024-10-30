package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;


    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
        return eventRepository.searchEvents(text.toLowerCase());
    }

    @Override
    public List<Event> searchEventsByRating(String rating) {
        if (rating == null || rating.isEmpty() || !isNumber(rating)) {
            return new ArrayList<>();
        }
        return eventRepository.getEventsByRatingGTE(Integer.parseInt(rating));
    }

    @Override
    public List<Event> dynamicSearch(String text, String rating) {
        if ((text == null || text.isEmpty()) && (rating == null || rating.isEmpty())) {
            return new ArrayList<>();
        }

        List<List<Event>> pool = new ArrayList<>();

        pool.add(searchEventsByText(text));
        pool.add(searchEventsByRating(rating));

        pool.removeIf(List::isEmpty);

        if(pool.isEmpty()){
            return new ArrayList<>();
        }

        return joinH(pool);

    }


    //v3
    private static List<Event> joinH(List<List<Event>> rs) {
        Set<Event> join = new HashSet<>(rs.get(0));
        for (int i = 1; i < rs.size(); i++) {
            Set<Event> currentSet = new HashSet<>(rs.get(i));
            if (join.isEmpty()) {
                join = new HashSet<>(currentSet);
            } else {
                join.retainAll(currentSet);
            }
        }
        return new ArrayList<>(join);
    }


    //v2
    private static List<Event> joinL(List<List<Event>> rs) {
        List<Event> join = new ArrayList<>(rs.get(0));
        for (int i = 1; i < rs.size(); i++) {
            List<Event> currentSet = new ArrayList<>(rs.get(i));
            if (join.isEmpty()) {
                join = new ArrayList<>(currentSet);
            } else {
                join.retainAll(currentSet);
            }
        }
        return new ArrayList<>(join);
    }


    //v1

    //    @Override
//    public List<Event> dynamicSearch(String text, String rating) {
//        if ((text == null || text.isEmpty()) && (rating == null || rating.isEmpty())) {
//            return new ArrayList<>();
//        }
//
//
//        // ustvari ke ti dajt najdobar match so rabotite so gi imas dadeno; ne morat se da imas vneseno
//
//        List<List<Event>> rs = new ArrayList<>();
//        List<Event> join;
//        rs.add(searchEventsByText(text));
//        rs.add(searchEventsByRating(rating));
//
//        for (int i = 0; i < rs.size() - 1; i++) {
//            List<Event> curr = rs.get(i);
//            List<Event> next = rs.get(i + 1);
//
//            if (curr.isEmpty() && next.isEmpty()) {
//                continue;
//            }
//
//            join = new ArrayList<>(next);
//
//            if (curr.isEmpty()) {
//                join = new ArrayList<>(next);
//            } else if (next.isEmpty()) {
//                join = new ArrayList<>(curr);
//            } else {
//                join.retainAll(curr);
//            }
//
//            rs.set(i + 1, join);
//        }
//
//
//        return rs.get(rs.size() - 1);
//
//    }

}
