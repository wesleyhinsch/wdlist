package com.hinsch.wdlist.api.converter;


import com.hinsch.wdlist.api.response.UsuarioResponseDTO;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "usuario.id")
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "documento", source = "usuario.documento")
    @Mapping(target = "endereco", source = "enderecoEntity")
    @Mapping(target = "produtos", source = "produtos")
    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario, EnderecoEntity enderecoEntity, List<ProdutoEntity> produtos);


}
