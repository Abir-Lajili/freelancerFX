package models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Publication {
    private int publicationId;
    private String title;
    private String content;
    private Date date;

    private String status;
    private int client;
    private int freelancer;
    private List<Commentaire> commentaires;


    public Publication(String title, String content, Date date, String status, int client, int freelancer) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.client = client;
        this.freelancer = freelancer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Publication(int publicationId, String title, String content, Date date, String status, int client, int freelancer) {
        this.publicationId = publicationId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.client = client;
        this.freelancer = freelancer;
    }

    public Publication(int publicationId, String title, String content, Date date, int client, int freelancer) {
        this.publicationId = publicationId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.client = client;
        this.freelancer = freelancer;
        this.commentaires = new ArrayList<>();
    }

    // Getters and Setters

    public int getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public int getclient() {
        return client;
    }

    public void setclient(int client) {
        this.client = client;
    }

    public int getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(int freelancer) {
        this.freelancer = freelancer;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void addCommentaire(Commentaire commentaire) {
        this.commentaires.add(commentaire);
    }


}
