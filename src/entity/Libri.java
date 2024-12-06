package entity;

public class Libri {

    private String idl;
    private String titolo;
    private String autore;
    private Prestiti prestito;

    public Libri(String id,String titolo,String autore){
        this.idl = id;
        this.titolo = titolo;
        this.autore = autore;
    }

    public Libri() {
        this.idl = "";
        this.titolo = "";
        this.autore = "";
    }

    public String getId() {
        return idl;
    }

    public void setId(String id) {
        this.idl = id;
    }

    public String getTitolo(){
        return titolo;
    }

    public void setTitolo(String titolo){
        this.titolo = titolo;
    }

    public String getAutore(){
        return autore;
    }
    public void setAutore(String autore){
        this.autore = autore;
    }
    public Prestiti getPrestito(){
        return prestito;
    }

    public void setPrestito(Prestiti prestito){
        this.prestito = prestito;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "id='" + idl + '\'' +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
               // ", prestito=" + (prestito != null ? prestito.toString() : "null") +
                '}';
    }

}
