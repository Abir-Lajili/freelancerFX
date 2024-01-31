package services;

import models.Publication;
import repository.PublicationInterface;
import utiles.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationService implements PublicationInterface {


    private Connection cnx;

    public PublicationService() {
        cnx = DbConnection.getInstance().getCnx();
    }
    @Override
    public int createPublication(Publication publication)   {
        String req = "INSERT INTO publication (title, content, date,status, clientId, freelancerId) VALUES (?, ?, ?,?, ?, ?)";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, publication.getTitle());
            st.setString(2, publication.getContent());
            st.setDate(3, publication.getDate());
            st.setString(4, publication.getStatus());
            st.setInt(5, publication.getclient());
            st.setInt(6, publication.getFreelancer());

            return st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

        @Override
        public List<Publication> getAllPublications(int clientId) {
    List<Publication> publications = new ArrayList<>();
    String req = "SELECT * FROM publication WHERE clientId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
        st.setInt(1, clientId);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int publicationId = rs.getInt("publicationId");
            String title = rs.getString("title");
            String content = rs.getString("content");
            java.sql.Date date = rs.getDate("date");
            String status = rs.getString("status");
            int freelancerId = rs.getInt("freelancerId");

            Publication publication = new Publication(publicationId, title, content, date, status, clientId, freelancerId);
            publications.add(publication);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        return publications;
}

    @Override
    public boolean updatePublication(Publication publication) {
        String req = "UPDATE publication SET title = ?, content = ?, date = ?, status = ?, clientId = ?, freelancerId = ? WHERE publicationId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, publication.getTitle());
            st.setString(2, publication.getContent());
            st.setDate(3, publication.getDate());
            st.setString(4, publication.getStatus());
            st.setInt(5, publication.getclient());
            st.setInt(6, publication.getFreelancer());
            st.setInt(7, publication.getPublicationId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletePublication(int publicationId) {
        String req = "DELETE FROM publication WHERE publicationId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, publicationId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }






