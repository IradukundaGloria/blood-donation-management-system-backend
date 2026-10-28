
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.DonationBed;
import auca.ac.blood_donation_management_system.service.DonationBedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/donation-beds")
public class DonationBedController {

    private final DonationBedService donationBedService;

    public DonationBedController(DonationBedService donationBedService) {
        this.donationBedService = donationBedService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DonationBed donationBed) {
        String message = donationBedService.save(donationBed);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<DonationBed> findAll() {
        return donationBedService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<DonationBed> donationBed = donationBedService.findById(id);

        if (donationBed.isPresent()) {
            return ResponseEntity.ok(donationBed.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The donation bed with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
                                         @RequestBody DonationBed donationBed) {
        String message = donationBedService.update(id, donationBed);

        if (message.equals("The donation bed with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = donationBedService.delete(id);

        if (message.equals("The donation bed with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
    
@GetMapping("/free")
public List<DonationBed> findFreeBeds() {
    return donationBedService.findFreeBeds();
}

}
