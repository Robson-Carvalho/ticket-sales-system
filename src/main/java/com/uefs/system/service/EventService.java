/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package com.uefs.system.service;

import com.uefs.system.repository.EventRepository;
import com.uefs.system.model.Event;
import com.uefs.system.Interface.IService;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas a eventos.
 * Implementa a interface IService para a entidade Event.
 */
public class EventService implements IService<Event> {
    private final EventRepository eventRepository;

    /**
     * Construtor da classe que inicializa o repositório de eventos.
     */
    public EventService() {
        this.eventRepository = new EventRepository();
    }

    /**
     * Cria um novo evento e o salva no repositório.
     *
     * @param event Evento a ser criado.
     * @return O evento criado.
     */
    @Override
    public Event create(Event event) {
        eventRepository.save(event);
        return event;
    }

    /**
     * Retorna todos os eventos salvos no repositório.
     *
     * @return Lista de todos os eventos.
     */
    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    /**
     * Busca um evento pelo seu ID.
     *
     * @param id ID do evento a ser buscado.
     * @return Evento encontrado ou null se não existir.
     */
    @Override
    public Event getById(UUID id) {
        return eventRepository.findById(id);
    }

    /**
     * Atualiza um evento existente.
     *
     * @param event Evento a ser atualizado.
     */
    @Override
    public void update(Event event) {
        eventRepository.update(event);
    }

    /**
     * Deleta um evento pelo seu ID.
     *
     * @param id ID do evento a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        eventRepository.delete(id);
    }

    /**
     * Deleta todos os eventos do repositório.
     */
    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
