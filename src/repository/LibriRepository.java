package repository;

import config.DbConnection;
import entity.Libri;
import entity.Prestiti;
import entity.Utenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibriRepository {

    public boolean libroIdIsPresentDB(String idl) {
        boolean isPresent = false;
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("connessione riuscita");
            String query = "SELECT  COUNT(idl) " +
                    "FROM libri l " +
                    "WHERE l.idl = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, idl.toUpperCase());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                isPresent = rs.getInt(1) > 0;
            }
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);

        }
        return isPresent;

    }

    public void create(Libri oLibro) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO libri (idl,titolo,autore)VALUES(?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oLibro.getId().toUpperCase());
            pstmt.setString(2, oLibro.getTitolo());
            pstmt.setString(3, oLibro.getAutore());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public List<Libri> read() {
        List<Libri> listaLibri = new ArrayList();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM libri ORDER BY idl ASC";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Libri libro = new Libri();
                libro.setId(rs.getString("idl"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                listaLibri.add(libro);
            }
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaLibri;
    }

    public void update(Libri oLobro) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connnessione Riuscita");
            String query = "UPDATE libri " +
                    "SET titolo = ?,autore = ? " +
                    "WHERE idl = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, oLobro.getTitolo());
            pstmt.setString(2, oLobro.getAutore());
            pstmt.setString(3, oLobro.getId().toUpperCase());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void delete(Libri libro) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("connessione Riuscita");
            String query = "DELETE FROM libri WHERE idl = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, libro.getId().toUpperCase());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /*public Libri findById(String idl){
        PrestitiRepository prestitiRepository = new PrestitiRepository();
        Libri libro = new Libri();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");


            String query = "SELECT * FROM libri WHERE idl = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,idl.toUpperCase());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                libro.setId(rs.getString("idl"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));

            }
            pstmt.close();
        }catch (ClassNotFoundException | SQLException | RuntimeException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return libro;
    }*/
    public Libri findById(String idl) {
        Libri libro = null;
        String query = "SELECT * FROM libri WHERE idl = ?";
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, idl.toUpperCase()); // Assicurati che l'ID sia in maiuscolo per la consistenza
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Assicurati che ci siano risultati
                    libro = new Libri();
                    libro.setId(rs.getString("idl"));
                    libro.setTitolo(rs.getString("titolo"));
                    libro.setAutore(rs.getString("autore"));
                    // Recupera il prestito associato, se presente
                    PrestitiRepository prestitiRepository = new PrestitiRepository();
                    Prestiti prestito = prestitiRepository.findByLibroId(idl);
                    libro.setPrestito(prestito);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return libro;
    }

    // --4) LIBRI IL CUI PRESTITO HA SUPERATO I 15GG
    public List<Libri> bookBeyondPeriod() {
        List<Libri> ListBookBeyondPeriod = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connesssione Riuscita");
            String query = "SELECT l.idl,l.titolo,l.autore,p.*,u.nome,u.cognome " +
                    "FROM libri l " +
                    "JOIN prestiti p ON l.idl = p.id_libro " +
                    "JOIN utenti u ON u.idu = p.idu " +
                    "WHERE (CURRENT_DATE - p.inizio) > 15 AND p.fine IS NULL ";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Libri libri = new Libri();
                libri.setId(rs.getString("idl"));
                libri.setTitolo(rs.getString("titolo"));
                libri.setAutore(rs.getString("autore"));

                Prestiti prestiti = new Prestiti();
                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("inizio").toLocalDate());

                Utenti utenti = new Utenti();
                utenti.setNome(rs.getString("nome"));
                utenti.setCognome(rs.getString("cognome"));
                libri.setPrestito(prestiti);
                ListBookBeyondPeriod.add(libri);
            }
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {

            System.err.println(e.getMessage());
            System.exit(0);

        }
        return ListBookBeyondPeriod;

    }


//--6)CLASSIFICA LIBRI PRESTATI PER NUMERO DI VOLTE

    public List<Libri> gratersLoans() {
        List<Libri>  listGratersLoans = new ArrayList<>();
        String query = "SELECT l.idl,l.titolo,l.autore,COUNT(p.id_libro) AS maggiorPrestiti " +
                "FROM libri l " +
                "JOIN prestiti p ON p.id_libro = l.idl " +
                "GROUP BY l.idl,l.titolo,l.autore " +
                "ORDER BY maggiorPrestiti DESC ";
        try (Connection c = DbConnection.openConnection();
             PreparedStatement preparedStatement = c.prepareStatement(query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Libri libri = new Libri();
                    libri.setId(rs.getString("idl"));
                    libri.setTitolo(rs.getString("titolo"));
                    libri.setAutore(rs.getString("autore"));

                    int count = rs.getInt("maggiorPrestiti");
                    System.out.println("la classifica dei libri piu prestati è : \n" + libri.toString() + "\n con un tolale di libri letti : " + count);
                    listGratersLoans.add(libri);
                }


            }

            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }

        return  listGratersLoans;
    }
}