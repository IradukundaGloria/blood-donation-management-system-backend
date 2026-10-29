
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.Certification;
import auca.ac.blood_donation_management_system.entity.Nurse;
import auca.ac.blood_donation_management_system.repository.CertificationRepository;
import auca.ac.blood_donation_management_system.repository.NurseRepository;
import org.springframework.stereotype.Service;
import auca.ac.blood_donation_management_system.entity.DonationBed;
import auca.ac.blood_donation_management_system.repository.DonationBedRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;
    private final CertificationRepository certificationRepository;
    private final DonationBedRepository donationBedRepository;

    
public NurseService(NurseRepository nurseRepository,
                    CertificationRepository certificationRepository,
                    DonationBedRepository donationBedRepository) {
    this.nurseRepository = nurseRepository;
    this.certificationRepository = certificationRepository;
    this.donationBedRepository = donationBedRepository;
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

    public List<Nurse> findByCertification(String name) {
        return nurseRepository.findByCertification(name);
    }

    public String addCertification(UUID nurseId, UUID certificationId) {

        Optional<Nurse> existingNurse = nurseRepository.findById(nurseId);

        if (existingNurse.isEmpty()) {
            return "The nurse with that id does not exist";
        }

        Optional<Certification> existingCertification =
                certificationRepository.findById(certificationId);

        if (existingCertification.isEmpty()) {
            return "That certification does not exist";
        }

        Nurse nurse = existingNurse.get();
        Certification certification = existingCertification.get();

        for (Certification c : nurse.getCertifications()) {
            if (c.getId().equals(certificationId)) {
                return "The nurse already has that certification";
            }
        }

        nurse.getCertifications().add(certification);
        nurseRepository.save(nurse);

        return "Certification added to the nurse";
    }
    
public String changeDonationBed(UUID nurseId, int bedNumber) {

    Optional<Nurse> existingNurse = nurseRepository.findById(nurseId);

    if (existingNurse.isEmpty()) {
        return "The nurse with that id does not exist";
    }

    Optional<DonationBed> existingBed =
            donationBedRepository.findByBedNumber(bedNumber);

    if (existingBed.isEmpty()) {
        return "The donation bed with that bedNumber does not exist";
    }

    Nurse nurse = existingNurse.get();
    DonationBed bed = existingBed.get();

    if (nurse.getDonationBed() != null &&
        nurse.getDonationBed().getId().equals(bed.getId())) {
        return "Donation bed changed successfully";
    }

    if (nurseRepository.existsByDonationBed(bed)) {
        return "That donation bed is taken by another nurse";
    }

    nurse.setDonationBed(bed);
    nurseRepository.save(nurse);

    return "Donation bed changed successfully";
}

}
