
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.DonationStatus;
import auca.ac.blood_donation_management_system.repository.DonorRepository;
import auca.ac.blood_donation_management_system.repository.NurseRepository;
import auca.ac.blood_donation_management_system.repository.DonationBedRepository;
import auca.ac.blood_donation_management_system.repository.DonationRepository;
import auca.ac.blood_donation_management_system.repository.BloodUnitRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final DonorRepository donorRepository;
    private final NurseRepository nurseRepository;
    private final DonationBedRepository donationBedRepository;
    private final DonationRepository donationRepository;
    private final BloodUnitRepository bloodUnitRepository;

    public DashboardService(
            DonorRepository donorRepository,
            NurseRepository nurseRepository,
            DonationBedRepository donationBedRepository,
            DonationRepository donationRepository,
            BloodUnitRepository bloodUnitRepository) {

        this.donorRepository = donorRepository;
        this.nurseRepository = nurseRepository;
        this.donationBedRepository = donationBedRepository;
        this.donationRepository = donationRepository;
        this.bloodUnitRepository = bloodUnitRepository;
    }

    public Map<String, Long> getSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("donors", donorRepository.count());
        summary.put("nurses", nurseRepository.count());
        summary.put("freeBeds",
                (long) donationBedRepository.findFreeBeds().size());
        summary.put("donationsToday",
                donationRepository.countByDonationDate(LocalDate.now()));
        summary.put("registered",
                donationRepository.countByStatus(DonationStatus.REGISTERED));
        summary.put("bloodUnits", bloodUnitRepository.count());

        return summary;
    }
}
