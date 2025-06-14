package com.acme.agrodigitalbackend.events.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.events.domain.model.commands.CreateEventCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event extends AuditableAbstractAggregateRoot<Event> {

    @Column(name = "tipo", nullable = false)
    @NotBlank
    private String tipo;

    @Column(name = "titulo", nullable = false)
    @NotBlank
    private String titulo;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private LocalDate fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "creator_id", nullable = false)
    @NotNull
    private Long creatorId;

    @Column(name = "creator_name", nullable = false)
    @NotBlank
    private String creatorName;

    public Event() {
    }

    public Event(String tipo, String titulo, LocalDate fecha, String descripcion, String imagen, Long creatorId, String creatorName) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
    }

    public Event(CreateEventCommand command) {
        this.tipo = command.tipo();
        this.titulo = command.titulo();
        this.fecha = command.fecha();
        this.descripcion = command.descripcion();
        this.imagen = command.imagen();
        this.creatorId = command.creatorId();
        this.creatorName = command.creatorName();
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    // Update methods
    public void updateTipo(String tipo) {
        this.tipo = tipo;
    }

    public void updateTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void updateFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void updateDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void updateImagen(String imagen) {
        this.imagen = imagen;
    }
}