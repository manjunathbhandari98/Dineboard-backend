package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.HotelDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String logoUrl; // Base64 string (preview)

    @Lob
    private byte[] logoUrlBytes; // Binary stored image

    private String address;
    private String contactEmail;
    private String contactPhone;
    private String website;
    private String description;

    @OneToOne
    private User owner;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = true)
    private Plan plan;

    public Hotel() {}

    public Hotel(Long id, String name, String logoUrl, byte[] logoUrlBytes, String address, String contactEmail,
                 String contactPhone, String website, String description, User owner,
                 LocalDateTime createdAt, LocalDateTime updatedAt, Plan plan) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.logoUrlBytes = logoUrlBytes;
        this.address = address;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.website = website;
        this.description = description;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.plan = plan;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public byte[] getLogoUrlBytes() { return logoUrlBytes; }
    public void setLogoUrlBytes(byte[] logoUrlBytes) { this.logoUrlBytes = logoUrlBytes; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public HotelDTO toDTO() {
        String base64Logo = this.logoUrlBytes != null ? "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(this.logoUrlBytes) : null;
        Integer planId = (plan != null) ? plan.getId() : null;
        return new HotelDTO(
                this.id,
                this.name,
                base64Logo,
                this.logoUrlBytes,
                this.address,
                this.contactEmail,
                this.contactPhone,
                this.owner != null ? this.owner.getId() : null,
                this.website,
                this.description,
                planId
        );
    }
}
