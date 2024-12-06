package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.entity.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepositoryDB extends JpaRepository<Event, Long> {
    Optional<List<Event>> findAllByLocationId(Integer locationId);
    Optional<List<Event>> findAllByPopularityScoreIsGreaterThanEqual(Double popularityScore);

    @Query("FROM Event e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%',:text,'%')) OR LOWER(e.description) LIKE LOWER(CONCAT('%',:text,'%'))")
    Optional<List<Event>> findAllByNameContainsOrDescriptionContainsIgnoreCase(@Param("text") String text);
    @Query("FROM Event e WHERE LOWER(e.location.name) LIKE LOWER(CONCAT('%',:locationName,'%')) ")
    Optional<List<Event>> findAllByLocationNameContainsIgnoreCase(@Param("locationName") String locationName);

    Optional<Event> getEventById(Integer id);

}
