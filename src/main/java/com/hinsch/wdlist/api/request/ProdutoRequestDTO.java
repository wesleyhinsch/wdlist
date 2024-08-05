package com.hinsch.wdlist.api.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProdutoRequestDTO {
    private String nome;
    private String codigoIdentificacao;
    private String valor;
    private String cor;
}
