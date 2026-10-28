
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {

    List<Donor> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
