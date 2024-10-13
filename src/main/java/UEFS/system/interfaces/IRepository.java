package main.java.UEFS.system.interfaces;

import java.util.List;
import java.util.UUID;

/**
 * Interface for a generic repository that provides basic CRUD operations.
 *
 * @param <T> the type of entity managed by the repository
 */
public interface IRepository<T> {
    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the unique identifier of the entity
     * @return the entity with the specified ID, or null if not found
     */
    T findById(UUID id);

    /**
     * Retrieves all entities managed by the repository.
     *
     * @return a list of all entities
     */
    List<T> findAll();

    /**
     * Saves a new entity to the repository.
     *
     * @param entity the entity to save
     */
    void save(T entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity the entity with updated information
     */
    void update(T entity);

    /**
     * Deletes an entity from the repository by its unique identifier.
     *
     * @param id the unique identifier of the entity to delete
     */
    void delete(UUID id);

    /**
     * Deletes all entities from the repository.
     */
    void deleteAll();
}
