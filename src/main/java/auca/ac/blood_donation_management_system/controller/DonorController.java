
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.Donor;
import auca.ac.blood_donation_management_system.service.DonorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Donor donor) {
        String message = donorService.save(donor);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Donor> findAll() {
        return donorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Donor> donor = donorService.findById(id);

        if (donor.isPresent()) {
            return ResponseEntity.ok(donor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The donor with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
                                         @RequestBody Donor donor) {
        String message = donorService.update(id, donor);

        if (message.equals("The donor with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = donorService.delete(id);

        if (message.equals("The donor with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
    
@GetMapping("/by-last-name")
public List<Donor> findByLastName(@RequestParam String lastName) {
    return donorService.findByLastName(lastName);
}

@GetMapping("/of-nurse/{nurseId}")
public ResponseEntity<?> findDonorsOfNurse(@PathVariable UUID nurseId) {
    if (!donorService.nurseExists(nurseId)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The nurse with that id does not exist");
    } else {
        return ResponseEntity.ok(donorService.findDonorsOfNurse(nurseId));
    }
}


}
