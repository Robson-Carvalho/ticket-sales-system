/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 *
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package com.uefs.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uefs.system.Interface.IRepository;
import com.uefs.system.model.Event;
import com.uefs.system.utils.PathsFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe repositório para a entidade Event, responsável por gerenciar as operações de CRUD
 * utilizando um arquivo JSON como armazenamento.
 */
public class EventRepository implements IRepository<Event> {
    private static final String FILE_PATH = PathsFile.getEventsJSON();
    private final List<Event> events;

    /**
     * Construtor da classe que inicializa a lista de eventos carregando os dados do arquivo JSON.
     */
    public EventRepository() {
        events = loadEvents();
    }

    /**
     * Carrega a lista de eventos do arquivo JSON.
     *
     * @return Lista de eventos carregada do arquivo, ou uma nova lista se ocorrer algum erro.
     */
    private List<Event> loadEvents() {
        List<Event> eventList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Event>>() {}.getType();
            eventList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventList != null ? eventList : new ArrayList<>();
    }

    /**
     * Salva a lista de eventos no arquivo JSON.
     */
    private void saveEvents() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(events, writer);
        } catch (Exception e) {
            System.out.println("Error while saving events");
        }
    }

    /**
     * Busca um evento pelo seu ID.
     *
     * @param id ID do evento a ser buscado.
     * @return Evento encontrado ou null se não existir.
     */
    @Override
    public Event findById(UUID id) {
        return loadEvents().stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna todos os eventos salvos.
     *
     * @return Lista de todos os eventos.
     */
    @Override
    public List<Event> findAll() {
        return loadEvents();
    }

    /**
     * Salva um novo evento no repositório e persiste no arquivo JSON.
     *
     * @param event Evento a ser salvo.
     */
    @Override
    public void save(Event event) {
        events.add(event);
        saveEvents();
    }

    /**
     * Atualiza um evento existente, removendo o antigo e salvando a nova versão.
     *
     * @param event Evento a ser atualizado.
     */
    @Override
    public void update(Event event) {
        delete(event.getId());
        events.add(event);
        saveEvents();
    }

    /**
     * Deleta um evento pelo seu ID.
     *
     * @param id ID do evento a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        events.removeIf(event -> event.getId().equals(id));
        saveEvents();
    }

    /**
     * Deleta todos os eventos do repositório.
     */
    @Override
    public void deleteAll() {
        events.clear();
        saveEvents();
    }
}
