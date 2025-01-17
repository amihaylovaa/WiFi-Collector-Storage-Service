package aggm.personal.storage.repository;

import aggm.personal.storage.domain.WifiLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WifiLocationRepository extends JpaRepository<WifiLocation, Long> {
}
