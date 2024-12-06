package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Location;
import mk.finki.ukim.wp.lab.repository.EventRepositoryDB;
import mk.finki.ukim.wp.lab.repository.LocationRepositoryDB;
import mk.finki.ukim.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final EventRepositoryDB eventRepository;

    private final LocationRepositoryDB locationRepository;

    public LocationServiceImpl(EventRepositoryDB repository, LocationRepositoryDB locationRepository) {
        this.eventRepository = repository;
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Event> searchEventsByLocation(String locationName) {
        if(locationName == null || locationName.isEmpty()) {
            return new ArrayList<>();
        }
        Optional<List<Event>> result = eventRepository.findAllByLocationNameContainsIgnoreCase(locationName);

        return result.orElse(new ArrayList<>());
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }
}
