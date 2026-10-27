
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.DonationBed;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DonationBedRepository extends JpaRepository<DonationBed, UUID> {
}
