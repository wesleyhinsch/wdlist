package com.hinsch.wdlist.business;

import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService  {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoEntity salvaProduto(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);
    }

    public List<ProdutoEntity>  findByUsuarioId(String usuarioId) {
        return produtoRepository.findAllByUsuarioId(usuarioId);
    }


    public ProdutoEntity findByCodigoIdentificacao(String codigoIdentificacao) {
        return produtoRepository.findByCodigoIdentificacao(codigoIdentificacao);
    }

    public void  deleteByCodigoIdentificacao(String codigoIdentificacao) {
        ProdutoEntity produtoEntity = produtoRepository.findByCodigoIdentificacao(codigoIdentificacao);
        produtoRepository.delete(produtoEntity);
    }

}
