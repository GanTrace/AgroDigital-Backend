package com.acme.agrodigitalbackend.treatments.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.treatments.domain.model.commands.CreateTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.domain.model.valueobjects.TreatmentCategory;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "treatments")
public class Treatment extends AuditableAbstractAggregateRoot<Treatment> {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @NotNull
    private TreatmentCategory category;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @Column(name = "duration", nullable = false)
    @NotBlank
    private String duration;

    @ElementCollection
    @CollectionTable(name = "treatment_materials", joinColumns = @JoinColumn(name = "treatment_id"))
    @Column(name = "material")
    private List<String> materials;

    @ElementCollection
    @CollectionTable(name = "treatment_steps", joinColumns = @JoinColumn(name = "treatment_id"))
    @Column(name = "step")
    private List<String> steps;

    @ElementCollection
    @CollectionTable(name = "treatment_animal_types", joinColumns = @JoinColumn(name = "treatment_id"))
    @Column(name = "animal_type")
    private List<String> animalTypes;

    @Column(name = "image")
    private String image;

    @Column(name = "created_by", nullable = false)
    @NotNull
    private Long createdBy;

    public Treatment() {
    }

    public Treatment(String name, TreatmentCategory category, String description, BigDecimal price,
                    String duration, List<String> materials, List<String> steps, List<String> animalTypes,
                    String image, Long createdBy) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.materials = materials;
        this.steps = steps;
        this.animalTypes = animalTypes;
        this.image = image;
        this.createdBy = createdBy;
    }

    public Treatment(CreateTreatmentCommand command) {
        this.name = command.name();
        this.category = command.category();
        this.description = command.description();
        this.price = command.price();
        this.duration = command.duration();
        this.materials = command.materials();
        this.steps = command.steps();
        this.animalTypes = command.animalTypes();
        this.image = command.image();
        this.createdBy = command.createdBy();
    }

    // Getters
    public String getName() {
        return name;
    }

    public TreatmentCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<String> getAnimalTypes() {
        return animalTypes;
    }

    public String getImage() {
        return image;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(TreatmentCategory category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public void setAnimalTypes(List<String> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}