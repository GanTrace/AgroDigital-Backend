package com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.CreateMedicalRecordCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medical_records")
public class MedicalRecord extends AuditableAbstractAggregateRoot<MedicalRecord> {

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

    @Column(name = "record_type", nullable = false)
    @NotBlank
    private String recordType; // Revisión, Vacunación, Tratamiento, Cirugía, Emergencia

    @Column(name = "diagnosis", nullable = false)
    @NotBlank
    private String diagnosis;

    @Column(name = "treatment", nullable = false)
    @NotBlank
    private String treatment;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "follow_up")
    private LocalDate followUp;

    @ElementCollection
    @CollectionTable(name = "medical_record_attachments", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "attachment_url")
    private List<String> attachments;

    @Column(name = "created_by", nullable = false)
    @NotNull
    private Long createdBy;

    // createdAt is inherited from AuditableAbstractAggregateRoot

    public MedicalRecord() {
    }

    public MedicalRecord(Long patientId, String patientName, String ownerName, LocalDate date,
                        String recordType, String diagnosis, String treatment, String notes,
                        LocalDate followUp, List<String> attachments, Long createdBy) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.ownerName = ownerName;
        this.date = date;
        this.recordType = recordType;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.followUp = followUp;
        this.attachments = attachments;
        this.createdBy = createdBy;
        // createdAt is automatically set by AuditableAbstractAggregateRoot
    }

    public MedicalRecord(CreateMedicalRecordCommand command) {
        this.patientId = command.patientId();
        this.patientName = command.patientName();
        this.ownerName = command.ownerName();
        this.date = command.date();
        this.recordType = command.recordType();
        this.diagnosis = command.diagnosis();
        this.treatment = command.treatment();
        this.notes = command.notes();
        this.followUp = command.followUp();
        this.attachments = command.attachments();
        this.createdBy = command.createdBy();
        // createdAt is automatically set by AuditableAbstractAggregateRoot
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

    public String getRecordType() {
        return recordType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getFollowUp() {
        return followUp;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    // getCreatedAt() is inherited from AuditableAbstractAggregateRoot

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

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setFollowUp(LocalDate followUp) {
        this.followUp = followUp;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    // setCreatedAt is not needed as createdAt is managed by AuditableAbstractAggregateRoot
}