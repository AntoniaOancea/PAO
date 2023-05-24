package model;

import java.time.LocalDate;
//orice membru poate imprumuta maxim 5 carti pe luna
public class Imprumut {
    private CopieCarte carte;
    private Membru membru;
    private LocalDate dataImprumut;
    private LocalDate dataScadenta;
    public Imprumut(CopieCarte carte, Membru membru) {
        this.carte = carte;
        this.membru = membru;
        this.dataImprumut = LocalDate.now();
        this.dataScadenta = dataImprumut.plusDays(14);
    }

    // metode de acces


    public CopieCarte getCopieCarte() {
        return carte;
    }

    public void setCopieCarte(CopieCarte carte) {
        this.carte = carte;
    }

    public Membru getMembru() {
        return membru;
    }

    public void setMembru(Membru membru) {
        this.membru = membru;
    }

    public LocalDate getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(LocalDate dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public LocalDate getDataScadenta() {
        return dataScadenta;
    }

    public void setDataScadenta(LocalDate dataScadenta) {
        this.dataScadenta = dataScadenta;
    }

}
