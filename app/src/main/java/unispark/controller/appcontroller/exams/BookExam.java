package unispark.controller.appcontroller.exams;

import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.engeneeringclasses.exceptions.ExamAlreadyVerbalized;
import unispark.engeneeringclasses.exceptions.ExamException;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.facade.ExamsFacade;
import unispark.engeneeringclasses.factory.FactoryExams;
import unispark.model.CourseModel;
import unispark.model.exams.BookExamModel;
import unispark.model.exams.ExamModel;

import java.sql.SQLException;
import java.util.List;

public class BookExam {

    public void bookExam(BeanLoggedStudent student, BeanBookExam exam) throws ExamAlreadyVerbalized, GenericException
    {

        List<BookExamModel> exams = student.getBookedExams();
        BookExamModel bookExam = new BookExamModel(exam.getId(), exam.getName(), exam.getYear(), exam.getDate(), exam.getCfu(), exam.getClassroom(), exam.getBuilding());
        try {
            ExamsDAO.bookExam(bookExam, student.getId());
            exams.add(bookExam);
            student.setBookedExams(exams);
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyVerbalized(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }



    public List<BeanExam> generateBookingExams(BeanLoggedStudent student){
        List<BookExamModel> bookExams = null;

        try{
            List<BookExamModel> bookedExams = student.getBookedExams();
            List<CourseModel> studentCourses = student.getCourses();
            bookExams = ExamsFacade.getInstance().getStudentExams(studentCourses, bookedExams);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return FactoryExams.getInstance().createBeanBookExams(bookExams);

    }

}
