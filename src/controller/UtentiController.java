package controller;

import entity.Libri;
import entity.Utenti;
import service.UtentiService;

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
        System.out.println("ecco una lista di libri");
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
    /*public void updateLibroprestato() {

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
        Utenti oUtente = new Utenti(prestato);
        utentiService.update(oUtente);
    }*/

    public void delete() {
        read();
        System.out.println("scegli un id tra i libri mostrati per eliminarlo");
        int idu = scanner.nextInt();
        scanner.nextLine();
        Utenti oUtente = new Utenti(idu);
        utentiService.delete(oUtente);
    }
}
