
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.BloodUnit;
import auca.ac.blood_donation_management_system.entity.Donation;
import auca.ac.blood_donation_management_system.entity.DonationStatus;
import auca.ac.blood_donation_management_system.repository.BloodUnitRepository;
import auca.ac.blood_donation_management_system.repository.DonationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BloodUnitService {

    private final BloodUnitRepository bloodUnitRepository;
    private final DonationRepository donationRepository;

    public BloodUnitService(BloodUnitRepository bloodUnitRepository,
                            DonationRepository donationRepository) {
        this.bloodUnitRepository = bloodUnitRepository;
        this.donationRepository = donationRepository;
    }

    public String save(UUID donationId, BloodUnit bloodUnit) {

        Optional<Donation> existing = donationRepository.findById(donationId);

        if (existing.isEmpty()) {
            return "The donation with that id does not exist";
        }

        Donation donation = existing.get();

        if (donation.getStatus() != DonationStatus.COLLECTED) {
            return "Blood units are only for COLLECTED donations";
        }

        bloodUnit.setDonation(donation);
        bloodUnit.setCollectedDate(LocalDate.now());
        bloodUnitRepository.save(bloodUnit);

        return "Blood unit saved successfully";
    }

    public List<BloodUnit> findByDonorNumber(String donorNumber) {
        return bloodUnitRepository.findByDonorNumber(donorNumber);
    }

    public String delete(UUID id) {

        if (!bloodUnitRepository.existsById(id)) {
            return "The blood unit with that id does not exist";
        }

        bloodUnitRepository.deleteById(id);

        return "Blood unit deleted successfully";
    }
}
