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

package main.java.com.UEFS.system.Interfaces;

import java.util.List;
import java.util.UUID;

/**
 * Interface genérica para operações de serviço.
 *
 * @param <T> o tipo de entidade que este serviço gerencia
 */
public interface IService<T> {

    /**
     * Cria uma nova entidade.
     *
     * @param entity a entidade a ser criada
     * @return a entidade criada
     * @throws Exception se ocorrer um erro ao criar a entidade
     */
    T create(T entity) throws Exception;

    /**
     * Retorna todas as entidades.
     *
     * @return uma lista contendo todas as entidades
     */
    List<T> getAll();

    /**
     * Encontra uma entidade pelo seu ID.
     *
     * @param id o UUID da entidade a ser encontrada
     * @return a entidade correspondente ou null se não encontrada
     */
    T getById(UUID id);

    /**
     * Atualiza uma entidade existente.
     *
     * @param entity a entidade a ser atualizada
     * @throws Exception se ocorrer um erro ao atualizar a entidade
     */
    void update(T entity) throws Exception;

    /**
     * Remove uma entidade pelo seu ID.
     *
     * @param id o UUID da entidade a ser removida
     */
    void delete(UUID id);

    /**
     * Remove todas as entidades.
     */
    void deleteAll();
}
