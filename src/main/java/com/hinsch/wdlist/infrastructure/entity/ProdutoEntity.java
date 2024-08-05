package com.hinsch.wdlist.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "produto_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    private String id;
    private String usuarioId;
    private String codigoIdentificacao;
    private String nome;
    private String valor;
    private String cor;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

}
