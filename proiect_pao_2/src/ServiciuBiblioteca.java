import JDBCRepository.JDBCRepositoryAutor;
import JDBCRepository.JDBCRepositoryCarte;
import JDBCRepository.JDBCRepositoryMembru;
import JDBCRepository.JDBCRepositoryMembruPremium;
import model.*;

import java.util.List;
import java.util.Map;

public class ServiciuBiblioteca {
    private BibliotecaRepository biblioteca;

    public ServiciuBiblioteca() {
        this.biblioteca = biblioteca.getInstance();
    }

    public BibliotecaRepository getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaRepository biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void afisareCarti() {
        List<Carte> colectieCarti = biblioteca.getColectieCarti();
        System.out.println("Cărți în bibliotecă:");
        for (Carte carte : colectieCarti) {
            System.out.println(carte.getTitlu() + " de " + carte.getAutor().getNume() + " (" + carte.getAnPublicare() + ")");
        }
        JDBCRepositoryCarte x = new JDBCRepositoryCarte();
        x.getAll();

    }

    public void afisareMembri() {
        Map<String, Membru> listaMembri = biblioteca.getListaMembri();
        System.out.println("Membri înregistrați:");
        for (Membru membru : listaMembri.values()) {
            if (membru instanceof MembruPremium)
                System.out.println(membru.getNume() + " (" + membru.getNumarTelefon() + "," + ((MembruPremium) membru).getEmail() + ")");
            else
                System.out.println(membru.getNume() + " (" + membru.getNumarTelefon() + ")");
        }
        System.out.println("Baza de date:");
        JDBCRepositoryMembru x = new JDBCRepositoryMembru();
        x.getAll();
    }

    public void afisareImprumuturi() {
        List<Imprumut> imprumuturi = biblioteca.getCopieImprumuturi();
        for (Imprumut imprumut : imprumuturi) {
            System.out.println(imprumut.getCopieCarte().getCarte().getTitlu() + " de " + imprumut.getCopieCarte().getCarte().getAutor().getNume() + " împrumutată de "
                    + imprumut.getMembru().getNume() + " (" + imprumut.getMembru().getNumarTelefon() + ")");
        }
    }
    public void imprumutOnline(String titlu, String autor, String numeMembru, String email) {

        Map<String, Membru>  listaMembri = biblioteca.getListaMembri();
        List<Carte> colectieCarti = biblioteca.getColectieCarti();

        Carte carte = null;
        for(Carte c:colectieCarti){
            if(c.getTitlu().equalsIgnoreCase(titlu) && c.getAutor().equals(autor)){
                carte = c;
                break;
            }
        }
        for(Membru value : listaMembri.values())
            if(value.getNume().equalsIgnoreCase(numeMembru) &&
                    value instanceof MembruPremium &&
                    ((MembruPremium) value).getEmail().equalsIgnoreCase(email)){
                biblioteca.imprumutOnline(carte,(MembruPremium) value);
            }
    }
    public void imprumutareCarte(String titlu, String autor, String numeMembru, String numarTelefon) {
        Map<String, Membru> listaMembri = biblioteca.getListaMembri();
        List<CopieCarte> colectieCarti = biblioteca.getCopieCarti();
//        for(CopieCarte c:colectieCarti)
//            System.out.println(c.getCarte().getTitlu()+c.getDisponibila()+c.getId());
        CopieCarte carte = null;
        for (CopieCarte c : colectieCarti) {
            if (c.getCarte().getTitlu().equalsIgnoreCase(titlu) && c.getCarte().getAutor().getNume().equalsIgnoreCase(autor) && c.getDisponibila()==true) {
                carte = c;
                break;
            }
        }

        Membru membru = listaMembri.get(numarTelefon);

        if (carte == null) {
            System.out.println("Cartea " + titlu + " de " + autor + " nu este disponibilă în bibliotecă.");
        } else if (membru == null) {
            System.out.println("Nu există un membru înregistrat cu numărul de telefon " + numarTelefon + ".");
        } else {
            biblioteca.imprumutaCarte(carte, membru);
            for(CopieCarte c: biblioteca.getCopieCarti())
                if(c.equals(carte))
                    System.out.println("Cartea " + titlu + " de " + autor + " a fost împrumutată de " + numeMembru + ".");
        }
    }

