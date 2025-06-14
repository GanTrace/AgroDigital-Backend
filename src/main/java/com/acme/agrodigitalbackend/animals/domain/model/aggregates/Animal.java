package com.acme.agrodigitalbackend.animals.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.animals.domain.model.commands.CreateAnimalCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "animals")
public class Animal extends AuditableAbstractAggregateRoot<Animal> {

    @Column(name = "nombre", nullable = false)
    @NotBlank
    private String nombre;

    @Column(name = "especie", nullable = false)
    @NotBlank
    private String especie;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull
    private LocalDate fechaNacimiento;

    @Column(name = "sexo", nullable = false)
    @NotBlank
    private String sexo; // "Macho" o "Hembra"

    @Column(name = "enfermedad", nullable = false)
    @NotBlank
    private String enfermedad; // "Si" o "No"

    @Column(name = "estado_reproductivo")
    private String estadoReproductivo;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "vacunas_aplicadas")
    private Integer vacunasAplicadas;

    @Column(name = "numero_partos")
    private Integer numeroPartos;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "created_by", nullable = false)
    @NotNull
    private Long createdBy;

    public Animal() {
    }

    public Animal(String nombre, String especie, LocalDate fechaNacimiento, String sexo, String enfermedad,
                  String estadoReproductivo, String imageURL, Integer vacunasAplicadas, Integer numeroPartos,
                  String ubicacion, String observaciones, String imagen, Long createdBy) {
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.enfermedad = enfermedad;
        this.estadoReproductivo = estadoReproductivo;
        this.imageURL = imageURL;
        this.vacunasAplicadas = vacunasAplicadas;
        this.numeroPartos = numeroPartos;
        this.ubicacion = ubicacion;
        this.observaciones = observaciones;
        this.imagen = imagen;
        this.createdBy = createdBy;
    }

    public Animal(CreateAnimalCommand command) {
        this.nombre = command.nombre();
        this.especie = command.especie();
        this.fechaNacimiento = command.fechaNacimiento();
        this.sexo = command.sexo();
        this.enfermedad = command.enfermedad();
        this.estadoReproductivo = command.estadoReproductivo();
        this.imageURL = command.imageURL();
        this.vacunasAplicadas = command.vacunasAplicadas();
        this.numeroPartos = command.numeroPartos();
        this.ubicacion = command.ubicacion();
        this.observaciones = command.observaciones();
        this.imagen = command.imagen();
        this.createdBy = command.createdBy();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public String getEstadoReproductivo() {
        return estadoReproductivo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getVacunasAplicadas() {
        return vacunasAplicadas;
    }

    public Integer getNumeroPartos() {
        return numeroPartos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getImagen() {
        return imagen;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    // Update methods
    public void updateNombre(String nombre) {
        this.nombre = nombre;
    }

    public void updateEspecie(String especie) {
        this.especie = especie;
    }

    public void updateFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void updateSexo(String sexo) {
        this.sexo = sexo;
    }

    public void updateEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void updateEstadoReproductivo(String estadoReproductivo) {
        this.estadoReproductivo = estadoReproductivo;
    }

    public void updateImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void updateVacunasAplicadas(Integer vacunasAplicadas) {
        this.vacunasAplicadas = vacunasAplicadas;
    }

    public void updateNumeroPartos(Integer numeroPartos) {
        this.numeroPartos = numeroPartos;
    }

    public void updateUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void updateObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void updateImagen(String imagen) {
        this.imagen = imagen;
    }
}