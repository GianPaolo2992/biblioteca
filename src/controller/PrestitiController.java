package controller;

import entity.Libri;

import entity.Prestiti;
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
        PrestitiService prestitiService= new PrestitiService();

        public void create(){
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

            System.out.println("inserisci la data inizio prestito");
            String dataFineInput = scanner.nextLine();
            DateTimeFormatter formatterFine = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataFine = LocalDate.parse(dataFineInput,formatterFine);

            prestitiService.create(idu,dataInizio,idl,dataFine);

        }

        public void read(){
            System.out.println("ecco una lista di prestiti");
            List<Prestiti> listaPrestiti = prestitiService.read();
            for(Prestiti prestiti : listaPrestiti ){
                System.out.println(prestiti.toString());
            }

        }

        public void update(){
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
            LocalDate dataInizio = LocalDate.parse(dataInizioInput,formatterInizio);

            libriController.read();
            System.out.println("inserisci l'id del libro ");
            String idl = scanner.nextLine().toUpperCase();

            System.out.println("inserisci la data inizio prestito");
            String dataFineInput = scanner.nextLine();
            DateTimeFormatter formatterFine = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataFine = LocalDate.parse(dataFineInput,formatterFine);

            prestitiService.update(idp,idu,dataInizio,idl,dataFine);

        }
   // hy237



        public void delete(){
            read();
            System.out.println("scegli un id tra i prestit mostrati per eliminarlo");
            int idp = scanner.nextInt();
            scanner.nextLine();

            prestitiService.delete(idp);
        }
    }


