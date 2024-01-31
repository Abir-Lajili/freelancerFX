package repository;

import models.Reponse;

import java.util.List;

public interface ReponseInterface {

    int createReponse(Reponse reponse);

    List<Reponse> getAllReponses();

    boolean updateReponse(Reponse reponse);

    boolean deleteReponse(int reponseId);
}
