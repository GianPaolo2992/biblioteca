package controller;

import entity.Libri;
import service.LibriService;

import java.util.List;
import java.util.Scanner;

public class LibriController {
    Scanner scanner = new Scanner(System.in);
    LibriService libriService = new LibriService();

    public void create(){
        System.out.println("inserisci un id alfa nmerico");
        String idl = scanner.nextLine();
        System.out.println("inserisci il titolo del nuovo libro");
        String titolo = scanner.nextLine();
        System.out.println("inserisci il nome dell' autore ");
        String  autore = scanner.nextLine();
        Libri oLibro = new Libri(idl,titolo,autore);
        libriService.create(oLibro);

    }

    public void update(){
        read();
        System.out.println("inserisci un id alfa numerico da aggiornare");
        String idl = scanner.nextLine();
        System.out.println("inserisci il titolo del nuovo libro");
        String titolo = scanner.nextLine();
        System.out.println("inserisci il nome dell' autore ");
        String  autore = scanner.nextLine();
        Libri oLibro = new Libri(idl,titolo,autore);
        libriService.update(oLibro);

    }


    public void read(){
        System.out.println("ecco una lista di libri");
        List<Libri> listaLibri = libriService.read();
        for(Libri libro : listaLibri ){
            System.out.println(libro.toString());
        }

    }

    public void delete(){
        read();
        System.out.println("scegli un id tra i libri mostrati per eliminarlo");
        String idl = scanner.nextLine();
        libriService.delete(idl);
    }
}
