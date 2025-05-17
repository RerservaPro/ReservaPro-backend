package br.com.reservapro.application.servico;

import br.com.reservapro.domain.PaginaResponse;
import br.com.reservapro.domain.StatusAtivacao;
import br.com.reservapro.domain.servico.Servico;
import br.com.reservapro.exception.RecursoNaoEncontradoException;
import br.com.reservapro.exception.ViolacaoIntegridadeDadoException;
import br.com.reservapro.exception.enums.RuntimeErroEnum;
import br.com.reservapro.infrastructure.database.entities.servico.ServicoEntity;
import br.com.reservapro.infrastructure.database.mappers.ServicoPersistenceMapper;
import br.com.reservapro.infrastructure.database.repositories.ServicoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepositoryImpl servicoRepository;
    private final ServicoPersistenceMapper mapper;

    public PaginaResponse<Servico> procurar(boolean estaAtivo, int pagina , int tamanhoPagina, String nome) {
        return mapper.mapToPageResponseDomain(
                servicoRepository.findByPagination(estaAtivo, PageRequest.of(pagina, tamanhoPagina), nome == null ? "" : nome)
        );
    }

    public Servico procurarPorId(String id) {
        return mapper.mapToDomain(servicoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));
    }

    public Servico inserir(Servico servico) {
        servico.setId(null);
        StatusAtivacao statusAtivacao = StatusAtivacao.builder()
                .dataCriacao(Instant.now())
                .estaAtivo(true)
                .build();
        servico.setStatusAtivacao(statusAtivacao);

        ServicoEntity salvo;
        try {
            salvo = servicoRepository.save(mapper.mapToEntity(servico));
        } catch (TransactionSystemException exception) {
            throw new ViolacaoIntegridadeDadoException(RuntimeErroEnum.ERR0002);
        }
        return mapper.mapToDomain(salvo);
    }

    public Servico atualizar(String id, Servico servicoNovo) {
        ServicoEntity servicoRecuperado = servicoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        servicoRecuperado.setNome(servicoNovo.getNome() == null ? servicoRecuperado.getNome() : servicoNovo.getNome());
        servicoRecuperado.setPreco(servicoNovo.getPreco() == null ? servicoRecuperado.getPreco() : servicoNovo.getPreco());
        servicoRecuperado.setDescricao(servicoNovo.getDescricao() == null ? servicoRecuperado.getDescricao() : servicoNovo.getDescricao());
        ServicoEntity atualizado;
        try {
            atualizado = servicoRepository.save(servicoRecuperado);
        } catch (TransactionSystemException exception) {
            throw new ViolacaoIntegridadeDadoException(RuntimeErroEnum.ERR0002);
        }
        return mapper.mapToDomain(atualizado);
    }

    public void desativar(String id) {
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        servico.getStatusAtivacao().setDataDesativacao(Instant.now());
        servico.getStatusAtivacao().setEstaAtivo(false);
        servicoRepository.save(servico);
    }

    public void deletar(String id) {
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        servicoRepository.delete(servico);
    }
}
