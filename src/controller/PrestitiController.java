package controller;

import entity.Libri;

import entity.Prestiti;
import entity.Utenti;
import repository.LibriRepository;
import repository.PrestitiRepository;
import repository.UtentiRepository;
import service.LibriService;
import service.PrestitiService;
import service.UtentiService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Scanner;

public class PrestitiController {

    Scanner scanner = new Scanner(System.in);
    UtentiController utentiController = new UtentiController();
    LibriController libriController = new LibriController();
    PrestitiService prestitiService = new PrestitiService();

    /* public void create(){
         utentiController.read();

         System.out.println("crea un oggetto prestito");
         System.out.println("inserisci l'id dell'utente ");
         int idu = scanner.nextInt();
         scanner.nextLine();
         System.out.println("inserisci la data inizio prestito");
         String dataInizioInput = scanner.nextLine();
         DateTimeFormatter formatterInizio = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         LocalDate dataInizio = LocalDate.parse(dataInizioInput,formatterInizio);

         libriController.read();
         System.out.println("inserisci l'id del libro ");
         String idl = scanner.nextLine();

         System.out.println("inserisci la data fine prestito");
         String dataFineInput = scanner.nextLine();
         DateTimeFormatter formatterFine = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         LocalDate dataFine = LocalDate.parse(dataFineInput,formatterFine);


         Prestiti prestito = getPrestiti(idu, dataInizio, idl, dataFine);

         LibriRepository libriRepository = new LibriRepository();
         // Aggiorna il libro per impostare il prestito
         Libri libro = libriRepository.findById(prestito.getIDL());
         if (libro != null && libro.getPrestito() == null) {
             prestitiService.create(idu,dataInizio,idl,dataFine);
             libro.setPrestito(prestito);
         }
         else if(libro == null ){
             throw new RuntimeException("libro non trovato");
         }
         else if( libro.getPrestito() != null){

             throw new RuntimeException("libro non disponibile in archivio, gia in uso da un altro utente");

         }
     }*/
    public void create() {
        utentiController.read();

        System.out.println("crea un oggetto prestito");
        System.out.println("inserisci l'id dell'utente ");
        int idu = scanner.nextInt();
        scanner.nextLine();

        System.out.println("inserisci la data inizio prestito");
        String dataInizioInput = scanner.nextLine();
        DateTimeFormatter formatterInizio = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInizio = LocalDate.parse(dataInizioInput, formatterInizio);

        libriController.read();

        System.out.println("inserisci l'id del libro ");
        String idl = scanner.nextLine().toUpperCase();



        Prestiti prestito = getPrestiti(idu, dataInizio, idl, null);

        LibriRepository libriRepository = new LibriRepository();
        UtentiRepository utentiRepository = new UtentiRepository();

        // Aggiorna il libro per impostare il prestito
        Libri libro = libriRepository.findById(prestito.getIDL());
        Utenti utente = utentiRepository.findById(prestito.getIdU());

        if (libro != null && libro.getPrestito() == null) {
            prestitiService.create(prestito);
            libro.setPrestito(prestito);
            utente.addLibriPrestito(libro);
        } else if (libro == null) {
            System.out.println("libro non trovato");
        } else if (libro.getPrestito() != null) {
            System.out.println("libro non disponibile in archivio, gia in uso da un altro utente");


        }
    }

    private static Prestiti getPrestiti(int idu, LocalDate dataInizio, String idl, LocalDate dataFine) {
        Prestiti prestito = new Prestiti();
        prestito.setIDU(idu);
        prestito.setDataInizio(dataInizio);
        prestito.setIDL(idl);
        prestito.setDataFine(dataFine);
        return prestito;
    }


    public void read() {
        System.out.println("ecco una lista di prestiti");
        List<Prestiti> listaPrestiti = prestitiService.read();
        for (Prestiti prestiti : listaPrestiti) {
            System.out.println(prestiti.toString());
        }

    }

