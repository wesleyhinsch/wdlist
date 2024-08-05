package com.hinsch.wdlist.api.converter;


import com.hinsch.wdlist.api.request.EnderecoRequestDTO;
import com.hinsch.wdlist.api.request.UsuarioRequestDTO;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {



    public UsuarioEntity paraUsuarioEntity(UsuarioRequestDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .id(UUID.randomUUID().toString())
                .nome(usuarioDTO.getNome())
                .documento(usuarioDTO.getDocumento())
                .email(usuarioDTO.getEmail())
                .dataCadastro(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

    }


    public EnderecoEntity paraEnderecoEntity(EnderecoRequestDTO enderecoDTO, UsuarioEntity usuarioEntity) {
        return EnderecoEntity.builder()
                .usuarioId(usuarioEntity.getId())
                .rua(enderecoDTO.getRua())
                .bairro(enderecoDTO.getBairro())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }

}
