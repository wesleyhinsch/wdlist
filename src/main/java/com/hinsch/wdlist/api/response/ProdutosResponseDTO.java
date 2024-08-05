package com.hinsch.wdlist.api.response;

public record ProdutosResponseDTO(String rua,

                                  Long numero,

                                  String bairro,

                                  String complemento,

                                  String cidade,

                                  String cep) {

}
