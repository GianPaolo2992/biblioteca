package entity;

import java.util.ArrayList;
import java.util.List;

public class Utenti {

    private int idu;
    private String nome;
    private String cognome;
    private boolean libri_prestati;
    private List<Libri> libriInPrestito;

    public Utenti(String nome, String cognome, boolean prestato) {
        this.nome = nome;
        this.cognome = cognome;
        this.libri_prestati = prestato;
    }
    public Utenti(String nome, String cognome, boolean prestato,List<Libri> libriInPrestito) {
        this.nome = nome;
        this.cognome = cognome;
        this.libri_prestati = prestato;
        this.libriInPrestito = libriInPrestito;
    }



    public Utenti() {
        this.nome = "";
        this.cognome = "";
        this.libri_prestati = false;
    }

    public Utenti(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Utenti(boolean prestato) {

        this.libri_prestati = prestato; // costruttore per cambiare valore al booleano quando utente prende
    }

    public Utenti(int idu) {
        this.idu = idu;
        this.libriInPrestito = new ArrayList<>();
    }




    public List<Libri> getListaLibri() {

        return libriInPrestito;
    }

    public void addLibriPrestito(Libri libro) {

        if (!libriInPrestito.contains(libro)) {
            System.out.println("libro aggiunto in utente");
            libriInPrestito.add(libro);
            setLibri_prestati(true);
        }
        System.out.println("fine addLibriPrestito");
    }

    public void removePrestito(Libri libro) {
        if (libriInPrestito != null) {
            libriInPrestito.remove(libro);
            if (libriInPrestito.isEmpty()) {
                setLibri_prestati(false);
            }
        }
    }

    public List<Libri> printLibriInPrestito() {
        if (libriInPrestito != null) {
            for (Libri libro : libriInPrestito) {
                System.out.println("Utente: " + nome + " " + cognome + " - " + libro.toString());
            }
        } else {
            System.out.println("Nessun libro in prestito per questo utente.");
        }
        return libriInPrestito;
    }

    public int getId() {
        return idu;
    }

    public void setId(int id) {
        this.idu = id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public boolean getLibri_prestati() {
        return libri_prestati;
    }

    public void setLibri_prestati(boolean libri_prestati) {
        this.libri_prestati = libri_prestati;
    }

    public String toString() {
        return "id : " + idu + "\n nome: " + nome + "\n cognome: " + cognome + "\n libri in prestito: " + libri_prestati;
    }

    public String toStringinLine() {
        return "id : " + idu + " nome: " + nome + " cognome: " + cognome + " libri in prestito: " + libri_prestati;
    }
}
