package com.laminas.dealbumes.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "albumes")
public class Album {

    @Id
    private String id;

    @NotBlank(message = "nombre no puede estar vacío")
    private String nombre;

    private String imagenUrl;

    @NotNull(message = "fechaLanzamiento es obligatoria")
    private LocalDate fechaLanzamiento;

    @NotBlank(message = "tipoLaminas no puede estar vacío")
    private String tipoLaminas;

    @Valid
    private List<Lamina> laminas = new ArrayList<>();

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;
}
