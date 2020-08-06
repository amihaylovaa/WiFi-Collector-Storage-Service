package repository;

import domain.WifiScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface WifiScanResultRepository extends JpaRepository<WifiScanResult, Long> {


}
