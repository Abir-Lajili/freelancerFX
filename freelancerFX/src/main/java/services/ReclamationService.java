package services;

import models.Reclamation;
import repository.ReclamationInterface;
import utiles.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService implements ReclamationInterface {
    private Connection cnx;
    public ReclamationService() {
        cnx = DbConnection.getInstance().getCnx();
    }

    @Override
    public int createReclamation(Reclamation reclamation) {
        String req = "INSERT INTO reclamation (type, description, statut, idUser, dateCreation) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, reclamation.getType());
            st.setString(2, reclamation.getDescription());
            st.setString(3, reclamation.getStatut());
            st.setInt(4, reclamation.getIdUser());
            st.setDate(5, new java.sql.Date(reclamation.getDateCreation().getTime()));

            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reclamation> getAllReclamations(int clientId) {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM reclamation WHERE idUser = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, clientId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idReclamation");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String statut = rs.getString("statut");
                Date dateCreation = rs.getDate("dateCreation");

                Reclamation reclamation = new Reclamation(id, type, description, statut, clientId, dateCreation);
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reclamations;
    }

    @Override
    public boolean updateReclamation(Reclamation reclamation) {
        String req = "UPDATE reclamation SET type=?, description=?, statut=? WHERE idReclamation=?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, reclamation.getType());
            st.setString(2, reclamation.getDescription());
            st.setString(3, reclamation.getStatut());
            st.setInt(4, reclamation.getIdReclamation());

           st.executeUpdate();
           return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteReclamation(int reclamationId) {
        String req = "DELETE FROM reclamation WHERE idReclamation=?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, reclamationId);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



