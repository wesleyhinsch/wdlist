package com.hinsch.wdlist;

import com.hinsch.wdlist.business.ProdutoService;
import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvaProduto() {
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.save(produto)).thenReturn(produto);

        ProdutoEntity result = produtoService.salvaProduto(produto);
        assertNotNull(result);
        assertEquals(produto, result);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testFindByUsuarioId() {
        String usuarioId = "123";
        List<ProdutoEntity> produtos = List.of(new ProdutoEntity(), new ProdutoEntity());
        when(produtoRepository.findAllByUsuarioId(usuarioId)).thenReturn(produtos);

        List<ProdutoEntity> result = produtoService.findByUsuarioId(usuarioId);
        assertNotNull(result);
        assertEquals(produtos.size(), result.size());
        verify(produtoRepository, times(1)).findAllByUsuarioId(usuarioId);
    }

    @Test
    void testFindByCodigoIdentificacao() {
        String codigoIdentificacao = "ABC123";
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findByCodigoIdentificacao(codigoIdentificacao)).thenReturn(produto);

        ProdutoEntity result = produtoService.findByCodigoIdentificacao(codigoIdentificacao);
        assertNotNull(result);
        assertEquals(produto, result);
        verify(produtoRepository, times(1)).findByCodigoIdentificacao(codigoIdentificacao);
    }

    @Test
    void testDeleteByCodigoIdentificacao() {
        String codigoIdentificacao = "ABC123";
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findByCodigoIdentificacao(codigoIdentificacao)).thenReturn(produto);
        doNothing().when(produtoRepository).delete(produto);

        produtoService.deleteByCodigoIdentificacao(codigoIdentificacao);

        verify(produtoRepository, times(1)).findByCodigoIdentificacao(codigoIdentificacao);
        verify(produtoRepository, times(1)).delete(produto);
    }
}
