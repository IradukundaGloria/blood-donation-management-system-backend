
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Donation;
import auca.ac.blood_donation_management_system.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DonationRepository extends JpaRepository<Donation, UUID> {

    List<Donation> findByStatusOrderByDonationDateAsc(DonationStatus status);

    List<Donation> findByDonationDateBetweenOrderByDonationDateAsc(
            LocalDate start, LocalDate end);
}
