package services;

import models.Offre;
import repository.OffreInterface;
import utiles.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffreService implements OffreInterface {

    private Connection cnx;

    public OffreService() {
        cnx = DbConnection.getInstance().getCnx();
    }

    @Override
    public void createOffre(Offre offre) {

    }

    @Override
    public Offre readOffre(int id) throws SQLException {
       Offre offre = null;
        String req = "SELECT * FROM offre WHERE idOffre = ?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                        offre = new Offre(
                            rs.getInt("idOffre"),
                            rs.getString("titreOffre"),
                            rs.getString("descriptionOffre"),
                            rs.getDouble("montantOffre"),
                            rs.getString("statutOffre"),
                            rs.getDate("deadlineOffre")
                    );
                }
            }
        }
        return offre;
    }

    @Override
    public void updateOffre(Offre offre) throws SQLException {
        String req = "UPDATE offre SET titreOffre=?, descriptionOffre=?, montantOffre=?, statutOffre=?, deadlineOffre=? WHERE idOffre=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, offre.getTitre());
            st.setString(2, offre.getDescription());
            st.setDouble(3, offre.getMontant());
            st.setString(4, offre.getStatut());
            st.setDate(5, new java.sql.Date(offre.getDeadline().getTime()));
            st.setInt(6, offre.getId());
            st.executeUpdate();
        }
    }

    @Override
    public void deleteOffre(int id) throws SQLException {
        String req = "DELETE FROM offre WHERE idOffre=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    @Override
    public List<Offre> getAllOffres() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String req = "SELECT * FROM offre";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Offre offre = new Offre();
                    offre.setId(rs.getInt("idOffre"));
                    offre.setTitre(rs.getString("titreOffre"));
                    offre.setDescription(rs.getString("descriptionOffre"));
                    offre.setMontant(rs.getDouble("montantOffre"));
                    offre.setStatut(rs.getString("statutOffre"));
                    offre.setDeadline(rs.getDate("deadlineOffre"));
                    offres.add(offre);
                }
            }
        }
        return offres;
    }

}






