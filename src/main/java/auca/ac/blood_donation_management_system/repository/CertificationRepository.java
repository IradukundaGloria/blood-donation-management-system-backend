
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {
}
