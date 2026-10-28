
package auca.ac.blood_donation_management_system.service;

import auca.ac.blood_donation_management_system.entity.Certification;
import auca.ac.blood_donation_management_system.repository.CertificationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public String save(Certification certification) {
        certificationRepository.save(certification);
        return "Certification saved successfully";
    }

    public List<Certification> findAll() {
        return certificationRepository.findAll();
    }

    public Optional<Certification> findById(UUID id) {
        return certificationRepository.findById(id);
    }

    public String update(UUID id, Certification certification) {
        Optional<Certification> existing = certificationRepository.findById(id);

        if (existing.isEmpty()) {
            return "The certification with that id does not exist";
        }

        Certification saved = existing.get();
        saved.setName(certification.getName());
        certificationRepository.save(saved);

        return "Certification updated successfully";
    }

    public String delete(UUID id) {
        Optional<Certification> existing = certificationRepository.findById(id);

        if (existing.isEmpty()) {
            return "The certification with that id does not exist";
        }

        certificationRepository.deleteById(id);
        return "Certification deleted successfully";
    }
    
public List<Certification> findUnusedCertifications() {
    return certificationRepository.findUnusedCertifications();
}

}
