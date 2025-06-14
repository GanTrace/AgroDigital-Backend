package com.acme.agrodigitalbackend.appointments.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.appointments.domain.model.commands.CreateAppointmentCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @Column(name = "patient_id", nullable = false)
    @NotNull
    private Long patientId;

    @Column(name = "patient_name", nullable = false)
    @NotBlank
    private String patientName;

    @Column(name = "owner_name", nullable = false)
    @NotBlank
    private String ownerName;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @NotNull
    private LocalTime time;

    @Column(name = "reason", nullable = false)
    @NotBlank
    private String reason;

    @Column(name = "status", nullable = false)
    @NotBlank
    private String status; // scheduled, completed, cancelled

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_by", nullable = false)
    @NotNull
    private Long createdBy;

    public Appointment() {
    }

    public Appointment(Long patientId, String patientName, String ownerName, LocalDate date,
                      LocalTime time, String reason, String status, String notes, Long createdBy) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.ownerName = ownerName;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
        this.createdBy = createdBy;
    }

    public Appointment(CreateAppointmentCommand command) {
        this.patientId = command.patientId();
        this.patientName = command.patientName();
        this.ownerName = command.ownerName();
        this.date = command.date();
        this.time = command.time();
        this.reason = command.reason();
        this.status = command.status();
        this.notes = command.notes();
        this.createdBy = command.createdBy();
    }


    // Getters
    public Long getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    // Setters
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}