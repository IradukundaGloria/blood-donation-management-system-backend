
package auca.ac.blood_donation_management_system.controller;

import auca.ac.blood_donation_management_system.entity.BloodUnit;
import auca.ac.blood_donation_management_system.service.BloodUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-units")
public class BloodUnitController {

    private final BloodUnitService bloodUnitService;

    public BloodUnitController(BloodUnitService bloodUnitService) {
        this.bloodUnitService = bloodUnitService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestParam UUID donationId,
            @RequestBody BloodUnit bloodUnit) {

        String result = bloodUnitService.save(donationId, bloodUnit);

        if (result.equals("The donation with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Blood units are only for COLLECTED donations")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/donor/{donorNumber}")
    public List<BloodUnit> findByDonorNumber(
            @PathVariable String donorNumber) {
        return bloodUnitService.findByDonorNumber(donorNumber);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String result = bloodUnitService.delete(id);

        if (result.equals("The blood unit with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
