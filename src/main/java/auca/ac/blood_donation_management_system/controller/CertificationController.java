
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.Certification;
import auca.ac.blood_donation_management_system.service.CertificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Certification certification) {
        String message = certificationService.save(certification);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Certification> findAll() {
        return certificationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Certification> certification = certificationService.findById(id);

        if (certification.isPresent()) {
            return ResponseEntity.ok(certification.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The certification with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
                                         @RequestBody Certification certification) {
        String message = certificationService.update(id, certification);

        if (message.equals("The certification with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = certificationService.delete(id);

        if (message.equals("The certification with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
}
