package controller;

import config.DbConnection;
import entity.Libri;
import entity.Prestiti;
import entity.Utenti;
import repository.LibriRepository;
import repository.PrestitiRepository;
import repository.UtentiRepository;
import service.UtentiService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UtentiController {
    Scanner scanner = new Scanner(System.in);
    UtentiService utentiService = new UtentiService();

    public void create() {

        System.out.println("inserisci il nome del nuovo utente");
        String nome = scanner.nextLine();
        System.out.println("inserisci il cognome dell' utente ");
        String cognome = scanner.nextLine();
        System.out.println("l' utente ha dei libri in prestito?");
        System.out.println("1. vero oppure 0.falso");
        boolean prestato = false;
        boolean isValid = false;

        while (!isValid) {
            int scelta = 0;
            scelta = scanner.nextInt();
            scanner.nextLine();
            if (scelta == 1) {
                prestato = true;
                isValid = true;
            } else if (scelta == 0) {

                isValid = true;
            } else {
                System.out.println("scelta non valida scegliere 1. vero oppure 0.falso");
            }
        }
        Utenti oUtente = new Utenti(nome, cognome, prestato);
        utentiService.create(oUtente);
    }

    public void read() {
        System.out.println("ecco una lista di utenti");
        List<Utenti> listaUtenti = utentiService.read();
        for (Utenti utente : listaUtenti) {
            System.out.println(utente.toStringinLine());
        }

    }

    public void update() {
        read();
        System.out.println("inserisci un id alfa numerico da aggiornare");
        int idu = scanner.nextInt();
        scanner.nextLine();
        System.out.println("inserisci il nome del nuovo utente");
        String nome = scanner.nextLine();
        System.out.println("inserisci il cognome dell' utente ");
        String cognome = scanner.nextLine();

        Utenti oUtente = new Utenti(nome, cognome);
        utentiService.update(oUtente, idu);
    }
    /*public void printLibriInPrestito() {
        read(); // Mostra la lista degli utenti
        System.out.println("Scegli l'ID dell'utente per visualizzare i libri in prestito:");
        int idu = scanner.nextInt();
        scanner.nextLine();

        UtentiRepository utentiRepository = new UtentiRepository();
        Utenti utente = utentiRepository.findById(idu); // Recupera l'utente dal servizio
        if (utente != null) {

            List<Libri> libriInPrestito = utente.getListaLibri();
            utente.toString();
            for(Libri libro: libriInPrestito){
                libro.toString();
            }
            if (libriInPrestito == null || libriInPrestito.isEmpty()) {
                System.out.println("L'utente non ha libri in prestito.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }
    }*/


    public void printLibriInPrestito() {
        read(); // Mostra la lista degli utenti
        System.out.println("Scegli l'ID dell'utente per visualizzare i libri in prestito:");
        int idu = scanner.nextInt();
        scanner.nextLine();

        UtentiRepository utentiRepository = new UtentiRepository();
        Utenti utente = utentiRepository.findById(idu); // Recupera l'utente dal servizio
        if (utente != null) {
            List<Libri> libriInPrestito = utente.printLibriInPrestito();
            if (libriInPrestito == null || libriInPrestito.isEmpty()) {
                System.out.println("L'utente non ha libri in prestito.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }
    }



    public void delete() {
        read();
        System.out.println("scegli un id tra i libri mostrati per eliminarlo");
        int idu = scanner.nextInt();
        scanner.nextLine();
        Utenti oUtente = new Utenti(idu);
        if (!oUtente.getLibri_prestati()) {
            utentiService.delete(oUtente);
        } else {
            System.out.println("non puoi eliminare un utente che ha prestiti attivi");
        }

    }

}
