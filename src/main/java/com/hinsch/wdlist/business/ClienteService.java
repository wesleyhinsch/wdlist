package com.hinsch.wdlist.business;

import com.hinsch.wdlist.api.converter.ProdutoConverter;
import com.hinsch.wdlist.api.converter.UsuarioConverter;
import com.hinsch.wdlist.api.converter.UsuarioMapper;
import com.hinsch.wdlist.api.request.UsuarioRequestDTO;
import com.hinsch.wdlist.api.response.UsuarioResponseDTO;
import com.hinsch.wdlist.infrastructure.entity.EnderecoEntity;
import com.hinsch.wdlist.infrastructure.entity.ProdutoEntity;
import com.hinsch.wdlist.infrastructure.entity.UsuarioEntity;
import com.hinsch.wdlist.infrastructure.exceptions.BusinessException;
import com.hinsch.wdlist.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notNull;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private static Integer MAX_PRODUTOS = 19;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioConverter usuarioConverter;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Autowired
    EnderecoService enderecoService;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    ProdutoConverter produtoConverter;


    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioEntity getUsuario(UsuarioEntity usuarioEntity) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(usuarioEntity.getEmail());
        return usuario;
    }

    public UsuarioResponseDTO adicionarProduto(UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioEntity usuarioEntity;
        EnderecoEntity enderecoEntity;
        List<ProdutoEntity> produtos = null;

        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            enderecoEntity = new EnderecoEntity();
            usuarioEntity = getUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            if (usuarioEntity == null) {
                usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
                ProdutoEntity produtoEntity = produtoConverter.paraProdutoEntity(usuarioRequestDTO, usuarioEntity);
                produtoService.salvaProduto(produtoEntity);
                enderecoEntity =  enderecoService.salvaEndereco(
                        usuarioConverter.paraEnderecoEntity(usuarioRequestDTO.getEndereco(), usuarioEntity));
            } else {
                ProdutoEntity produtoEntity = produtoService.findByCodigoIdentificacao(usuarioRequestDTO.getProduto().getCodigoIdentificacao());

                produtos = wdListValidation(usuarioEntity, produtoEntity);

                produtoEntity = produtoConverter.paraProdutoEntity(usuarioRequestDTO, usuarioEntity);
                produtoService.salvaProduto(produtoEntity);
            }
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados da WD list", e);
        }
        return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity, produtos);
    }

    private List<ProdutoEntity> wdListValidation(UsuarioEntity usuarioEntity, ProdutoEntity produtoEntity) {
        List<ProdutoEntity> produtos;
        if(produtoEntity != null && produtoEntity.getUsuarioId().equals(usuarioEntity.getId()))
            throw new BusinessException("Produto Já esta vinculado a WD do Cliente");

        produtos = produtoService.findByUsuarioId(usuarioEntity.getId());

        if(produtos.size() > MAX_PRODUTOS)
            throw new BusinessException("Numero maximo para WD list, para inserir um novo produto pfv retire algum produto da lista");
        return produtos;
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
        try {
            UsuarioEntity entity = usuarioRepository.findByEmail(email);
            notNull(entity, "Cliente não encontrado");
            EnderecoEntity enderecoEntity = enderecoService.findByUsuarioId(entity.getId());
            List<ProdutoEntity> produtos = produtoService.findByUsuarioId(entity.getId());

            return usuarioMapper.paraUsuarioResponseDTO(entity, enderecoEntity, produtos);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar dados de usuário", e);
        }
    }

    public boolean buscaProdutoNaWdCliente(String email, String codigoIdentificacao) {
        try {
            UsuarioEntity entity = usuarioRepository.findByEmail(email);
            notNull(entity, "Cliente não encontrado");
            List<ProdutoEntity> produtos = produtoService.findByUsuarioId(entity.getId());
            Optional<ProdutoEntity> produtoEntity =  produtos.stream().filter(produto -> produto.getCodigoIdentificacao().equals(codigoIdentificacao)).findFirst();

            return produtoEntity.isPresent();

        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar dados de usuário", e);
        }
    }

    @Transactional
    public void deletarProdutoWd(String codigoIdentificacao) {
        produtoService.deleteByCodigoIdentificacao(codigoIdentificacao);
    }


}
