package com.hinsch.wdlist.api.response;

import java.util.List;

public record UsuarioResponseDTO(String id,

                                 String nome,

                                 String email,

                                 String documento,

                                 EnderecoResponseDTO endereco,

                                 List<ProdutosResponseDTO> produtos

) {


}
