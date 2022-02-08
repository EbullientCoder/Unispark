package unispark.engeneeringclasses.bean.exams;

import java.io.Serializable;

public abstract class BeanExam implements Serializable {

    private int id;
    private String name;
    private String year;
    private String date;
    private String cfu;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getCfu() {
        return cfu;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }
}
