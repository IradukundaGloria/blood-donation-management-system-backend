
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.Nurse;
import auca.ac.blood_donation_management_system.repository.NurseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public String save(Nurse nurse) {
        nurseRepository.save(nurse);
        return "Nurse saved successfully";
    }

    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    public Optional<Nurse> findById(UUID id) {
        return nurseRepository.findById(id);
    }

    public String update(UUID id, Nurse nurse) {
        Optional<Nurse> existing = nurseRepository.findById(id);

        if (existing.isEmpty()) {
            return "The nurse with that id does not exist";
        }

        Nurse saved = existing.get();
        saved.setFirstName(nurse.getFirstName());
        saved.setLastName(nurse.getLastName());
        saved.setLicenseNumber(nurse.getLicenseNumber());
        saved.setDonationBed(nurse.getDonationBed());
        saved.setCertifications(nurse.getCertifications());

        nurseRepository.save(saved);
        return "Nurse updated successfully";
    }

    public String delete(UUID id) {
        Optional<Nurse> existing = nurseRepository.findById(id);

        if (existing.isEmpty()) {
            return "The nurse with that id does not exist";
        }

        nurseRepository.deleteById(id);
        return "Nurse deleted successfully";
    }
}
