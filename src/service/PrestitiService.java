package service;

import entity.Libri;
import entity.Prestiti;
import entity.Utenti;
import repository.LibriRepository;
import repository.PrestitiRepository;
import repository.UtentiRepository;

import java.time.LocalDate;
import java.util.List;

public class PrestitiService {

    PrestitiRepository prestitiRepository = new PrestitiRepository();
    LibriRepository libriRepository = new LibriRepository();
    UtentiRepository utentiRepository = new UtentiRepository();

    /*public void create(int idu, LocalDate dataInizio, String idl, LocalDate dataFine) {
        Prestiti oPrestiti = new Prestiti();
        oPrestiti.setIDU(idu);
        oPrestiti.setDataInizio(dataInizio);
        oPrestiti.setIDL(idl);
        oPrestiti.setDataFine(dataFine);
        prestitiRepository.create(oPrestiti);


    }*/
    public void create(Prestiti oPrestiti) {

        prestitiRepository.create(oPrestiti);


    }


    public List<Prestiti> read() {
        return prestitiRepository.read();
    }

    public void update(int idp, int idu, LocalDate dataInizio, String idl) {
        // Controlla se il libro esiste nella tabella 'libri'
        if (!libriRepository.libroIdIsPresentDB(idl.toUpperCase())) { // Conversione a maiuscolo per sicurezza
            System.out.println("Il libro con ID " + idl + " non esiste nella tabella 'libri'.");
            return; // Esci se il libro non esiste
        }


        Prestiti oPrestiti = new Prestiti();
        oPrestiti.setId(idp);
        oPrestiti.setIDU(idu);
        oPrestiti.setDataInizio(dataInizio);
        oPrestiti.setIDL(idl.toUpperCase()); // Conversione a maiuscolo per sicurezza

        prestitiRepository.update(oPrestiti);
    }

    public void updateDataFine(LocalDate dataFine, int idp) {


        Prestiti oPrestiti = new Prestiti();
        oPrestiti.setId(idp);

        oPrestiti.setDataFine(dataFine);
        prestitiRepository.updateDataFine(oPrestiti);
    }


    public void delete(int prestitoId) {

        // Recupera il prestito dal repository
        Prestiti prestito = prestitiRepository.findById(prestitoId);

        Utenti utente = utentiRepository.findById(prestito.getIdU());

        // Rimuovi il prestito dalla tabella 'prestiti' nel database
        prestitiRepository.delete(prestito);

        // Recupera il libro associato
        Libri libro = libriRepository.findById(prestito.getIDL());
        if (libro != null && libro.getPrestito() != null && libro.getPrestito().getId() == prestitoId) {
            // Rimuovi l'associazione del prestito dal libro
            libro.setPrestito(null);
            utente.removePrestito(libro);
            // Non è necessario aggiornare il libro nel repository se il database non gestisce questa relazione


        }
    }

}



