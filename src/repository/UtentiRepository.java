package repository;

import config.DbConnection;
import entity.Libri;
import entity.Utenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtentiRepository {
    public void create(Utenti oUtente){
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO utenti (cognome,nome,libri_prestati)VALUES(?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,oUtente.getNome());
            pstmt.setString(2,oUtente.getCognome());
            pstmt.setBoolean(3,oUtente.getLibri_prestati());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public List<Utenti> read(){
        List<Utenti> listaUtenti = new ArrayList();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM utenti ORDER BY idu ASC";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Utenti utenti = new Utenti();
                utenti.setId(rs.getInt("idu"));
                utenti.setCognome(rs.getString("cognome"));
                utenti.setNome(rs.getString("nome"));

                listaUtenti.add(utenti);
            }
            pstmt.close();
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaUtenti;
    }

    public void update(Utenti oUtente,int id){
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connnessione Riuscita");
            String query = "UPDATE utenti " +
                    "SET nome = ?,cognome = ? " +
                    "WHERE idu = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,oUtente.getNome());
            pstmt.setString(2,oUtente.getCognome());
            pstmt.setInt(3,id);

            pstmt.executeUpdate();
            pstmt.close();

        }catch (ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
    public void delete(Utenti oUtente){
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connnessione Riuscita");
            String query = "DELETE FROM utenti " +
                    "WHERE idu = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1,oUtente.getId());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (ClassNotFoundException|SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
    public Utenti findById(int idu) {
        Utenti utenti = null;
        String query = "SELECT * FROM utenti WHERE idu = ?";
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setInt(1, idu); // Assicurati che l'ID sia in maiuscolo per la consistenza
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Assicurati che ci siano risultati
                    utenti = new Utenti();
                    utenti.setId(rs.getInt("idu"));
                    utenti.setNome(rs.getString("nome"));
                    utenti.setCognome(rs.getString("cognome"));

                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return utenti;
    }
}
