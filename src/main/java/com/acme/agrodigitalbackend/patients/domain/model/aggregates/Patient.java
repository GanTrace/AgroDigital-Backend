package com.acme.agrodigitalbackend.patients.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.patients.domain.model.commands.CreatePatientCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "type", nullable = false)
    @NotBlank
    private String type; // Vaca Holstein, Toro Angus, Cabra Alpina, Oveja Merino, Caballo Andaluz

    @Column(name = "age", nullable = false)
    @NotBlank
    private String age;

    @Column(name = "gender", nullable = false)
    @NotBlank
    private String gender; // Macho, Hembra

    @Column(name = "health_issues", nullable = false)
    @NotBlank
    private String healthIssues; // problemas respiratorios, cojera leve, infección, otro

    @Column(name = "owner", nullable = false)
    @NotBlank
    private String owner;

    @Column(name = "last_visit")
    private LocalDate lastVisit;

    @Column(name = "next_visit")
    private LocalDate nextVisit;

    @Column(name = "image")
    private String image; // URL de imagen

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "created_by", nullable = false)
    @NotNull
    private Long createdBy;

    public Patient() {
    }

    public Patient(String name, String type, String age, String gender, String healthIssues,
                   String owner, LocalDate lastVisit, LocalDate nextVisit, String image,
                   String observations, Long createdBy) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.healthIssues = healthIssues;
        this.owner = owner;
        this.lastVisit = lastVisit;
        this.nextVisit = nextVisit;
        this.image = image;
        this.observations = observations;
        this.createdBy = createdBy;
    }

    public Patient(CreatePatientCommand command) {
        this.name = command.name();
        this.type = command.type();
        this.age = command.age();
        this.gender = command.gender();
        this.healthIssues = command.healthIssues();
        this.owner = command.owner();
        this.lastVisit = command.lastVisit();
        this.nextVisit = command.nextVisit();
        this.image = command.image();
        this.observations = command.observations();
        this.createdBy = command.createdBy();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getHealthIssues() {
        return healthIssues;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public LocalDate getNextVisit() {
        return nextVisit;
    }

    public String getImage() {
        return image;
    }

    public String getObservations() {
        return observations;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    // Update methods
    public void updateName(String name) {
        this.name = name;
    }

    public void updateType(String type) {
        this.type = type;
    }

    public void updateAge(String age) {
        this.age = age;
    }

    public void updateGender(String gender) {
        this.gender = gender;
    }

    public void updateHealthIssues(String healthIssues) {
        this.healthIssues = healthIssues;
    }

    public void updateOwner(String owner) {
        this.owner = owner;
    }

    public void updateLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    public void updateNextVisit(LocalDate nextVisit) {
        this.nextVisit = nextVisit;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public void updateObservations(String observations) {
        this.observations = observations;
    }
}