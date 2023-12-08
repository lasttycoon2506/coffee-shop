package coffeeshop.Models;

import java.util.List;
import java.util.Optional;


public interface DAO<Type> {
    Optional<Type> get(Integer id);
    List<Type> getAll();
    void save(Type t);
    void update(Type t);
    void delete(Type t);
}
