package models;

import java.util.Date;

public class Reponse {
    private int idReponse;
    private String contenu;
    private int idReclamation;
    private Date dateCreation;

    // Constructeurs

    public Reponse() {
        // Constructeur par défaut
    }

    public Reponse(int id, String contenu, int idReclamation, Date dateCreation) {
        this.idReponse = id;
        this.contenu = contenu;
        this.idReclamation = idReclamation;
        this.dateCreation = dateCreation;
    }

    // Getters et setters

    public int getId() {
        return idReponse;
    }

    public void setId(int id) {
        this.idReponse = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
