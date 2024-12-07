package entity;

import java.time.LocalDate;

public class Prestiti {
    private int id;
    private int idU;
    private LocalDate dataInizio;
    private String idL;
    private LocalDate dataFine;


//    public Prestiti(int idu,LocalDate dataInizio, String idl,LocalDate dataFine){
//        this.idU = idu;
//        this.dataInizio = dataInizio;
//        this.idL = idl;
//        this.dataFine = dataFine;
//
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public int getIdU() {
        return idU;
    }

    public String getIDL() {
        return idL;
    }


    public void setDataInizio(LocalDate data) {
        this.dataInizio = data;
    }

    public void setDataFine(LocalDate data) {
        this.dataFine = data;
    }

    public void setIDU(int idu) {
        this.idU = idu;
    }

    public void setIDL(String idl) {
        this.idL = idl;
    }

   public String toString(){
       return "/id : " + id + " /id utente : " + idU+ " /data inizio prestito : " + dataInizio +  " /data fine prestito :" +(dataFine != null ? dataFine : " null") + " /id Libro " + idL;
}

    // System.out.println("id : " + prestiti.getId() +"id utente : " + prestiti.getIdU()+ "/ data inizio prestito : " + prestiti.getDataInizio() + "/ data fine prestito :" + prestiti.getDataFine() + "/ id Libro " + prestiti.getIDL());
//

}
