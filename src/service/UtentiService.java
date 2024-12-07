package service;

import entity.Utenti;
import repository.UtentiRepository;

import java.util.List;

public class UtentiService {
    UtentiRepository utentiRepository = new UtentiRepository();

    public void create(Utenti oUtente) {
        utentiRepository.create(oUtente);

    }

    public List<Utenti> read() {
        return utentiRepository.read();
    }

    public void update(Utenti oUtente, int id) {
        utentiRepository.update(oUtente, id);
    }

    public void delete(Utenti oUtente) {
        utentiRepository.delete(oUtente);
    }
}
