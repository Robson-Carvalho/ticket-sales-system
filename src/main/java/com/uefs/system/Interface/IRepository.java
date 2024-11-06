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

package com.uefs.system.Interface;

import java.util.List;
import java.util.UUID;

/**
 * Interface genérica para operações de repositório.
 *
 * @param <T> o tipo de entidade que este repositório gerencia
 */
public interface IRepository<T> {

    /**
     * Encontra uma entidade pelo seu ID.
     *
     * @param id o UUID da entidade a ser encontrada
     * @return a entidade correspondente ou null se não encontrada
     */
    T findById(UUID id);

    /**
     * Retorna todas as entidades.
     *
     * @return uma lista contendo todas as entidades
     */
    List<T> findAll();

    /**
     * Salva uma nova entidade no repositório.
     *
     * @param entity a entidade a ser salva
     */
    void save(T entity);

    /**
     * Atualiza uma entidade existente no repositório.
     *
     * @param entity a entidade a ser atualizada
     */
    void update(T entity);

    /**
     * Remove uma entidade pelo seu ID.
     *
     * @param id o UUID da entidade a ser removida
     */
    void delete(UUID id);

    /**
     * Remove todas as entidades do repositório.
     */
    void deleteAll();
}
