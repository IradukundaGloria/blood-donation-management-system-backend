
package auca.ac.blood_donation_management_system.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "nurse")
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "donation_bed_id")
    private DonationBed donationBed;

    @ManyToMany
    @JoinTable(
        name = "nurse_certification",
        joinColumns = @JoinColumn(name = "nurse_id"),
        inverseJoinColumns = @JoinColumn(name = "certification_id")
    )
    private List<Certification> certifications;

    public Nurse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public DonationBed getDonationBed() {
        return donationBed;
    }

    public void setDonationBed(DonationBed donationBed) {
        this.donationBed = donationBed;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }
}
