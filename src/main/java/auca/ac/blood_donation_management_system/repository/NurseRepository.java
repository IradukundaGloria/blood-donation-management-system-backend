
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NurseRepository extends JpaRepository<Nurse, UUID> {
}
