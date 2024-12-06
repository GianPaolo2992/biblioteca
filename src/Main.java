import controller.LibriController;
import controller.UtentiController;
import service.LibriService;
import service.UtentiService;

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
                System.out.println("***Menu***");
                System.out.println("1. crea un nuovo librio");
                System.out.println("2. lista libri");
                System.out.println("3. aggiorna libri ");
                System.out.println("4.  elimina libri");
                System.out.println("5.  stampa libri di gare per id utente");
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
            System.out.println("***Menu***");
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
                  //  disciplineController.delete();
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
//        else if(entityChoice == 3){
//            System.out.println("***Menu***");
//            System.out.println("1. crea un nuova sport");
//            System.out.println("2. lista Sport");
//            System.out.println("3. aggiorna sport ");
//            System.out.println("4. elimina sport");
//
//            System.out.println("9. Exit");
//            System.out.print("Inserisci la tua scelta: ");
//            choice = scanner.nextInt();
//            SportsController sportsController = new SportsController();
//            switch(choice){
//                case 1:
//                    sportsController.create();
//                    break;
//                case 2:
//                    sportsController.read();
//                    break;
//                case 3:
//                    sportsController.update();
//                    break;
//                case 4:
//                    sportsController.delete();
//                    break;//
//                case 9:
//                    System.out.println("exiting");
//                    break;
//                default:
//                    System.out.println("Scelta errata. Scegliere un numero da 1 a 4 o 9");
//            }
//        }
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