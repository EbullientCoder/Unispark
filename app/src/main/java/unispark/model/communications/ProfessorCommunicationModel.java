package unispark.model.communications;

import java.io.Serializable;

public class ProfessorCommunicationModel implements Serializable {

    //Attributes
    private int profilePhoto;
    private String shortCourseName;
    private String fullName;
    private String professorName;
    private String date;
    private String type;
    private String communication;


    //Methods
    //Constructor
    public ProfessorCommunicationModel(int profilePhoto, String shortCourseName, String fullName, String professorName, String date, String type, String communication) {
        this.profilePhoto = profilePhoto;
        this.shortCourseName = shortCourseName;
        this.fullName = fullName;
        this.professorName = professorName;
        this.date = date;
        this.type = type;
        this.communication = communication;
    }


    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getShortCourseName() {
        return shortCourseName;
    }

    public void setShortCourseName(String shortCourseName) {
        this.shortCourseName = shortCourseName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }
}

