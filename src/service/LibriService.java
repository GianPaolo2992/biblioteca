package service;

import entity.Libri;
import repository.LibriRepository;

import java.util.List;

public class LibriService {
    LibriRepository libriRepository = new LibriRepository();

    public void create(Libri oLibro){
        if (!libriRepository.libroIdIsPresentDB(oLibro.getId())){
            System.out.println("Libro creato con successo!");
            libriRepository.create(oLibro);
        }else {
            System.out.println("Il libro con l'id \"" + oLibro.getId() + "\" esiste già nel database.");
        }

    }

    public List<Libri> read(){
        return libriRepository.read();
    }
    public void update(Libri oLibro){
        libriRepository.update(oLibro);
    }

    public void delete (String id){
        Libri libro = new Libri();
        libro.setId(id);
        libriRepository.delete(libro);
    }
}
