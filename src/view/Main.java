package view;

import controller.LibriController;
import controller.PrestitiController;
import controller.UtentiController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int entityChoice= 0;
        System.out.println("scegli tra le seguenti opzioni");

        System.out.println("1. libri");
        System.out.println("2. utenti");
        System.out.println("3. prestiti");




        entityChoice = scanner.nextInt();
        scanner.nextLine();
        do {
            if(entityChoice == 1){
                System.out.println("***Menu Libri***");
                System.out.println("1. crea un nuovo librio");
                System.out.println("2. lista libri");
                System.out.println("3. aggiorna libri ");
                System.out.println("4.  elimina libri");

                System.out.println("9. Exit");
                System.out.print("Inserisci la tua scelta: ");
                choice = scanner.nextInt();
                LibriController libriController = new LibriController();
                switch(choice){
                    case 1:
                        libriController.create();
                        break;
                    case 2:
                          libriController.read();
                          break;
                    case 3:
                         libriController.update();
                         break;
                    case 4:
                           libriController.delete();
                          break;
//                    case 5:
//                        athleteController.read();
//                        gameController.printGamesForAthlete();
//                        break;
                    case 9:
                        System.out.println("exiting");
                        break;
                    default:
                        System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");
                }
            }
        else if(entityChoice == 2){
            System.out.println("***Menu Utenti***");
            System.out.println("1. crea un nuovo Utente");
            System.out.println("2. lista Utenti");
            System.out.println("3. aggiorna Utente ");
            System.out.println("4. elimina Utente");
            //System.out.println("5.  stampa lista di gare per id disciplina");
            System.out.println("9. Exit");
            System.out.print("Inserisci la tua scelta: ");
            choice = scanner.nextInt();
                UtentiController utentiController = new UtentiController();
            switch(choice){
                case 1:
                    utentiController.create();
                    break;
                case 2:
                    utentiController.read();
                    break;
                case 3:
                    utentiController.update();
                    break;
                case 4:
                    utentiController.delete();
                    break;
                //case 5:
                   // disciplineController.read();
                   // gameController.printGamesForDiscipline();
                   // break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");
            }
        }
        else if(entityChoice == 3){
            System.out.println("***Menu Prestiti***");
            System.out.println("1. associa utente a libro");
            System.out.println("2. lista prestiti");
            System.out.println("3. aggiorna prestito ");
            System.out.println("4.  elimina prestito");
            System.out.println("5.  aggiorna data fine prestito");

            System.out.println("9. Exit");
            System.out.print("Inserisci la tua scelta: ");
            choice = scanner.nextInt();
                PrestitiController prestitiController = new PrestitiController();
            switch(choice){
                case 1:
                    prestitiController.create();
                    break;
                case 2:
                    prestitiController.read();
                    break;
                case 3:
                    prestitiController.update();
                    break;
                case 4:
                    prestitiController.delete();
                    break;
                case 5:
                    prestitiController.updateDataFine();
                    break;
                case 9:
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");
            }
        }
//        else if(entityChoice == 4){
//            System.out.println("***Menu***");
//            System.out.println("1. associa atleta a gara");
//            System.out.println("2. lista delle gare");
//            System.out.println("3. aggiorna partecipazioni gara ");
//            System.out.println("4. elimina gara");
//            System.out.println("9. Exit");
//            System.out.print("Inserisci la tua scelta: ");
//            choice = scanner.nextInt();
//            GameController gameController = new GameController();
//            switch(choice){
//                case 1:
//                    gameController.create_asscociaGaraAtleta();
//                    break;
//                case 2:
//                    gameController.readGame();
//                    break;
//                case 3:
//                    gameController.updateGame();
//                    break;
//                case 4:
//                    gameController.deleteGame();
//                case 9:
//                    System.out.println("exiting");
//                    break;
//                default:
//                    System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");
//            }
//        }
        }while(choice != 9);

        scanner.close();

    }


}