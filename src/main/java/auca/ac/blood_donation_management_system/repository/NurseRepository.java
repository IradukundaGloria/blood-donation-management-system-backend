
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface NurseRepository extends JpaRepository<Nurse, UUID> {
    
@Query("SELECT DISTINCT n FROM Nurse n JOIN n.certifications c WHERE LOWER(c.name) = LOWER(:name)")
List<Nurse> findByCertification(@Param("name") String name);

}
