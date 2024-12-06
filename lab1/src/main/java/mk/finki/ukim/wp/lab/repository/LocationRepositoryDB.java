package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepositoryDB extends JpaRepository<Location, Long> {
    Optional<Location> getLocationById(Long id);
}