    public void returnareCarte(String titlu, String autor, String numeMembru, String numarTelefon) {
        Map<String, Membru> listaMembri = biblioteca.getListaMembri();

        Carte carte = null;
        for (Carte c : biblioteca.getColectieCarti()) {
            if (c.getTitlu().equalsIgnoreCase(titlu) && c.getAutor().equals(autor)) {
                carte = c;
                break;
            }
        }

        Membru membru = listaMembri.get(numarTelefon);

        if (carte == null) {
            System.out.println("Cartea " + titlu + " de " + autor + " nu este disponibilă în bibliotecă.");
        } else if (membru == null) {
            System.out.println("Nu există un membru înregistrat cu numărul de telefon " + numarTelefon + ".");
        } else {
            for(Imprumut i: biblioteca.getImprumuturi())
                if(i.equals(carte)) {
                    biblioteca.returneazaCarte(i.getCopieCarte(), membru);
                    System.out.println("Cartea " + titlu + " de " + autor + " a fost returnată de " + numeMembru + ".");
                }
        }
    }
    public void addMembruPremium(String nume,String nrTelefon,String email) {
        biblioteca.addMembru(new MembruPremium(nume,nrTelefon,email));
        JDBCRepositoryMembruPremium x = new JDBCRepositoryMembruPremium();
        x.add(new MembruPremium(nume,nrTelefon,email));
    }
    public void addMembru(String nume,String nrTelefon) {
        biblioteca.addMembru(new Membru(nume,nrTelefon));
    }
    public void addCarte(String titlu, String numeAutor, int anPublicare){
        Autor autor = null;
        for(Autor a: biblioteca.getListaAutori()){
            if(a.getNume().equalsIgnoreCase(numeAutor))
            {
                autor = a;
                break;
            }
        }
        if(autor == null)
            System.out.println("Nu exista autorul.Adauga mai intai autorul si dupa cartea.");
        else{
            biblioteca.addCarte(new Carte(titlu,autor,anPublicare));
        }

        try {
            JDBCRepositoryCarte x = new JDBCRepositoryCarte();
            x.add(new Carte(titlu,new Autor(numeAutor,0,0 ),anPublicare));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void addCopieCarte(String titlu,String numeAutor,int id){
        Carte carte = null;
        for(Carte c:biblioteca.getColectieCarti()){
            if(c.getTitlu().equalsIgnoreCase(titlu) && c.getAutor().getNume().equalsIgnoreCase(numeAutor)){
                carte = c;
                break;
            }
        }
        if(carte == null){
            System.out.println("Cartea "+ titlu + "de " + numeAutor + "nu exista!");
        }
        else{
            biblioteca.addCopieCarte(carte,id);
        }
    }
    public void viewAutori(){
        for(Autor a: getBiblioteca().getListaAutori()){
            System.out.println(a.getNume() + ":" + a.getAnNastere() + "----rand:" + a.getRand());
        }
        JDBCRepositoryAutor x = new JDBCRepositoryAutor();
        x.getAll();
    }

    public void addAutor(String numeAutor, int anNastere, int rand){
        biblioteca.adaugaAutor(new Autor(numeAutor,anNastere,rand));
    }

    public void sortListaDeCarti(){
        biblioteca.sortareDupaTitlu();
    }
    public void deleteMembru(String numeMembru,String nrTelefon){
//        Membru membru = null;
//        for(Membru m: biblioteca.getListaMembri().values()){
//            if(m.getNume().equalsIgnoreCase(numeMembru) && m.getNumarTelefon().equalsIgnoreCase(nrTelefon)){
//                membru = m;
//                break;
//            }
//        }
//        if(membru == null)
//            System.out.println("Membrul cu numele " + numeMembru + " si numarul de telefon " + nrTelefon + " nu exista!");
//        else
//            biblioteca.deleteMembru(membru);

        JDBCRepositoryMembru x = new JDBCRepositoryMembru();
        x.delete(numeMembru);
    }

    public void deleteCarte(String titlu, String numeAutor){
        Carte carte = null;
        for(Carte c:biblioteca.getColectieCarti()){
            if(c.getAutor().getNume().equalsIgnoreCase(numeAutor) && c.getTitlu().equalsIgnoreCase(titlu)){
                carte = c;
                break;
            }
        }
        if(carte == null)
            System.out.println("Cartea " + titlu + " de " + numeAutor + " nu exista!");
        else
            biblioteca.deleteCarte(carte);
        JDBCRepositoryCarte x = new JDBCRepositoryCarte();
        x.delete(titlu);
    }

    public void deleteCopieCarte(int cod){
        biblioteca.deleteCopieCarte(cod);
    }

    public void modificaNrTelefon(String numeMembru,String nrVechi,String nrNou){
        Membru membru =null;
        JDBCRepositoryMembru x = new JDBCRepositoryMembru();
        x.update(new Membru(numeMembru,nrNou));

//        for(Membru m:biblioteca.getListaMembri().values()){
//            if(m.getNumarTelefon().equalsIgnoreCase(nrVechi) && m.getNume().equalsIgnoreCase(numeMembru)){
//                m.setNumarTelefon(nrNou);
//                membru = m;
//                break;
//            }
//        }
//        if(membru == null)
//            System.out.println("Membrul cu numele " + numeMembru + " si numarul de telefon " + nrVechi + " nu exista!");

    }

    public void modificaEmail(String nume, String emailVechi, String emailNou){
        Membru membru =null;
        JDBCRepositoryMembruPremium x = new JDBCRepositoryMembruPremium();
        x.update(new MembruPremium(nume,null,emailNou));
        for(Membru m:biblioteca.getListaMembri().values()){
            if (m instanceof MembruPremium)
                if(((MembruPremium) m).getEmail().equalsIgnoreCase(emailVechi) && m.getNume().equalsIgnoreCase(nume)){
                    ((MembruPremium) m).setEmail(emailNou);
                    membru = m;
                    break;
                }
        }
        if(membru == null)
            System.out.println("Membrul cu numele " + nume + " si adresa de email " + emailVechi + " nu exista!");
    }
    public void afiseazaImprumuturile(String nume){
        Membru membru = null;
        for(Membru m : biblioteca.getListaMembri().values())
            if(m.getNume().equalsIgnoreCase(nume)){
                membru = m;
                break;
            }
        int nr = 0;
        if(membru == null)
            System.out.println("Membrul nu exista!");
        else {
            if (membru instanceof MembruPremium)
                for (ImprumutOnline i : biblioteca.getImprumutOnline()) {
                    if(i.getMembru().getNume().equalsIgnoreCase(membru.getNume()) && i.getCarte() != null) {
                        System.out.println(i.getCarte().getTitlu() + " de " + i.getCarte().getAutor().getNume() + " la date de " + i.getDataImprumut());
                        nr++;
                    }
                }
            for(Imprumut i:biblioteca.getCopieImprumuturi()){
                System.out.println(i.getCopieCarte().getCarte().getTitlu() + " de " + i.getCopieCarte().getCarte().getAutor().getNume() + " la data de " + i.getDataImprumut());
                nr++;
            }

        }
        if(nr == 0)
            System.out.println("Membrul dat nu are inregistrat un imprumut");

    }
}
