
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

    if (donation.getDonationDate().isBefore(LocalDate.now())) {
        return "Donation date cannot be in the past";
    }

    if (donationRepository.existsByNurseIdAndDonationDateAndStatusNot(
            donation.getNurse().getId(),
            donation.getDonationDate(),
            DonationStatus.DEFERRED)) {

        return "Nurse already collects a donation on that date";
    }

    donation.setStatus(DonationStatus.REGISTERED);
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

public String updateStatus(UUID id, DonationStatus newStatus) {

    Optional<Donation> existing = donationRepository.findById(id);

    if (existing.isEmpty()) {
        return "The donation with that id does not exist";
    }

    Donation donation = existing.get();
    DonationStatus currentStatus = donation.getStatus();

    boolean allowed = false;

    if (currentStatus == DonationStatus.REGISTERED) {
        if (newStatus == DonationStatus.SCREENED ||
            newStatus == DonationStatus.DEFERRED) {
            allowed = true;
        }
    } else if (currentStatus == DonationStatus.SCREENED) {
        if (newStatus == DonationStatus.COLLECTED ||
            newStatus == DonationStatus.DEFERRED) {
            allowed = true;
        }
    }

    if (!allowed) {
        return "Cannot change status from " + currentStatus + " to " + newStatus;
    }

    donation.setStatus(newStatus);
    donationRepository.save(donation);

    return "Donation status updated successfully";
}

}
