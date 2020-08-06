package repository;

import domain.WifiLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface WifiLocationRepository extends JpaRepository<WifiLocation, Long> {
}
