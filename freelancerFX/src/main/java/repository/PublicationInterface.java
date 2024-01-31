package repository;
import models.Publication;

import java.util.List;

public interface PublicationInterface {

    int createPublication(Publication publication);

    List<Publication> getAllPublications(int clientId);

    boolean updatePublication(Publication publication);

    boolean deletePublication(int publicationId);
}
