import java.util.Scanner;
public class Main {

    public static <all> void main(String[] args) {
        ServiciuBiblioteca serviciuBiblioteca = new ServiciuBiblioteca();
        Scanner scanner = new Scanner(System.in);
        int idCopieCarte = 100;
        DatabaseConnection conn = new DatabaseConnection();
        conn.conexiune();

        CrudRepository crud = CrudRepository.getInstance();
        while (true) {
            System.out.println("\nBun venit!");
            System.out.println("Alegeti una dintre opțiunile de mai jos:");
            System.out.println("1. Vizualizare membrii inregistrati");
            System.out.println("2. Vizualizare carti");
            System.out.println("3. Imprumutare carte");
            System.out.println("4. Returnare carte");
            System.out.println("5. Adaugare membru");
            System.out.println("6. Adaugare carte");
            System.out.println("7. Adaugare imprumut online");
            System.out.println("8. Adauga copia unei carti");
            System.out.println("9. Afiseaza lista de autori");
            System.out.println("10. Adauga autor");
            System.out.println("11. Sorteaza lista de carti");
            System.out.println("12. Sterge un membru");
            System.out.println("13. Sterge o carte si copiile ei");
            System.out.println("14. Sterge o copie a unei carti");
            System.out.println("15. Modifica numarul de telefon al unui membru");
            System.out.println("16. Modifica adresa de email a unui membru");
            System.out.println("17. Afisare imprumuturi");
            System.out.println("18. Afiseaza toate imprumuturile unui membru");
            System.out.println("0. Iesire din program");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    serviciuBiblioteca.afisareMembri();
                    break;
                case 2:
                    serviciuBiblioteca.afisareCarti();
                    break;
                case 3:
                    System.out.println("Introduceți titlul cartii:");
                    String titlu = scanner.nextLine();
                    System.out.println("Introduceți autorul cartii:");
                    String numeAutor = scanner.nextLine();
                    System.out.println("Introduceți numele membrului care imprumuta cartea:");
                    String numeMembru = scanner.nextLine();
                    System.out.println("Introduceti numarul de telefon al membrului:");
                    String numarTelefon = scanner.nextLine();
                    serviciuBiblioteca.imprumutareCarte(titlu, numeAutor, numeMembru, numarTelefon);
                    break;
                case 4:
                    System.out.println("Introduceți titlul carții:");
                    titlu = scanner.nextLine();
                    System.out.println("Introduceți autorul cărții:");
                    numeAutor = scanner.nextLine();
                    System.out.println("Introduceți numele membrului care returnează cartea:");
                    numeMembru = scanner.nextLine();
                    System.out.println("Introduceti numarul de telefon al membrului:");
                    numarTelefon = scanner.nextLine();
                    serviciuBiblioteca.returnareCarte(titlu, numeAutor, numeMembru, numarTelefon);
                    break;
                case 5:
                    System.out.println("Introdu numele membrului:");
                    String nume = scanner.nextLine();
                    System.out.println("Introdu numarul de telefon al membrului:");
                    String nrTelefon = scanner.nextLine();
                    System.out.println("Membrul are cont Premium?(DA/NU):");
                    String premium = scanner.nextLine();
                    if(premium.equalsIgnoreCase("DA"))
                    {
                        System.out.println("Introdu adresa de email a membrului:");
                        String email = scanner.nextLine();
                        serviciuBiblioteca.addMembruPremium(nume,nrTelefon,email);
                    }
                    else
                        serviciuBiblioteca.addMembru(nume,nrTelefon);
                    break;
                case 6:
                    System.out.println("Introdu titlul cartii:");
                    titlu = scanner.nextLine();
                    System.out.println("Introdu numele autorului:");
                    numeAutor = scanner.nextLine();
                    System.out.println("Introdu anul publicarii:");
                    int an = scanner.nextInt();
                    serviciuBiblioteca.addCarte(titlu,numeAutor,an);
                    break;
                case 7:
                    System.out.println("Introdu titlul cartii:");
                    titlu = scanner.nextLine();
                    System.out.println("Introdu numele autorului:");
                    numeAutor = scanner.nextLine();
                    System.out.println("Introdu numele membrului care imprumuta cartea");
                    numeMembru = scanner.nextLine();
                    System.out.println("Introdu adresa de email a membrului:");
                    String email = scanner.nextLine();
                    serviciuBiblioteca.imprumutOnline(titlu,numeAutor,numeMembru,email);
                    break;
                case 8:
                    System.out.println("Introdu titlul cartii:");
                    titlu = scanner.nextLine();
                    System.out.println("Introdu numele autorului:");
                    numeAutor = scanner.nextLine();
                    serviciuBiblioteca.addCopieCarte(titlu,numeAutor,idCopieCarte);
                    idCopieCarte++;
                    break;
                case 9:
                    System.out.println("Autori:");
                    serviciuBiblioteca.viewAutori();
                    break;
                case 10:
                    System.out.println("Introdu numele autorului:");
                    numeAutor = scanner.nextLine();
                    System.out.println("Introdu anul nasterii:");
                    int anNastere = scanner.nextInt();
                    System.out.println("Introdu randul care ii este atribuit autorului:");
                    int rand = scanner.nextInt();
                    serviciuBiblioteca.addAutor(numeAutor,anNastere,rand);
                    break;
                case 11:
                    serviciuBiblioteca.sortListaDeCarti();
                    break;
                case 12:
                    System.out.println("Introdu numele membrului pe care vrei sa il elimini:");
                    numeMembru = scanner.nextLine();
                    System.out.println("Introdu numarul de telefon al membrului pe care vrei sa il elemini:");
                    nrTelefon = scanner.nextLine();
                    serviciuBiblioteca.deleteMembru(numeMembru,nrTelefon);
                    break;
                case 13:
                    System.out.println("Introdu titlul cartii pe care vrei sa o elimini:");
                    titlu = scanner.nextLine();
                    System.out.println("Introdu autorul cartii pe care vrei sa o elimini:");
                    numeAutor = scanner.nextLine();
                    serviciuBiblioteca.deleteCarte(titlu,numeAutor);
                    break;
                case 14:
                    System.out.println("Introdu codul cartii pe care vrei sa o elimini:");
                    int codCarte = scanner.nextInt();
                    serviciuBiblioteca.deleteCopieCarte(codCarte);
                    break;
                case 15:
                    System.out.println("Introdu numele membrului:");
                    numeMembru = scanner.nextLine();
                    System.out.println("Introdu vechiul numar de telefon:");
                    nrTelefon = scanner.nextLine();
                    System.out.println("Introdu noul numar de telefon:");
                    numarTelefon = scanner.nextLine();
                    serviciuBiblioteca.modificaNrTelefon(numeMembru,nrTelefon,numarTelefon);
                    break;
                case 16:
                    System.out.println("Introdu numele membrului:");
                    numeMembru = scanner.nextLine();
                    System.out.println("Introdu vechiul email:");
                    String emailVechi = scanner.nextLine();
                    System.out.println("Introdu noul email:");
                    String emailNou = scanner.nextLine();
                    serviciuBiblioteca.modificaEmail(numeMembru,emailVechi,emailNou);
                    break;
                case 17:
                    System.out.println("Imprumuturi:");
                    serviciuBiblioteca.afisareImprumuturi();
                    break;
                case 18:
                    System.out.println("Introdu numele membrului pentru care vrei imprumuturile");
                    numeMembru = scanner.nextLine();
                    serviciuBiblioteca.afiseazaImprumuturile(numeMembru);
                    break;
                case 0:
                    System.out.println("La revedere!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        }
    }
}