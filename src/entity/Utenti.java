package entity;

import java.util.List;

public class Utenti {

    private int id;
    private String nome;
    private String cognome;
    private boolean libri_prestati;
    private List<Libri> libriInPrestito;

    public Utenti(String nome,String cognome, boolean prestato){
        this.nome= nome;
        this.cognome=cognome;
        this.libri_prestati=prestato;
    }


    public Utenti() {
        this.nome= "";
        this.cognome="";
        this.libri_prestati=false;
    }

    public Utenti(String nome,String cognome) {
        this.nome= nome;
        this.cognome=cognome;
    }
    public Utenti( boolean prestato){

        this.libri_prestati=prestato; // costruttore per cambiare valore al booleano quando utente prende
    }


    public List<Libri> getListaLibri(){
        return libriInPrestito;
    }

    public void addLibriPrestito(Libri libro){
        if (!libriInPrestito.contains(libro)){
            libriInPrestito.add(libro);
        }
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }


    public String getCognome(){
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

    public String toString(){
        return "id : " + id + "\n nome: " + nome + "\n cognome: " + cognome + "\n libri in prestito: " + libri_prestati;
    }
    public String toStringinLine(){
        return "id : " + id + " nome: " + nome + " cognome: " + cognome + " libri in prestito: " + libri_prestati;
    }
}
