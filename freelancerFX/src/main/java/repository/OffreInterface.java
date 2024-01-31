package repository;

import models.Offre;

import java.sql.SQLException;
import java.util.List;

public interface OffreInterface {

    void createOffre(Offre offre);

    Offre readOffre(int id) throws SQLException;

    void updateOffre(Offre updatedOffre) throws SQLException;

    void deleteOffre(int id) throws SQLException;

    List<Offre> getAllOffres() throws SQLException;
}
