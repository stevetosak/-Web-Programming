package mk.finki.ukim.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Event;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class EventList {

    public static List<Event>  events = new ArrayList<>();


    @PostConstruct
    public void init(){
        Random random = new Random();
        String[] names = {"Disco","Party","Concert","Theater"};
        String[] desc = {"Fun","People","Quiet"};

        for(int i = 0; i < 10000; i++){
            Event event = new Event();
            String name = names[random.nextInt(names.length)];
            String description = desc[random.nextInt(desc.length)];
            event.setName(name + random.nextInt(10));
            event.setDescription(description);
            event.setPopularityScore(random.nextInt(90) + 10);
            events.add(event);
        }

    }
}
