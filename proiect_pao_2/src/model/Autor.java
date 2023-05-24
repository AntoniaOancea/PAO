package model;

public class Autor implements Comparable<Autor>{
    private String nume;
    private int anNastere;

    protected int rand;//cartile sunt sortate pe randuri in functie de autor

    public Autor(String nume, int anNastere,int rand) {
        this.nume = nume;
        this.anNastere = anNastere;
        this.rand = rand;

    }

    // metode de acces pentru atributele private

    public String getNume() {
        return nume;
    }

    public int getAnNastere() {
        return anNastere;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAnNastere(int anNastere) {
        this.anNastere = anNastere;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Autor autor = (Autor) o;

        if (anNastere != autor.anNastere) return false;
        return nume.equals(autor.nume);
    }

    @Override
    public int hashCode() {
        int result = nume.hashCode();
        result = 31 * result + anNastere;
        return result;
    }

    public int compareTo(Autor autor) {
        int cmp = nume.compareTo(autor.nume);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(anNastere, autor.anNastere);
    }

}

