package coffeeshop.Models;

import java.util.List;
import java.util.Optional;

public interface DAO<Type> {
    Optional<Type> get(long id);
    List<Type> getAll();
    public void save(Type t);
    void update(Type t, String[] params);
    void delete(Type t);
}
