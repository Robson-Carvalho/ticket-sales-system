package main.java.UEFS.system.interfaces;

import java.util.List;
import java.util.UUID;

/**
 * Interface for a generic service that provides basic CRUD operations.
 *
 * @param <T> the type of entity managed by the service
 */
public interface IService<T> {
    /**
     * Creates a new entity in the service.
     *
     * @param entity the entity to create
     * @return the created entity
     * @throws Exception if an error occurs during creation
     */
    T create(T entity) throws Exception;

    /**
     * Retrieves all entities managed by the service.
     *
     * @return a list of all entities
     */
    List<T> getAll();

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id the unique identifier of the entity
     * @return the entity with the specified ID, or null if not found
     */
    T getById(UUID id);

    /**
     * Updates an existing entity in the service.
     *
     * @param entity the entity with updated information
     * @throws Exception if an error occurs during the update
     */
    void update(T entity) throws Exception;

    /**
     * Deletes an entity from the service by its unique identifier.
     *
     * @param id the unique identifier of the entity to delete
     */
    void delete(UUID id);

    /**
     * Deletes all entities from the service.
     */
    void deleteAll();
}
