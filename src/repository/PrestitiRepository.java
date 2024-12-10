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

    public List<Libri> readLibriXUtente(int id) {
        List<Libri> listaPrestitiXutente = new ArrayList<>();
        try {
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT * FROM prestiti p " +
                    "JOIN libri l ON p.id_libro = l.idl " +
                    "JOIN utenti u ON p.idu = u.idu " +
                    "WHERE u.idu = ? AND p.fine IS NULL";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            Utenti utenti = new Utenti();
            while (rs.next()) {
                Prestiti prestiti = new Prestiti();
                prestiti.setId(rs.getInt("idp"));
                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("Inizio").toLocalDate());
                prestiti.setIDL(rs.getString("id_libro").toUpperCase());
                Date dataFine = rs.getDate("fine");
                if (dataFine != null) {
                    prestiti.setDataFine(java.sql.Date.valueOf(String.valueOf(dataFine)).toLocalDate());
                } else {
                    prestiti.setDataFine(null);
                }

                utenti.setId(rs.getInt("idu"));
                utenti.setCognome(rs.getString("cognome"));
                utenti.setNome(rs.getString("nome"));
                utenti.setLibri_prestati(rs.getBoolean("libri_prestati"));

                Libri libri = new Libri();
                libri.setId(rs.getString("idl"));
                libri.setTitolo(rs.getString("titolo"));
                libri.setAutore(rs.getString("autore"));

                listaPrestitiXutente.add(libri);


            }
            if(!listaPrestitiXutente.isEmpty()){
                utenti.setLibri_prestati(true);
            }
            else{
                utenti.setLibri_prestati(false);
            }

            pstmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestitiXutente;
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
            System.exit(0);
        }
        return prestito;
    }
        //ORDINI PER UTENTE IN ORDINE CRONOLOGICO
    public List<Prestiti> ordiniXutenteCronologico(int id){
        List<Prestiti> listaPrestitiSortAsc = new ArrayList<>();

        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT u.nome,u.cognome,l.titolo,l.idl,p.* " +
                    "FROM prestiti p " +
                    "JOIN utenti u ON u.idu = p.idu " +
                    "JOIN libri l ON l.idl = p.id_libro " +
                    "WHERE u.idu = ? " +
                    "ORDER BY p.inizio ASC";
            PreparedStatement pstmt  = c.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Prestiti prestiti = new Prestiti();

                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("inizio").toLocalDate());
                prestiti.setIDL(rs.getString("id_libro"));
                if (rs.getDate("fine")==null){
                    prestiti.setDataFine(null);
                }else{
                    prestiti.setDataFine(rs.getDate("fine").toLocalDate());
                }
                Utenti utenti = new Utenti();
                utenti.setNome(rs.getString("nome"));
                utenti.setCognome(rs.getString("cognome"));

                Libri libri = new Libri();
                libri.setId(rs.getString("idl"));
                libri.setTitolo(rs.getString("titolo"));

                listaPrestitiSortAsc.add(prestiti);

            }
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaPrestitiSortAsc;
    }

    public List<Prestiti> libriNonRientrati(){
        List<Prestiti> libriNonRientrati = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT l.titolo,l.idl,l.autore,p.* " +
                    "FROM prestiti p " +
                    "JOIN libri l ON l.idl = p.id_libro " +
                    "WHERE p.fine IS NULL ";
            PreparedStatement pstmt  = c.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Prestiti prestiti = new Prestiti();

                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("inizio").toLocalDate());
                prestiti.setIDL(rs.getString("id_libro"));
                if (rs.getDate("fine")==null){
                    prestiti.setDataFine(null);
                }else{
                    prestiti.setDataFine(rs.getDate("fine").toLocalDate());
                }

                Libri libri = new Libri();
                libri.setId(rs.getString("idl"));
                libri.setTitolo(rs.getString("titolo"));
                libri.setAutore(rs.getString("autore"));


                libriNonRientrati.add(prestiti);

            }
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return  libriNonRientrati;


    }

    public List<Prestiti> ordiniXPeriodo(LocalDate datainizio){
        List<Prestiti> listaOrdiniXPeriodo = new ArrayList<>();

        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            String query = "SELECT l.titolo,l.idl,l.autore,p.* " +
                    "FROM prestiti p " +
                    "JOIN libri l ON l.idl = p.id_libro " +
                    "WHERE p.inizio > ? ";
            PreparedStatement pstmt  = c.prepareStatement(query);
            pstmt.setDate(1, java.sql.Date.valueOf(datainizio));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                entity.Prestiti prestiti = new entity.Prestiti();

                prestiti.setIDU(rs.getInt("idu"));
                prestiti.setDataInizio(rs.getDate("inizio").toLocalDate());
                prestiti.setIDL(rs.getString("id_libro"));
                if (rs.getDate("fine")==null){
                    prestiti.setDataFine(null);
                }else{
                    prestiti.setDataFine(rs.getDate("fine").toLocalDate());
                }

                Libri libri = new Libri();
                libri.setId(rs.getString("idl"));
                libri.setTitolo(rs.getString("titolo"));
                libri.setAutore(rs.getString("autore"));


                listaOrdiniXPeriodo.add(prestiti);

            }
            pstmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return listaOrdiniXPeriodo;
    }

}
