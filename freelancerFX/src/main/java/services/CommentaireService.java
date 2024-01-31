package services;

import models.Commentaire;
import repository.CommentaireInterface;
import repository.ContratInterface;
import utiles.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentaireService implements CommentaireInterface {
    private Connection cnx;

    public CommentaireService() {
        cnx = DbConnection.getInstance().getCnx();
    }

    @Override
    public void createCommentaire(Commentaire commentaire) {
        String req = "INSERT INTO commentaire (content, date, userId) VALUES (?, ?, ?)";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, commentaire.getContent());
            st.setDate(2, new java.sql.Date(commentaire.getDate().getTime()));
            st.setInt(3, commentaire.getUserId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Commentaire> getAllCommentaires(int clientId) {
        List<Commentaire> commentaires = new ArrayList<>();
        String req = "SELECT * FROM commentaire WHERE userId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, clientId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int commentaireId = rs.getInt("commentaireId");
                String content = rs.getString("content");
                java.sql.Date date = rs.getDate("date");

                Commentaire commentaire = new Commentaire(commentaireId, content, date, clientId);
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commentaires;
    }

    @Override
    public boolean updateCommentaire(Commentaire commentaire) {
        String req = "UPDATE commentaire SET content = ?, date = ? WHERE commentaireId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1, commentaire.getContent());
            st.setDate(2, new java.sql.Date(commentaire.getDate().getTime()));
            st.setInt(3, commentaire.getCommentaireId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCommentaire(int commentaireId) {
        String req = "DELETE FROM commentaire WHERE commentaireId = ?";

        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, commentaireId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
