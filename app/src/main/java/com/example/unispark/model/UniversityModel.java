package com.example.unispark.model;


import java.util.List;

public class UniversityModel extends UserModel {
    //Attributes
    String name;
    String streetAddress;
    private List<ProfessorModel> professors;
    private List<StudentModel> students;
    private List<String> faculties;


    //Methods
    //Constructor

    public UniversityModel(String name, String email, int profilePicture, String streetAddress, List<ProfessorModel> professors, List<StudentModel> students, List<String> faculties) {
        super(email, profilePicture);
        this.name = name;
        this.streetAddress = streetAddress;
        this.professors = professors;
        this.students = students;
        this.faculties = faculties;
    }


    //Getter


    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public List<ProfessorModel> getProfessors() {
        return professors;
    }

    public List<StudentModel> getStudents() {
        return students;
    }

    public List<String> getFaculties() {
        return faculties;
    }


    //Setter


    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setProfessors(List<ProfessorModel> professors) {
        this.professors = professors;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }

    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }
}
