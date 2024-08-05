package com.hinsch.wdlist;

import com.hinsch.wdlist.api.converter.ProdutoConverter;
import com.hinsch.wdlist.api.converter.UsuarioConverter;
import com.hinsch.wdlist.api.converter.UsuarioMapper;
import com.hinsch.wdlist.api.request.UsuarioRequestDTO;
import com.hinsch.wdlist.api.response.UsuarioResponseDTO;
import com.hinsch.wdlist.business.ClienteService;
import com.hinsch.wdlist.business.EnderecoService;
import com.hinsch.wdlist.business.ProdutoService;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import com.hinsch.wdlist.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ProdutoService produtoService;
    @Mock
    private ProdutoConverter produtoConverter;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvaUsuario() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        UsuarioEntity result = clienteService.salvaUsuario(usuarioEntity);

        assertEquals(usuarioEntity, result);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
    }

    @Test
    void testGetUsuario() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail("test@example.com");
        when(usuarioRepository.findByEmail(anyString())).thenReturn(usuarioEntity);

        UsuarioEntity result = clienteService.getUsuario(usuarioEntity);

        assertEquals(usuarioEntity, result);
        verify(usuarioRepository, times(1)).findByEmail(usuarioEntity.getEmail());
    }


    @Test
    void testDeletarProdutoWd() {
        doNothing().when(produtoService).deleteByCodigoIdentificacao(anyString());

        clienteService.deletarProdutoWd("codigo123");

        verify(produtoService, times(1)).deleteByCodigoIdentificacao("codigo123");
    }
}
