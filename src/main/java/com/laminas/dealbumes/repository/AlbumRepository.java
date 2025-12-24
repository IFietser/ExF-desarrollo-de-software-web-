package com.laminas.dealbumes.repository;

import com.laminas.dealbumes.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
