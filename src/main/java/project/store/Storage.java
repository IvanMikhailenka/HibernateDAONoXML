package project.store;

import java.util.List;

/**
 * Created by Ivan on 28.01.2017.
 */

public interface Storage<T> {
    List<T> findAll();
    void add(T r);
    void delete(int id);
}
