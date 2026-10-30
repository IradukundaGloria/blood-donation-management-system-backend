
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Donation;
import auca.ac.blood_donation_management_system.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DonationRepository extends JpaRepository<Donation, UUID> {

    List<Donation> findByStatusOrderByDonationDateAsc(DonationStatus status);

    List<Donation> findByDonationDateBetweenOrderByDonationDateAsc(
            LocalDate start, LocalDate end);
            
boolean existsByNurseIdAndDonationDateAndStatusNot(
        UUID nurseId,
        LocalDate donationDate,
        DonationStatus status
);

@Query("SELECT d.status, COUNT(d) FROM Donation d GROUP BY d.status")
List<Object[]> countDonationsByStatus();


@Modifying
@Query("UPDATE Donation d SET d.status = :deferred WHERE d.nurse.id = :nurseId AND d.donationDate = :date AND d.status <> :collected")
int cancelDonationsByNurseAndDate(
        @Param("nurseId") UUID nurseId,
        @Param("date") LocalDate date,
        @Param("deferred") DonationStatus deferred,
        @Param("collected") DonationStatus collected
);

long countByDonationDate(LocalDate donationDate);

long countByStatus(DonationStatus status);


}
