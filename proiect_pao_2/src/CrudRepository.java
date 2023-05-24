import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudRepository<T> {
    private List<T> colectieObiecte;
    private Map<String, T> listaObiecte;

    private static CrudRepository<?> instance;

    private CrudRepository() {
        this.colectieObiecte = new ArrayList<>();
        this.listaObiecte = new HashMap<>();
    }

    public static synchronized <T> CrudRepository<T> getInstance() {
        if (instance == null) {
            instance = new CrudRepository<>();
        }
        return (CrudRepository<T>) instance;
    }

    public List<T> getColectieObiecte() {
        return colectieObiecte;
    }

    public void setColectieObiecte(List<T> colectieObiecte) {
        this.colectieObiecte = colectieObiecte;
    }

    public Map<String, T> getListaObiecte() {
        return listaObiecte;
    }

    public void setListaObiecte(Map<String, T> listaObiecte) {
        this.listaObiecte = listaObiecte;
    }
}
