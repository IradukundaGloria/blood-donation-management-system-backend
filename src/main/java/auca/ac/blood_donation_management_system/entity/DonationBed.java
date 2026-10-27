
package auca.ac.blood_donation_management_system.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "donation_bed")
public class DonationBed {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int bedNumber;

    private String name;

    public DonationBed() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
