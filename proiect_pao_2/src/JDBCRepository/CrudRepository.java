package JDBCRepository;

public interface CrudRepository<T> {
    boolean add(T entity);
    int get(String a);
    void getAll();
    boolean update(T entity);
    boolean delete(String a);
}
