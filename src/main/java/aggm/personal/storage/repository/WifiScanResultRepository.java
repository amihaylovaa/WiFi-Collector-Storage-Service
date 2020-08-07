package aggm.personal.storage.repository;

import aggm.personal.storage.domain.WifiScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WifiScanResultRepository extends JpaRepository<WifiScanResult, Long> {


}
