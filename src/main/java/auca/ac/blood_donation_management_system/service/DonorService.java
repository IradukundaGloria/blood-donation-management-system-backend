
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.Donor;
import auca.ac.blood_donation_management_system.repository.DonorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public String save(Donor donor) {
        donorRepository.save(donor);
        return "Donor saved successfully";
    }

    public List<Donor> findAll() {
        return donorRepository.findAll();
    }

    public Optional<Donor> findById(UUID id) {
        return donorRepository.findById(id);
    }

    public String update(UUID id, Donor donor) {
        Optional<Donor> existing = donorRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donor with that id does not exist";
        }

        Donor saved = existing.get();
        saved.setFirstName(donor.getFirstName());
        saved.setLastName(donor.getLastName());
        saved.setDonorNumber(donor.getDonorNumber());
        saved.setBloodGroup(donor.getBloodGroup());
        saved.setPhone(donor.getPhone());

        donorRepository.save(saved);
        return "Donor updated successfully";
    }

    public String delete(UUID id) {
        Optional<Donor> existing = donorRepository.findById(id);

        if (existing.isEmpty()) {
            return "The donor with that id does not exist";
        }

        donorRepository.deleteById(id);
        return "Donor deleted successfully";
    }
    
public List<Donor> findByLastName(String lastName) {
    return donorRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}

}
