package service;

import entity.Libri;
import entity.Prestiti;
import repository.LibriRepository;
import repository.PrestitiRepository;

import java.time.LocalDate;
import java.util.List;

public class PrestitiService {

    PrestitiRepository prestitiRepository = new PrestitiRepository();
    LibriRepository libriRepository = new LibriRepository();

        public void create(int idu, LocalDate dataInizio, String idl, LocalDate dataFine){
            Prestiti oPrestiti = new Prestiti();
            oPrestiti.setIDU(idu);
            oPrestiti.setDataInizio(dataInizio);
            oPrestiti.setIDL(idl);
            oPrestiti.setDataFine(dataFine);
            prestitiRepository.create(oPrestiti);


        }

        public List<Prestiti> read(){
            return prestitiRepository.read();
        }
    public void update(int id, int idu, LocalDate dataInizio, String idl, LocalDate dataFine) {
        // Controlla se il libro esiste nella tabella 'libri'
        if (!libriRepository.libroIdIsPresentDB(idl.toUpperCase())) { // Conversione a maiuscolo per sicurezza
            System.out.println("Il libro con ID " + idl + " non esiste nella tabella 'libri'.");
            return; // Esci se il libro non esiste
        }

        Prestiti oPrestiti = new Prestiti();
        oPrestiti.setId(id);
        oPrestiti.setIDU(idu);
        oPrestiti.setDataInizio(dataInizio);
        oPrestiti.setIDL(idl.toUpperCase()); // Conversione a maiuscolo per sicurezza
        oPrestiti.setDataFine(dataFine);
        prestitiRepository.update(oPrestiti);
    }


    public void delete (int id){
            Prestiti prestiti = new Prestiti();
            prestiti.setId(id);
            prestitiRepository.delete(prestiti);
        }
    }


