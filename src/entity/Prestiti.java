package entity;

import java.time.LocalDate;

public class Prestiti {
    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int idU;
    private String idL;

    public void setId(int id) {
        this.id = id;
    }

     public int getId(){
        return id;
     }

    public LocalDate getDataFine() {
        return dataFine;
    }
    public LocalDate getDataInizio(){
        return dataInizio;
    }

    public int getIdU() {
        return idU;
    }

    public String getIDL(){
        return  idL;
    }


    public void setDataInizio(LocalDate data){
        this.dataInizio = data;
    }

    public void setDataFine(LocalDate data){
        this.dataFine = data;
    }
    public void setIDU(int idu){
        this.idU = idu;
    }
    public void setIDL(String idl){
        this.idL = idl;
    }

}
