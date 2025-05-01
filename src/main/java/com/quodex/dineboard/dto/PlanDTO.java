package com.quodex.dineboard.dto;

import java.util.List;

public class PlanDTO {

    private Integer id;
    private String name;
    private String description;
    private double price;
    private boolean allowsWhiteLabeling;
    private boolean highlighted;

    private Integer allowedMenus;
    private List<String> features;

    public PlanDTO(Integer id, String name, String description, double price,
                   boolean allowsWhiteLabeling, boolean highlighted, Integer allowedMenus, List<String> features) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allowsWhiteLabeling = allowsWhiteLabeling;
        this.highlighted = highlighted;
        this.allowedMenus = allowedMenus;
        this.features = features;
    }

    // --- Getters and Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAllowedMenus() {
        return allowedMenus;
    }

    public void setAllowedMenus(Integer allowedMenus) {
        this.allowedMenus = allowedMenus;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
