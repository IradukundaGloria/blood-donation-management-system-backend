
package auca.ac.blood_donation_management_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "blood_unit")
public class BloodUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String componentType;
    private String storageNote;
    private int volumeMl;
    private LocalDate collectedDate;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getStorageNote() {
        return storageNote;
    }

    public void setStorageNote(String storageNote) {
        this.storageNote = storageNote;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        this.volumeMl = volumeMl;
    }

    public LocalDate getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(LocalDate collectedDate) {
        this.collectedDate = collectedDate;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }
}
