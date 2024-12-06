//package mk.finki.ukim.wp.lab.data;
//
//import jakarta.annotation.PostConstruct;
//import mk.finki.ukim.wp.lab.entity.Event;
//import mk.finki.ukim.wp.lab.entity.Location;
//import mk.finki.ukim.wp.lab.repository.EventRepositoryDB;
//import mk.finki.ukim.wp.lab.repository.LocationRepositoryDB;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@DependsOn("locationDataInit")
//public class EventDataHolder implements CommandLineRunner {
//
//
//    @Autowired
//    private EventRepositoryDB eventRepository;
//    @Autowired
//    private LocationRepositoryDB locationRepository;
//    private static final int num_events = 90000;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        Random random = new Random();
//        String[] names = {"Disco","Party","Concert","Theater","Conference","Coffee"};
//        String[] desc = {"Fun","People","Quiet","Exciting","Business"};
//
//        List<Location> locations = locationRepository.findAll();
//
//        System.out.println("SIZE " + locations.size());
//
//        for(int i = 0; i < num_events; i++){
//            Event event = new Event();
//            String name = names[random.nextInt(names.length)];
//            String description = desc[random.nextInt(desc.length)];
//            event.setId(i);
//            event.setName(name + random.nextInt(10));
//            event.setDescription(description);
//            event.setPopularityScore(random.nextInt(90) + 10);
//            Location location = locations.get(random.nextInt(locations.size()));
//            event.setLocation(location);
//            eventRepository.save(event);
//        }
//    }
//}