    public void update() {
        read();
        System.out.println("inserisci l'id dell'prestito da aggiornare ");
        int idp = scanner.nextInt();
        utentiController.read();
        System.out.println("crea un oggetto prestito");
        System.out.println("inserisci l'id dell'utente ");
        int idu = scanner.nextInt();
        scanner.nextLine();
        System.out.println("inserisci la data inizio prestito");
        String dataInizioInput = scanner.nextLine();
        DateTimeFormatter formatterInizio = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInizio = LocalDate.parse(dataInizioInput, formatterInizio);

        libriController.read();
        System.out.println("inserisci l'id del libro ");
        String idl = scanner.nextLine().toUpperCase();

           /* System.out.println("inserisci la data fine prestito");
            String dataFineInput = scanner.nextLine();
            DateTimeFormatter formatterFine = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataFine = LocalDate.parse(dataFineInput,formatterFine);*/

        prestitiService.update(idp, idu, dataInizio, idl);

    }

    public void updateDataFine() {
        read();
        System.out.println("inserisci l'id dell'prestito da aggiornare ");
        int idp = scanner.nextInt();
        scanner.nextLine();
        System.out.println("inserisci la data fine prestito");
        String dataFineInput = scanner.nextLine();
        DateTimeFormatter formatterUpdateFine = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFine = LocalDate.parse(dataFineInput, formatterUpdateFine);

        prestitiService.updateDataFine(dataFine, idp);
        PrestitiRepository prestitiRepository = new PrestitiRepository();
        LibriRepository libriRepository = new LibriRepository();
        UtentiRepository utentiRepository = new UtentiRepository();

        Prestiti prestito = prestitiRepository.findById(idp);
        Libri libro = libriRepository.findById(prestito.getIDL());
        Utenti utente = utentiRepository.findById(prestito.getIdU());
        if (libro != null) {
            libro.setPrestito(null);
            utente.removePrestito(libro);


        }
    }


    public void delete() {
        read();
        System.out.println("scegli un id tra i prestit mostrati per eliminarlo");
        int idp = scanner.nextInt();
        scanner.nextLine();

        prestitiService.delete(idp);
    }

    public void readAllLibriXUtente(){
        utentiController.read();
        System.out.println("inserisci l'id dell'utente da stampare ");
        int idu = scanner.nextInt();
        scanner.nextLine();
        List<Libri> listaLibri = prestitiService.readLibriXUtente(idu);
        UtentiRepository utentiRepository = new UtentiRepository();
        Utenti utenti = utentiRepository.findById(idu);
        System.out.println("ecco una lista di prestiti");
        System.out.println(utenti.toStringinLine());
        for (Libri libri : listaLibri) {

            System.out.println(libri.toString());
        }


    }
    public void ordiniXutenteCronologico(){
        LibriRepository libriRepository = new LibriRepository();
        utentiController.read();
        System.out.println("inserisci l'id dell'utente da stampare ");
        int idu = scanner.nextInt();
        scanner.nextLine();
        List<Prestiti> listaPrestitiSortAsc = prestitiService.ordiniXutenteCronologico(idu);
        UtentiRepository utentiRepository = new UtentiRepository();
        Utenti utenti = utentiRepository.findById(idu);
        System.out.println("ecco una lista di prestiti");
        for (Prestiti prestiti : listaPrestitiSortAsc) {
            Libri libri = libriRepository.findById(prestiti.getIDL());

            System.out.println("utente "+utenti.getNome() + " " + utenti.getCognome()+ "libro" + libri.getId() + " " + libri.getTitolo() + "/data inizio: " + prestiti.getDataInizio()+ "/ data fine" + prestiti.getDataFine());
        }
    }
    public void libriNonRientrati(){
        LibriRepository libriRepository = new LibriRepository();

        System.out.println("ecco una lista di libri non rientrati");
        List<Prestiti> libriNonRientrati = prestitiService.libriNonRientrati();
        for (Prestiti prestiti : libriNonRientrati){
            Libri libri = libriRepository.findById(prestiti.getIDL());

            System.out.println(prestiti.toString());
            System.out.println(libri.toString());


        }
    }
    public void ordiniXPeriodo(){
        LibriRepository libriRepository = new LibriRepository();

        System.out.println("ecco una lista prestiti per perido");
        read();
        System.out.println("inserisci la data del perido");
        String dataInizoInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInizio = LocalDate.parse(dataInizoInput,formatter);

        List<Prestiti> listaOrdiniXPeriodo = prestitiService.ordiniXPeriodo(dataInizio);
        for (Prestiti prestiti : listaOrdiniXPeriodo){
            Libri libri = libriRepository.findById(prestiti.getIDL());

            System.out.println(prestiti.toString());
            System.out.println(libri.toString());


        }
    }
}


