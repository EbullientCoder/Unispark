package unispark.engeneeringclasses.model.communications;

import java.io.Serializable;

public class UniversityCommunicationModel implements Serializable {

    //Attributes
    private int background;
    private String title;
    private String date;
    private String communication;
    private String faculty;


    //Methods
    //Constructor
    public UniversityCommunicationModel(int background, String title, String date, String communication, String faculty) {
        this.background = background;
        this.title = title;
        this.date = date;
        this.communication = communication;
        this.faculty = faculty;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

