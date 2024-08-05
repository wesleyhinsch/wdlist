package com.hinsch.wdlist;

import com.hinsch.wdlist.api.controller.WDController;
import com.hinsch.wdlist.business.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WDControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private WDController wdController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemoverProduto() {
        String codigoIdentificacao = "ABC123";

        doNothing().when(clienteService).deletarProdutoWd(codigoIdentificacao);
        ResponseEntity<Void> response = wdController.removerProduto(codigoIdentificacao);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(clienteService, times(1)).deletarProdutoWd(codigoIdentificacao);
    }

    @Test
    void testBuscaTodosProdutosPorCliente_ProdutoExiste() {
        String email = "test@example.com";
        String codigoIdentificacao = "ABC123";
        when(clienteService.buscaProdutoNaWdCliente(email, codigoIdentificacao)).thenReturn(true);

        ResponseEntity<Boolean> response = wdController.buscaTodosProdutosPorCliente(email, codigoIdentificacao);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(clienteService, times(1)).buscaProdutoNaWdCliente(email, codigoIdentificacao);
    }

    @Test
    void testBuscaTodosProdutosPorCliente_ProdutoNaoExiste() {
        String email = "test@example.com";
        String codigoIdentificacao = "ABC123";
        when(clienteService.buscaProdutoNaWdCliente(email, codigoIdentificacao)).thenReturn(false);

        ResponseEntity<Boolean> response = wdController.buscaTodosProdutosPorCliente(email, codigoIdentificacao);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody());
        verify(clienteService, times(1)).buscaProdutoNaWdCliente(email, codigoIdentificacao);
    }
}
