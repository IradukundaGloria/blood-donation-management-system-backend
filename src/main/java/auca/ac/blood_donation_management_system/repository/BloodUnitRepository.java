
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.BloodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface BloodUnitRepository extends JpaRepository<BloodUnit, UUID> {

    @Query("SELECT b FROM BloodUnit b WHERE b.donation.donor.donorNumber = :donorNumber ORDER BY b.collectedDate DESC")
    List<BloodUnit> findByDonorNumber(@Param("donorNumber") String donorNumber);

    boolean existsByDonationId(UUID donationId);
}
