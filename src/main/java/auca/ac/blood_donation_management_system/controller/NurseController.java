
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.Nurse;
import auca.ac.blood_donation_management_system.service.NurseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Nurse nurse) {
        String message = nurseService.save(nurse);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Nurse> findAll() {
        return nurseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Nurse> nurse = nurseService.findById(id);

        if (nurse.isPresent()) {
            return ResponseEntity.ok(nurse.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The nurse with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
                                         @RequestBody Nurse nurse) {
        String message = nurseService.update(id, nurse);

        if (message.equals("The nurse with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = nurseService.delete(id);

        if (message.equals("The nurse with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
    
@GetMapping("/by-certification")
public List<Nurse> findByCertification(@RequestParam String name) {
    return nurseService.findByCertification(name);
}

@PutMapping("/{nurseId}/certifications/{certificationId}")
public ResponseEntity<String> addCertification(
        @PathVariable UUID nurseId,
        @PathVariable UUID certificationId) {

    String result = nurseService.addCertification(nurseId, certificationId);

    if (result.equals("The nurse with that id does not exist") ||
        result.equals("That certification does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("The nurse already has that certification")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.ok(result);
}


}
