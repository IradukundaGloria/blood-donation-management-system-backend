
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface CertificationRepository extends JpaRepository<Certification, UUID> {
    
@Query("SELECT c FROM Certification c WHERE c.nurses IS EMPTY")
List<Certification> findUnusedCertifications();

}
