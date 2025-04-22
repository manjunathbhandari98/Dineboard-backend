package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.User;

public class HotelDTO {
    private Long id;
    private String name;
    private String logoUrl; // base64 string
    private byte[] logoUrlBytes; // used internally, not sent to frontend
    private String address;
    private String contactEmail;
    private String contactPhone;
    private Long ownerId;
    private String website;
    private String description;

    public HotelDTO() {}

    public HotelDTO(Long id, String name, String logoUrl, byte[] logoUrlBytes, String address, String contactEmail,
                    String contactPhone, Long ownerId, String website, String description) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.logoUrlBytes = logoUrlBytes;
        this.address = address;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.ownerId = ownerId;
        this.website = website;
        this.description = description;
    }

    public Hotel toEntity(User owner) {
        Hotel hotel = new Hotel();
        hotel.setId(this.id);
        hotel.setName(this.name);
        hotel.setLogoUrl(this.logoUrl);
        hotel.setLogoUrlBytes(this.logoUrlBytes);
        hotel.setAddress(this.address);
        hotel.setContactEmail(this.contactEmail);
        hotel.setContactPhone(this.contactPhone);
        hotel.setWebsite(this.website);
        hotel.setDescription(this.description);
        hotel.setOwner(owner);
        return hotel;
    }

    // Getters and Setters (generate manually if preferred)

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

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
