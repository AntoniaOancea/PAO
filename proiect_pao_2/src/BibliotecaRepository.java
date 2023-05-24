import JDBCRepository.JDBCRepositoryAutor;
import JDBCRepository.JDBCRepositoryMembru;
import model.*;

import java.time.LocalDate;
import java.util.*;

public class BibliotecaRepository{
    private List<Carte> colectieCarti;

    private TreeSet<Autor> listaAutori;
    private List<CopieCarte> copieCarti;
    private Map<String, Membru> listaMembri;
    private List<Imprumut> imprumuturi;

    private List<ImprumutOnline> imprumutOnline;

    private List<Imprumut> copieImprumuturi;

    private static BibliotecaRepository instance;

    private BibliotecaRepository() {
        this.colectieCarti = new ArrayList<>();
        this.listaMembri = new HashMap<>();
        this.imprumuturi = new ArrayList<>();
        this.copieImprumuturi = new ArrayList<>();
        this.listaAutori = new TreeSet<>();
        this.copieCarti = new ArrayList<>();
        this.imprumutOnline = new ArrayList<>();
    }

    public static synchronized BibliotecaRepository getInstance() {
        if (instance == null) {
            instance = new BibliotecaRepository();
        }
        return instance;
    }

    public List<ImprumutOnline> getImprumutOnline() {
        return imprumutOnline;
    }

    public void setImprumutOnline(List<ImprumutOnline> imprumutOnline) {
        this.imprumutOnline = imprumutOnline;
    }

    public TreeSet<Autor> getListaAutori() {
        return listaAutori;
    }

    public void setListaAutori(TreeSet<Autor> listaAutori) {
        this.listaAutori = listaAutori;
    }

    public void setImprumuturi(List<Imprumut> imprumuturi) {
        this.imprumuturi = imprumuturi;
    }


    public List<Carte> getColectieCarti() {
        return colectieCarti;
    }

    public void setColectieCarti(List<Carte> colectieCarti) {
        this.colectieCarti = colectieCarti;
    }

    public List<CopieCarte> getCopieCarti() {
        return copieCarti;
    }

    public void setCopieCarti(List<CopieCarte> copieCarti) {
        this.copieCarti = copieCarti;
    }

    public Map<String, Membru> getListaMembri() {
        return listaMembri;
    }

    public void setListaMembri(Map<String, Membru> listaMembri) {
        this.listaMembri = listaMembri;
    }

    public List<Imprumut> getimprumuturi() {
        return imprumuturi;
    }

    public void setimprumuturi(List<Imprumut> imprumuturi) {
        this.imprumuturi = imprumuturi;
    }

    public List<Imprumut> getImprumuturi() {
        return imprumuturi;
    }

    public List<Imprumut> getCopieImprumuturi() {
        return copieImprumuturi;
    }

    public void setCopieImprumuturi(List<Imprumut> copieImprumuturi) {
        this.copieImprumuturi = copieImprumuturi;
    }

    public void sortareDupaTitlu() {
        colectieCarti.sort((c1, c2) -> c1.getTitlu().compareTo(c2.getTitlu()));
    }


    // metode pentru gestionarea listei de membri
    public void adaugaMembru(Membru membru) {
        listaMembri.put(membru.getNumarTelefon(), membru);
    }


    //metode pentru gestionarea împrumuturilor

    public int numarImprumuturiLunaCurenta(Membru membru) {
        int numarImprumuturi = 0;
        LocalDate dataCurenta = LocalDate.now();
        for (Imprumut imprumut : imprumuturi) {
            if (membru.equals(imprumut.getMembru()) &&
                    imprumut.getDataImprumut().getMonth() == dataCurenta.getMonth() &&
                    imprumut.getDataImprumut().getYear() == dataCurenta.getYear()) {
                numarImprumuturi++;
            }
        }
        return numarImprumuturi;
    }


    public void imprumutaCarte(CopieCarte carte, Membru membru) {
        if (carte.getDisponibila()==true && numarImprumuturiLunaCurenta(membru)<=5) {
            carte.setDisponibila(false);
            imprumuturi.add(new Imprumut(carte, membru));
            copieImprumuturi.add(new Imprumut(carte, membru));
        }
    }

    public void imprumutOnline(Carte carte, MembruPremium membru) {

        imprumutOnline.add(new ImprumutOnline(carte, membru));
    }

    public void returneazaCarte(CopieCarte carte, Membru membru) {
        carte.setDisponibila(true);
        imprumuturi.removeIf(imprumut -> imprumut.getCopieCarte().equals(carte) && imprumut.getMembru().equals(membru));
    }

    public void adaugaAutor(Autor autor){
        listaAutori.add(autor);
        JDBCRepositoryAutor x = new JDBCRepositoryAutor();
        x.add(new Autor(autor.getNume(),autor.getAnNastere(),autor.getRand()));
    }
    public void addMembru(Membru membru){
        listaMembri.put(membru.getNumarTelefon(),membru);
        JDBCRepositoryMembru x = new JDBCRepositoryMembru();
        x.add(new Membru(membru.getNume(),membru.getNumarTelefon()));
    }
    public void addCarte(Carte carte){
        colectieCarti.add(carte);
    }
    public void addCopieCarte(Carte carte,int id){
        copieCarti.add(new CopieCarte(id,carte));
    }
    public void deleteMembru(Membru m){
        listaMembri.remove(m.getNumarTelefon());
    }
    public void deleteCarte(Carte c){
        Iterator<Carte> iterator = colectieCarti.iterator();
        while (iterator.hasNext()) {
            Carte carte = iterator.next();
            if (carte.getTitlu().equalsIgnoreCase(c.getTitlu())) {
                iterator.remove();
            }
        }
        Iterator<CopieCarte> iterator2 = copieCarti.iterator();
        while (iterator2.hasNext()) {
            CopieCarte carte = iterator2.next();
            if (carte.getCarte().getTitlu().equalsIgnoreCase(c.getTitlu())) {
                iterator2.remove();
            }
        }
    }

    public void deleteCopieCarte(int cod){
        Iterator<CopieCarte> iterator2 = copieCarti.iterator();
        while (iterator2.hasNext()) {
            CopieCarte carte = iterator2.next();
            if (carte.getId() == cod) {
                iterator2.remove();
            }
        }
    }
}
