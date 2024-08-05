package com.hinsch.wdlist;


import com.hinsch.wdlist.business.EnderecoService;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvaEndereco() {
        EnderecoEntity endereco = new EnderecoEntity();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        EnderecoEntity result = enderecoService.salvaEndereco(endereco);
        assertNotNull(result);
        assertEquals(endereco, result);
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    void testFindByUsuarioId() {
        String usuarioId = "123";
        EnderecoEntity endereco = new EnderecoEntity();
        when(enderecoRepository.findByUsuarioId(usuarioId)).thenReturn(endereco);

        EnderecoEntity result = enderecoService.findByUsuarioId(usuarioId);
        assertNotNull(result);
        assertEquals(endereco, result);
        verify(enderecoRepository, times(1)).findByUsuarioId(usuarioId);
    }

    @Test
    void testDeleteByUsuarioId() {
        String usuarioId = "123";

        doNothing().when(enderecoRepository).deleteByUsuarioId(usuarioId);
        enderecoService.deleteByUsuarioId(usuarioId);

        verify(enderecoRepository, times(1)).deleteByUsuarioId(usuarioId);
    }
}