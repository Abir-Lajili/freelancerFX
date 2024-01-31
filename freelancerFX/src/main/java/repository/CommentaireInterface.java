package repository;

import models.Commentaire;


import java.util.List;

public interface CommentaireInterface {

    void createCommentaire(Commentaire publication);

    List<Commentaire> getAllCommentaires(int clientId);

    boolean updateCommentaire(Commentaire publication);

    boolean deleteCommentaire(int publicationId);
}
