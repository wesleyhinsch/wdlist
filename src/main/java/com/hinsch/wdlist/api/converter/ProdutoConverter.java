package com.hinsch.wdlist.api.converter;


import com.hinsch.wdlist.api.request.EnderecoRequestDTO;
import com.hinsch.wdlist.api.request.UsuarioRequestDTO;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProdutoConverter {

    public ProdutoEntity paraProdutoEntity(UsuarioRequestDTO usuarioDTO, UsuarioEntity usuarioEntity) {
        return ProdutoEntity.builder()
                                .usuarioId(usuarioEntity.getId())
                                .codigoIdentificacao(usuarioDTO.getProduto().getCodigoIdentificacao())
                                .nome(usuarioDTO.getProduto().getNome())
                                .valor(usuarioDTO.getProduto().getValor())
                                .cor(usuarioDTO.getProduto().getCor())
                                .dataCadastro(LocalDateTime.now())
                                .dataAtualizacao(LocalDateTime.now())
                                .build();
    }


    public EnderecoEntity paraEnderecoEntity(EnderecoRequestDTO enderecoDTO, String usuarioId) {
        return EnderecoEntity.builder()
                .rua(enderecoDTO.getRua())
                .bairro(enderecoDTO.getBairro())
                .usuarioId(usuarioId)
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }

}
