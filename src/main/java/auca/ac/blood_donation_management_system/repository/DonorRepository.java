
package auca.ac.blood_donation_management_system.repository;

import auca.ac.blood_donation_management_system.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DonorRepository extends JpaRepository<Donor, UUID> {
    
@Query("SELECT DISTINCT d.donor FROM Donation d WHERE d.nurse.id = :nurseId")
List<Donor> findDonorsOfNurse(@Param("nurseId") UUID nurseId);


    List<Donor> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
