package model;
//membrul imprumuta o copie, nu o carte
public class CopieCarte {
    private int id;
    private Carte carte;
    private boolean disponibila;//cand cineva o imprumuta ==> false


    // constructorul clasei
    public CopieCarte(int id, Carte carte) {
        this.id = id;
        this.carte = carte;
        this.disponibila = true;
    }

    // metode de acces pentru atribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public boolean getDisponibila() {
        return disponibila;
    }

    public void setDisponibila(boolean disponibila) {
        this.disponibila = disponibila;
    }
}


