package com.superdev.helpdesk.services;

import com.superdev.helpdesk.dto.ticket.*;
import com.superdev.helpdesk.enums.Papel;
import com.superdev.helpdesk.enums.StatusTicket;
import com.superdev.helpdesk.exceptions.RegraDeNegocio;
import com.superdev.helpdesk.models.Ticket;
import com.superdev.helpdesk.models.Usuario;
import com.superdev.helpdesk.repositories.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final UsuarioService UsuarioService;

    public TicketService(TicketRepository repository, UsuarioService usuarioService) {this.repository = repository;
        UsuarioService = usuarioService;
    }

    public List<Ticket> listar() {
        return this.repository.findAll();
    }

    private String gerarNumeroProtocolo(Ticket ticket) {
        String dataCriacao = ticket.getDataCriacao().format(DateTimeFormatter.BASIC_ISO_DATE);
        String numero = String.format("%05d", ticket.getId());
        return dataCriacao + "-" + numero;
    }

    public Ticket criar(TicketCriarDto dado) {
        Usuario usuario = UsuarioService.obterPorId(dado.solicitanteId());
        if (usuario.getPapel() != Papel.SOLICITANTE) {
            throw new RegraDeNegocio("Tickets podem ser abertos somente por usários com papel de 'SOLICITANTE");
        }

        String numeroProtocoloFake = UUID.randomUUID().toString().substring(0, 20);

        Ticket ticket = Ticket.builder()
                .titulo(dado.titulo())
                .descricao(dado.descricao())
                .setor(dado.setor())
                .solicitante(usuario)
                .status(StatusTicket.ABERTO)
                .numeroProtocolo("20260925-0001")
                .numeroProtocolo(numeroProtocoloFake)
                .build();

        /*
        * Executa o INSERT agora, ainda dentro da transação (sem commit). ) banco gera o valor da coluna
        * IDENTITY e o Hibernate preenche o ticket .getId()
        * */
        repository.saveAndFlush(ticket);

        // Com id e data é possível gerar o número do protocolo
        String numeroProtocolo = gerarNumeroProtocolo(ticket);

        return ticket;
    }

    public Ticket associar(int id, TicketAssociarDto dado) {
        Ticket ticket = repository.findById(id).orElseThrow();
        Usuario usuario = UsuarioService.obterPorId(dado.usuarioId());

        if(ticket.getStatus() != StatusTicket.ABERTO) {
            throw new RegraDeNegocio("Tickets podem ser associados somente com status em Aberto");
        }

        if(usuario.getPapel() != Papel.ATENDENTE){
            throw new RegraDeNegocio("Tickets podm ser associados somente a usuários que são atendentes");
        }

        ticket.setAtendente(usuario);
        ticket.setStatus(StatusTicket.EM_ANALISE);
        return repository.save(ticket);
    }
    public Ticket cancelar(int id, TicketCancelarDto dado) {
        Ticket ticket = repository.findById(id).orElseThrow();

        if(ticket.getStatus() == StatusTicket.CANCELADO) {
            throw new RegraDeNegocio("Tickets não podem ser cancelados quando já estão cancelados");
        } else if (ticket.getStatus() == StatusTicket.RESOLVIDO) {
            throw new RegraDeNegocio("Tickets não podem ser cancelados quando já estão resolvidos");
        }

        ticket.setStatus(StatusTicket.CANCELADO);
        ticket.setMotivoCancelamento(dado.motivoCancelamento());
        return repository.save(ticket);
    }

    public Ticket resolver(int id, TicketResolverDto dado) {
        Ticket ticket = repository.findById(id).orElseThrow();

        if(ticket.getStatus() != StatusTicket.EM_ANALISE)
            throw new RegraDeNegocio("Ticket não pode quando não estiver em análise");

        ticket.setStatus(StatusTicket.RESOLVIDO);
        ticket.setDescricaoSolucao(dado.descricaoSolucao());
        return repository.save(ticket);
    }

    public Ticket definirPrioridade(int id, TicketDefinirPrioridadeDto dado) {
        Ticket ticket = repository.findById(id).orElseThrow();

        if(ticket.getStatus() != StatusTicket.EM_ANALISE){
            throw new RegraDeNegocio("Não é possível definir prioridade do ticket quando o status não está em análise");
        }

        ticket.setPrioridade(dado.prioridade());
        return repository.save(ticket);
    }
}
