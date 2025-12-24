package com.laminas.dealbumes.controller;

import com.laminas.dealbumes.dto.ApiResponse;
import com.laminas.dealbumes.model.Album;
import com.laminas.dealbumes.model.Lamina;
import com.laminas.dealbumes.services.AlbumService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/albumes")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
//ALBUMES
    // Crear álbum
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Album> crear(@Valid @RequestBody Album album) {
        Album creado = albumService.crearAlbum(album);
        return new ApiResponse<>("Álbum creado correctamente", creado);
    }

    // Listar álbumes
    @GetMapping
    public ApiResponse<List<Album>> listar() {
        return new ApiResponse<>("Listado de álbumes", albumService.listarAlbumes());
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ApiResponse<Album> obtener(@PathVariable String id) {
        return new ApiResponse<>("Álbum encontrado", albumService.obtenerAlbum(id));
    }

    // Actualizar álbum
    @PutMapping("/{id}")
    public ApiResponse<Album> actualizar(@PathVariable String id, @Valid @RequestBody Album album) {
        Album actualizado = albumService.actualizarAlbum(id, album);
        return new ApiResponse<>("Álbum actualizado correctamente", actualizado);
    }

    // Eliminar álbum
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
public ApiResponse<Object> eliminar(@PathVariable String id) {
    albumService.eliminarAlbum(id);
    return new ApiResponse<>("Álbum eliminado correctamente", null);
}

//LAMINAS
    // Agregar 1 lámina
    @PostMapping("/{id}/laminas")
    public ApiResponse<Album> agregarLamina(@PathVariable String id, @Valid @RequestBody Lamina lamina) {
        Album actualizado = albumService.agregarLamina(id, lamina);
        return new ApiResponse<>("Lámina agregada correctamente", actualizado);
    }

    // Agregar lote de láminas
    @PostMapping("/{id}/laminas/lote")
    public ApiResponse<Album> agregarListado(@PathVariable String id, @Valid @RequestBody List<Lamina> laminas) {
        Album actualizado = albumService.agregarListadoLaminas(id, laminas);
        return new ApiResponse<>("Listado de láminas agregado correctamente", actualizado);
    }

    // Repetidas
    @GetMapping("/{id}/laminas/repetidas")
    public ApiResponse<List<Map<String, Object>>> repetidas(@PathVariable String id) {
        return new ApiResponse<>("Listado de láminas repetidas", albumService.listarRepetidas(id));
    }

    // Faltantes
    @GetMapping("/{id}/laminas/faltantes")
    public ApiResponse<List<Lamina>> faltantes(@PathVariable String id) {
        return new ApiResponse<>("Listado de láminas faltantes", albumService.listarFaltantes(id));
    }
}
