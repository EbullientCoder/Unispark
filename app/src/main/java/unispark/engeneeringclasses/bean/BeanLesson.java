package unispark.engeneeringclasses.bean;

public class BeanLesson {
    //Attributes
    private String lessonName;
    private String day;
    private String hour;


    //Getter
    public String getLessonName() {
        return lessonName;
    }
    public String getDay(){ return day;}
    public String getHour() {
        return hour;
    }


    //Setter
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
}
