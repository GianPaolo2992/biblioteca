package service;

import entity.Libri;
import repository.LibriRepository;

import java.util.List;

public class LibriService {
    LibriRepository libriRepository = new LibriRepository();

    public void create(Libri oLibro) {
        if (!libriRepository.libroIdIsPresentDB(oLibro.getId())) {
            System.out.println("Libro creato con successo!");
            libriRepository.create(oLibro);
        } else {
            System.out.println("Il libro con l'id \"" + oLibro.getId() + "\" esiste già nel database.");
        }

    }

    public List<Libri> read() {
        return libriRepository.read();
    }

    public void update(Libri oLibro) {
        libriRepository.update(oLibro);
    }

    public void delete(String id) {
        Libri libro = libriRepository.findById(id);
        libro.setId(id);
        if (libro.getPrestito() == null) {
            libriRepository.delete(libro);
        } else if (libro == null) {
            System.out.println("libro non presente nell' archivio");

        } else {
            System.out.println("non puoi eliminare un libro che attualmente è in prestito");
        }

    }


    public List<Libri> bookBeyondPeriod(){
        return libriRepository.bookBeyondPeriod();
    }
}
