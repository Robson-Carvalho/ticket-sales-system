package main.java.UEFS.system.interfaces;

import java.util.List;
import java.util.UUID;

public interface IService<T> {
    T create(T entity);
    List<T> getAll();
    T getById(UUID id);
    void update(T entity);
    void delete(UUID id);
    void deleteAll();
}
