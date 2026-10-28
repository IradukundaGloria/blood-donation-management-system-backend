
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.DonationBed;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface DonationBedRepository extends JpaRepository<DonationBed, UUID> {
    
@Query("SELECT b FROM DonationBed b WHERE NOT EXISTS (SELECT n FROM Nurse n WHERE n.donationBed = b) ORDER BY b.bedNumber ASC")
List<DonationBed> findFreeBeds();

}
