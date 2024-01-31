package services;

import models.Reponse;
import repository.ReponseInterface;
import utiles.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseService implements ReponseInterface {
    private Connection cnx;

    public ReponseService() {
        cnx = DbConnection.getInstance().getCnx();
    }

    @Override
    public int createReponse(Reponse reponse) {
        String req = "INSERT INTO reponse (contenu, idReclamation, dateCreation) VALUES (?, ?, ?)";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, reponse.getContenu());
            st.setInt(2, reponse.getIdReclamation());
            st.setDate(3, new java.sql.Date(reponse.getDateCreation().getTime()));

            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateReponse(Reponse reponse) {
        String req = "UPDATE reponse SET contenu = ?, dateCreation = ? WHERE idReponse = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, reponse.getContenu());
            st.setDate(2, new java.sql.Date(reponse.getDateCreation().getTime()));
            st.setInt(3, reponse.getId());

            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteReponse(int reponseId) {
        String req = "DELETE FROM reponse WHERE idReponse = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, reponseId);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reponse> getAllReponses() {
        List<Reponse> reponses = new ArrayList<>();
        String req = "SELECT * FROM reponse";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idReponse");
                String contenu = rs.getString("contenu");
                int idReclamation = rs.getInt("idReclamation");
                Date dateCreation = rs.getDate("dateCreation");

                Reponse reponse = new Reponse(id, contenu, idReclamation, dateCreation);
                reponses.add(reponse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reponses;
    }
}
