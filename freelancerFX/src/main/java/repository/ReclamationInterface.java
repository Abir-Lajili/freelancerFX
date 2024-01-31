package repository;

import models.Reclamation;

import java.util.List;

public interface ReclamationInterface {

    int createReclamation(Reclamation reclamation);

    List<Reclamation> getAllReclamations(int clientId);

    boolean updateReclamation(Reclamation reclamation);

    boolean deleteReclamation(int reclamationId);
}
