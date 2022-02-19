package unispark.engeneeringclasses.exceptions;

import unispark.engeneeringclasses.bean.BeanErrorLessonAlreadyExists;


public class LessonAlreadyExists extends Exception{

    private static final long serialVersionUID = 1L;
    private final String lessonName;
    private final String day;
    private final String hour;

    public LessonAlreadyExists(String lessonName, String day, String hour) {
        this.lessonName = lessonName;
        this.day = day;
        this.hour = hour;
    }

    public BeanErrorLessonAlreadyExists getMess() {
        BeanErrorLessonAlreadyExists error;
        error = new BeanErrorLessonAlreadyExists();
        error.setLesson(lessonName);
        error.setDay(day);
        error.setHour(hour);
        return error;
    }
}
