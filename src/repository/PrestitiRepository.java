package repository;

import config.DbConnection;
import entity.Prestiti;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestitiRepository {

    public boolean libroIdIsPresentDB(String idl) {
        boolean isPresent = false;
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("connessione riuscita");
            String query = "SELECT  COUNT(idl) " +
                    "FROM libri l " +
                    "WHERE l.idl = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, idl);
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
    public void create(Prestiti oPrestiti) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO prestiti (idu, inizio, id_libro, fine) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, oPrestiti.getIdU());
            pstmt.setDate(2, java.sql.Date.valueOf(oPrestiti.getDataInizio()));
            pstmt.setString(3, oPrestiti.getIDL().toUpperCase());
            pstmt.setDate(4, null);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /*public void create(Prestiti oPrestiti) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "INSERT INTO prestiti (idu,inizio,id_libro,fine)VALUES(?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, oPrestiti.getIdU());
            pstmt.setDate(2, java.sql.Date.valueOf(oPrestiti.getDataInizio()));
            pstmt.setString(3, oPrestiti.getIDL().toUpperCase());
            pstmt.setDate(4, null);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }*/

    public List<Prestiti> read() {
        List<Prestiti> listaPrestiti = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM prestiti ORDER BY idp ASC";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Connessione Riuscita while");
                Prestiti prestiti = new Prestiti();
                prestiti.setId(rs.getInt("idp"));
                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("Inizio").toLocalDate());
                // Gestione dei valori null per le date


                prestiti.setIDL(rs.getString("id_libro").toUpperCase());

                Date dataFine = rs.getDate("fine");
                if (dataFine != null) {
                    prestiti.setDataFine(java.sql.Date.valueOf(String.valueOf(dataFine)).toLocalDate());
                } else {
                    prestiti.setDataFine(null);
                }

                listaPrestiti.add(prestiti);
            }
            pstmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestiti;
    }

    public void update(Prestiti oPrestiti) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connnessione Riuscita");
            String query = "UPDATE prestiti " +
                    "SET idu = ?, inizio = ?, id_libro = ?, fine = ? " +
                    "WHERE idp = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, oPrestiti.getIdU());
            pstmt.setDate(2, java.sql.Date.valueOf(oPrestiti.getDataInizio()));
            pstmt.setString(3, oPrestiti.getIDL());
            // pstmt.setDate(4, java.sql.Date.valueOf(oPrestiti.getDataFine()));
            pstmt.setInt(5, oPrestiti.getId());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void updateDataFine(Prestiti oPrestiti) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connnessione Riuscita");
            String query = "UPDATE prestiti " +
                    "SET  fine = ? " +
                    "WHERE idp = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setDate(1, java.sql.Date.valueOf(oPrestiti.getDataFine()));
            pstmt.setInt(2, oPrestiti.getId());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void delete(Prestiti oPrestiti) {
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("connessione Riuscita ciao");
            String query = "DELETE FROM prestiti WHERE idp = ? ";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, oPrestiti.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public Prestiti findById(int idp) {
        Prestiti prestito = null;


        try {
            Connection c = DbConnection.openConnection();
            String query = "SELECT * FROM prestiti WHERE idp = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, idp);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                prestito = new Prestiti();
                prestito.setId(rs.getInt("idp"));
                prestito.setIDU(rs.getInt("idu"));
                prestito.setDataInizio(rs.getDate("inizio").toLocalDate());
                prestito.setIDL(rs.getString("id_libro"));
                prestito.setDataFine(rs.getDate("fine").toLocalDate());
            }
            pstmt.close();

        } catch (ClassNotFoundException | SQLException | RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return prestito;
    }

    public Prestiti findByLibroId(String idL) {
        Prestiti prestito = null;
        String query = "SELECT * FROM prestiti WHERE id_libro = ? AND fine IS NULL"; // Trova solo i prestiti attivi
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, idL.toUpperCase());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prestito = new Prestiti();
                    prestito.setId(rs.getInt("idp"));
                    prestito.setIDU(rs.getInt("idu"));
                    prestito.setDataInizio(rs.getDate("inizio").toLocalDate());
                    prestito.setIDL(rs.getString("id_libro"));
                    prestito.setDataFine(rs.getDate("fine") != null ? rs.getDate("fine").toLocalDate() : null);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return prestito;
    }

    public Prestiti findByUtenteId(String idu) {
        Prestiti prestito = null;
        String query = "SELECT * FROM prestiti WHERE idu = ? AND fine IS NULL"; // Trova solo i prestiti attivi
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, idu);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prestito = new Prestiti();
                    prestito.setId(rs.getInt("idp"));
                    prestito.setIDU(rs.getInt("idu"));
                    prestito.setDataInizio(rs.getDate("inizio").toLocalDate());
                    prestito.setIDL(rs.getString("id_libro"));
                    prestito.setDataFine(rs.getDate("fine") != null ? rs.getDate("fine").toLocalDate() : null);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return prestito;
    }


}
