package com.example.unispark.model;


import java.util.List;

public class UniversityModel extends UserModel {
    //Attributes
    private String name;
    private String address;
    private List<ProfessorModel> professors;
    private List<StudentModel> students;


    //Methods
    //Constructor
    public UniversityModel(String email, String password, String name, String address, List<ProfessorModel> professors, List<StudentModel> students) {
        super(email, password);
        this.name = name;
        this.address = address;
        this.professors = professors;
        this.students = students;
    }


    //Getter
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<ProfessorModel> getProfessors() {
        return professors;
    }

    public List<StudentModel> getStudents() {
        return students;
    }


    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfessors(List<ProfessorModel> professors) {
        this.professors = professors;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }
}
