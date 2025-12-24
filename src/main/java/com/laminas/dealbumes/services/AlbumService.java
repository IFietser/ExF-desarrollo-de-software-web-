package com.laminas.dealbumes.services;

import com.laminas.dealbumes.model.Album;
import com.laminas.dealbumes.model.Lamina;
import com.laminas.dealbumes.repository.AlbumRepository;
import com.laminas.dealbumes.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;


    public Album crearAlbum(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> listarAlbumes() {
        return albumRepository.findAll();
    }

    public Album obtenerAlbum(String id) {
        return albumRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Álbum no encontrado: " + id));
}

    public Album actualizarAlbum(String id, Album album) {
        Album actual = obtenerAlbum(id);
        actual.setNombre(album.getNombre());
        actual.setImagenUrl(album.getImagenUrl());
        actual.setFechaLanzamiento(album.getFechaLanzamiento());
        actual.setTipoLaminas(album.getTipoLaminas());
        return albumRepository.save(actual);
    }

    public void eliminarAlbum(String id) {
        albumRepository.deleteById(id);
    }

    // Láminas
    public Album agregarLamina(String albumId, Lamina lamina) {
        Album album = obtenerAlbum(albumId);
        album.getLaminas().add(lamina);
        return albumRepository.save(album);
    }

    public Album agregarListadoLaminas(String albumId, List<Lamina> laminas) {
        Album album = obtenerAlbum(albumId);
        album.getLaminas().addAll(laminas);
        return albumRepository.save(album);
    }

    public List<Map<String, Object>> listarRepetidas(String albumId) {
        Album album = obtenerAlbum(albumId);

        return album.getLaminas().stream()
                .filter(l -> l.getCantidadRepetidas() > 0)
                .map(l -> {
                    Map<String, Object> r = new HashMap<>();
                    r.put("codigo", l.getCodigo());
                    r.put("nombre", l.getNombre());
                    r.put("cantidadRepetidas", l.getCantidadRepetidas());
                    return r;
                })
                .collect(Collectors.toList());
    }

    public List<Lamina> listarFaltantes(String albumId) {
        Album album = obtenerAlbum(albumId);

        return album.getLaminas().stream()
                .filter(l -> !l.isPegada())
                .collect(Collectors.toList());
    }
}
