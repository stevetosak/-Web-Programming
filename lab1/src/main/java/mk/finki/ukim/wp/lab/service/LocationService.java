package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Location;

import java.util.List;

public interface LocationService {
    List<Event> searchEventsByLocation(String location);

    List<Location> findAll();

    Location findById(Long locationId);

}
