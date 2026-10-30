
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.Donation;
import auca.ac.blood_donation_management_system.entity.DonationStatus;
import auca.ac.blood_donation_management_system.service.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

  
@PostMapping("/save")
public ResponseEntity<String> save(@RequestBody Donation donation) {

    String result = donationService.save(donation);

    if (result.equals("Donation date cannot be in the past")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    if (result.equals("Nurse already collects a donation on that date")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
}


    @GetMapping("/all")
    public List<Donation> findAll() {
        return donationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Donation> donation = donationService.findById(id);

        if (donation.isPresent()) {
            return ResponseEntity.ok(donation.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The donation with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
                                         @RequestBody Donation donation) {
        String message = donationService.update(id, donation);

        if (message.equals("The donation with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

   
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(@PathVariable UUID id) {

    String result = donationService.delete(id);

    if (result.equals("The donation with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("That donation has blood units, delete them first")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.ok(result);
}

    
@GetMapping("/by-status")
public List<Donation> findByStatus(@RequestParam DonationStatus status) {
    return donationService.findByStatus(status);
}

@GetMapping("/between")
public List<Donation> findBetween(@RequestParam String start,
                                  @RequestParam String end) {
    return donationService.findBetween(
            LocalDate.parse(start), LocalDate.parse(end));
}

@PatchMapping("/{id}/status")
public ResponseEntity<String> updateStatus(
        @PathVariable UUID id,
        @RequestParam DonationStatus status) {

    String result = donationService.updateStatus(id, status);

    if (result.equals("The donation with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.startsWith("Cannot change status from")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    return ResponseEntity.ok(result);
}

@GetMapping("/stats/by-status")
public List<Object[]> countDonationsByStatus() {
    return donationService.countDonationsByStatus();
}

@PatchMapping("/cancel-day")
public ResponseEntity<String> cancelDay(
        @RequestParam UUID nurseId,
        @RequestParam String date) {

    String result = donationService.cancelDay(
            nurseId,
            LocalDate.parse(date)
    );

    return ResponseEntity.ok(result);
}



}
