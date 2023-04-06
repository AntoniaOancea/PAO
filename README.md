# BIBLIOTECA

## Tipuri de obiecte

	a) Autor (nume, anNastere, rand)
	b) Carte (titlu, autor, anPublicare)
	c) CopieCarte (carte, id)
	d) Membru (nume, nrTelefon)
	e) MembruPremium (nume, nrTelefon, email)
	f) Imprumut (copieCarte, membru, dataImprumut, dataScadenta)
	g) ImprumutOnline (carte,membruPremium, dataImprumut)
	
	h) 	List<Carte> colectieCarti;
		List<CopieCarte> copieCarti;
		List<Imprumut> imprumuturi;
		List<ImprumutOnline> imprumutOnline;
		List<Imprumut> copieImprumuturi;
	
	i) 	TreeSet<Autor> listaAutori;

	j)	Map<String, Membru> listaMembri;

### Interogari

	a) Vizualizare membrii inregistrati.
  
	b) Vizualizare carti.
  
	c) Vizualizare autori.
  
	d) Vizualizare imprumuturi.
  
	e) Vizualizarea imprumuturilor unui membru.
  

### Actiuni

	a) Adaugare imprumut.
  
	b) Returnare carte.
  
	c) Adaugare membru.
  
	d) Adaugare carte.
  
	e) Adaugare imprumut online.
  
	f) Adaugarea copiei unei carti.
  
	g) Adauga autor.
  
	h) Sortare pe lista de carti.
  
	i) Sterge un membru.
  
	j) Sterge o carte si copiile ei.
  
	k) Sterge o copie a unei carti.
  
	l) Modifica numarul de telefon al unui membru.
  
	m) Modifica adresa de email a unui membru.
