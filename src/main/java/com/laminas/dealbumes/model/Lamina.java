package com.laminas.dealbumes.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Lamina {

    @NotBlank(message = "codigo no puede estar vacío")
    private String codigo;

    @NotBlank(message = "nombre no puede estar vacío")
    private String nombre;

    private String imagenUrl;

    @Min(value = 0, message = "cantidadRepetidas no puede ser negativa")
    private int cantidadRepetidas;

    private boolean pegada;
}
