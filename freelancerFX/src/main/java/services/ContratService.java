package services;

import models.Contrat;
import repository.ContratInterface;
import utiles.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratService implements ContratInterface {


    private Connection cnx;

    public ContratService() {
        cnx = DbConnection.getInstance().getCnx();
    }
    @Override
    public void createContrat(Contrat contrat) throws SQLException {
        String req = "INSERT INTO contrat (titreContrat, statutContrat, descriptionContrat, idUsers, idOffre) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, contrat.getTitreContrat());
            st.setString(2, contrat.getStatutContrat());
            st.setString(3, contrat.getDescriptionContrat());
            st.setInt(4, contrat.getIdUsers());
            st.setInt(5, contrat.getIdOffre());
            st.executeUpdate();
        }
    }

    @Override
    public Contrat readContrat(int id) {
            Contrat contrat = null;
            String req = "SELECT * FROM contrat WHERE idContrat = ?";
            try (PreparedStatement st = cnx.prepareStatement(req)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        contrat = new Contrat(
                                rs.getInt("idContrat"),
                                rs.getString("titreContrat"),
                                rs.getString("statutContrat"),
                                rs.getString("descriptionContrat"),
                                rs.getInt("idUsers"),
                                rs.getInt("idOffre")
                        );
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return contrat;
        }


    @Override
    public void updateContrat(Contrat contrat) {
        String req = "UPDATE contrat SET titreContrat=?, statutContrat=?, descriptionContrat=?, idUsers=?, idOffre=? WHERE idContrat=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, contrat.getTitreContrat());
            st.setString(2, contrat.getStatutContrat());
            st.setString(3, contrat.getDescriptionContrat());
            st.setInt(4, contrat.getIdUsers());
            st.setInt(5, contrat.getIdOffre());
            st.setInt(6, contrat.getIdContrat());
             st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteContrat(int id) throws SQLException {
        String req = "DELETE FROM contrat WHERE idContrat=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    @Override
    public List<Contrat> getAllContrats() throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        String req = "SELECT * FROM contrat";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Contrat contrat = new Contrat(
                            rs.getInt("idContrat"),
                            rs.getString("titreContrat"),
                            rs.getString("statutContrat"),
                            rs.getString("descriptionContrat"),
                            rs.getInt("idUsers"),
                            rs.getInt("idOffre")
                    );
                    contrats.add(contrat);
                }
            }
        }
        return contrats;
    }
}
