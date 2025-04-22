package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.PlanDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private boolean allowsWhiteLabeling;

    private boolean highlighted;

    @ElementCollection
    @CollectionTable(name = "plan_features", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "feature")
    private List<String> features;

    public Plan() {}

    public Plan(Long id, String name, String description, double price, boolean allowsWhiteLabeling, boolean highlighted, List<String> features) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allowsWhiteLabeling = allowsWhiteLabeling;
        this.highlighted = highlighted;
        this.features = features;
    }

    public PlanDTO toDTO() {
        return new PlanDTO(
                this.id,
                this.name,
                this.description,
                this.price,
                this.allowsWhiteLabeling,
                this.highlighted,
                this.features
        );
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAllowsWhiteLabeling() {
        return allowsWhiteLabeling;
    }

    public void setAllowsWhiteLabeling(boolean allowsWhiteLabeling) {
        this.allowsWhiteLabeling = allowsWhiteLabeling;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
