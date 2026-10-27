
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.DonationBed;
import auca.ac.blood_donation_management_system.repository.DonationBedRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonationBedService {

    private final DonationBedRepository donationBedRepository;

    public DonationBedService(DonationBedRepository donationBedRepository) {
        this.donationBedRepository = donationBedRepository;
    }

    public String save(DonationBed donationBed) {
        donationBedRepository.save(donationBed);
        return "Donation bed saved successfully";
    }

    public List<DonationBed> findAll() {
        return donationBedRepository.findAll();
    }

    public Optional<DonationBed> findById(UUID id) {
        return donationBedRepository.findById(id);
    }

    public String update(UUID id, DonationBed donationBed) {
        Optional<DonationBed> existing = donationBedRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donation bed with that id does not exist";
        }

        DonationBed saved = existing.get();
        saved.setBedNumber(donationBed.getBedNumber());
        saved.setName(donationBed.getName());

        donationBedRepository.save(saved);
        return "Donation bed updated successfully";
    }

    public String delete(UUID id) {
        Optional<DonationBed> existing = donationBedRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donation bed with that id does not exist";
        }

        donationBedRepository.deleteById(id);
        return "Donation bed deleted successfully";
    }
}
