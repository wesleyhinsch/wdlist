package com.hinsch.wdlist.infrastructure.repository;

import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<ProdutoEntity, String> {

    List<ProdutoEntity> findAllByUsuarioId(String usuarioId);

    ProdutoEntity findByCodigoIdentificacao(String codigoIdentificacao);

}
