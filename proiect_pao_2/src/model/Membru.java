package model;

public class Membru {
    protected String nume;
    protected String numarTelefon;

    public Membru(String nume, String numarTelefon) {
        this.nume = nume;
        this.numarTelefon = numarTelefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membru membru = (Membru) o;

        if (!nume.equals(membru.nume)) return false;
        return numarTelefon.equals(membru.numarTelefon);
    }

    @Override
    public int hashCode() {
        int result = nume.hashCode();
        result = 31 * result + numarTelefon.hashCode();
        return result;
    }
    // metode de acces

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public boolean isVip() {
        return this instanceof MembruPremium;
    }
}

