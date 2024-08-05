package com.hinsch.wdlist.infrastructure.repository;

import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

    UsuarioEntity findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
}
