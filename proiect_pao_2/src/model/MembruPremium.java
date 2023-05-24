package model;
//poate primi cartile scanate pe email platind un abonament
public class MembruPremium extends Membru{

    private String email;

    public MembruPremium(String nume, String numarTelefon, String email) {
        super(nume, numarTelefon);
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
