
package auca.ac.blood_donation_management_system.service;


import auca.ac.blood_donation_management_system.entity.Donation;
import auca.ac.blood_donation_management_system.entity.DonationStatus;
import auca.ac.blood_donation_management_system.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public String save(Donation donation) {
        donationRepository.save(donation);
        return "Donation saved successfully";
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public Optional<Donation> findById(UUID id) {
        return donationRepository.findById(id);
    }

    public String update(UUID id, Donation donation) {
        Optional<Donation> existing = donationRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donation with that id does not exist";
        }

        Donation saved = existing.get();
        saved.setDonationDate(donation.getDonationDate());
        saved.setStatus(donation.getStatus());
        saved.setHemoglobinLevel(donation.getHemoglobinLevel());
        saved.setDonor(donation.getDonor());
        saved.setNurse(donation.getNurse());

        donationRepository.save(saved);
        return "Donation updated successfully";
    }

    public String delete(UUID id) {
        Optional<Donation> existing = donationRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donation with that id does not exist";
        }

        donationRepository.deleteById(id);
        return "Donation deleted successfully";
    }
    
public List<Donation> findByStatus(DonationStatus status) {
    return donationRepository.findByStatusOrderByDonationDateAsc(status);
}

public List<Donation> findBetween(LocalDate start, LocalDate end) {
    return donationRepository.findByDonationDateBetweenOrderByDonationDateAsc(start, end);
}

}
