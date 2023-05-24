package model;

import java.time.LocalDate;

public class ImprumutOnline {
    private Carte carte;
    private MembruPremium membru;
    private LocalDate dataImprumut;

    public ImprumutOnline(Carte carte, MembruPremium membru) {
        this.carte = carte;
        this.membru = membru;
        this.dataImprumut = LocalDate.now();
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public MembruPremium getMembru() {
        return membru;
    }

    public void setMembru(MembruPremium membru) {
        this.membru = membru;
    }

    public LocalDate getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(LocalDate dataImprumut) {
        this.dataImprumut = dataImprumut;
    }


}
