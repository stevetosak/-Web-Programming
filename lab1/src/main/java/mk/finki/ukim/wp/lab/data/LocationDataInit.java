package mk.finki.ukim.wp.lab.data;

import mk.finki.ukim.wp.lab.entity.Event;
import mk.finki.ukim.wp.lab.entity.Location;
import mk.finki.ukim.wp.lab.repository.EventRepositoryDB;
import mk.finki.ukim.wp.lab.repository.LocationRepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class LocationDataInit implements CommandLineRunner {

    @Autowired
    private EventRepositoryDB eventRepository;
    private final LocationRepositoryDB locationRepositoryDB;

    private static final int num_events = 90000;

    public LocationDataInit(LocationRepositoryDB repository) {
        this.locationRepositoryDB = repository;
    }

    @Override
    public void run(String... args) {
        Random random = new Random();

        String[] names = new String[]{
                "Emberwood Heights",
                "Azure Cove Marina",
                "Shadowcliff Fortress",
                "Crystal Hollow Springs",
                "Ironwood Market Square"
        };

        String[] addresses = new String[]{
                "1452 Redwood Crescent, Emberwood, CA 90210",
                "99 Harbor Lane, Azure Cove, FL 33101",
                "870 Darkstone Pass, Shadowcliff, MT 59001",
                "3101 Mineral Avenue, Crystal Hollow, NV 89701",
                "540 Commerce Street, Ironwood, TX 75001"
        };

        String[] descriptions = new String[]{
                "A tranquil neighborhood nestled within towering redwood trees, known for its scenic hiking trails and serene atmosphere.",
                "A bustling waterfront marina offering boat rentals, fresh seafood restaurants, and a lively boardwalk.",
                "An ancient stronghold perched on a rocky cliff, surrounded by legends of hidden treasures and ghostly apparitions.",
                "A picturesque spa town famous for its crystal-clear hot springs and holistic wellness retreats.",
                "A vibrant downtown area with eclectic shops, street performers, and a weekly farmers' market."
        };

        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Location location = new Location();
            location.setName(names[random.nextInt(names.length)] + i);
            location.setAddress(addresses[random.nextInt(addresses.length)]);
            location.setDescription(descriptions[random.nextInt(descriptions.length)]);
            locations.add(location);
        }

        locationRepositoryDB.saveAll(locations);

        System.out.println("Sample locations initialized in the database.");


        String[] evtNames = {"Disco", "Party", "Concert", "Theater", "Conference", "Coffee"};
        String[] desc = {"Fun", "People", "Quiet", "Exciting", "Business"};



        for (int i = 0; i < 20; i++) {
            Event event = new Event();
            String name = evtNames[random.nextInt(evtNames.length)];
            String description = desc[random.nextInt(desc.length)];
            event.setName(name + random.nextInt(10));
            event.setDescription(description);
            event.setPopularityScore(random.nextInt(90) + 10);
            Location location = locations.get(random.nextInt(locations.size()));
            event.setLocation(location);
            eventRepository.save(event);

        }
    }
}
