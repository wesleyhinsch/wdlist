package com.hinsch.wdlist.api.controller;


import com.hinsch.wdlist.api.request.UsuarioRequestDTO;
import com.hinsch.wdlist.api.response.UsuarioResponseDTO;
import com.hinsch.wdlist.business.ClienteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wd")
@RequiredArgsConstructor
public class WDController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/adicionarProduto")
    @ApiResponses(value={
            @ApiResponse(responseCode="201", description="Produto adicionado com sucesso", content=@Content(schema=@Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode="400", description="Error ao adicionar produto")
    })
    public ResponseEntity<UsuarioResponseDTO> adicionarProduto(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(clienteService.adicionarProduto(usuarioRequestDTO));
    }


    @GetMapping("/buscaTodosProdutosPorCliente")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Produtos listado com sucesso", content=@Content(schema=@Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode="400", description="Error ao listar produtos")
    })
    public ResponseEntity<UsuarioResponseDTO> buscaTodosProdutosPorCliente(@RequestParam ("email") String email) {
        return ResponseEntity.ok(clienteService.buscaDadosUsuario(email));
    }

    @DeleteMapping("/removerProduto")
    @ApiResponses(value={
            @ApiResponse(responseCode="202", description="Produto deletado com sucesso"),
            @ApiResponse(responseCode="400", description="Error ao deletar produto")
    })
    public ResponseEntity<Void> removerProduto(@RequestParam ("codigoIdentificacao") String codigoIdentificacao) {
        clienteService.deletarProdutoWd(codigoIdentificacao);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/existeProdutoWDCliente")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Produto pesquisado com sucesso", content=@Content(schema=@Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode="400", description="Error ao listar produtos")
    })
    public ResponseEntity<Boolean> buscaTodosProdutosPorCliente(@RequestParam ("email") String email, @RequestParam ("codigoIdentificacao") String codigoIdentificacao) {
        return ResponseEntity.ok(clienteService.buscaProdutoNaWdCliente(email, codigoIdentificacao));
    }


}
