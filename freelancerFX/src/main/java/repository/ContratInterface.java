package repository;

import models.Contrat;

import java.sql.SQLException;
import java.util.List;

public interface ContratInterface {

    void createContrat(Contrat contrat) throws SQLException;

    Contrat readContrat(int id);

    void updateContrat(Contrat updatedContrat);

    void deleteContrat(int id) throws SQLException;

    List<Contrat> getAllContrats() throws SQLException;
}
