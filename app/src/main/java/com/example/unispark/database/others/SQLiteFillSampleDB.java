package com.example.unispark.database.others;



import static com.example.unispark.database.others.Password.getHash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;


import com.example.unispark.R;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.example.unispark.model.exams.ExamGradeModel;
import com.example.unispark.model.exams.BookingExamModel;

public class SQLiteFillSampleDB extends SQLiteOpenHelper {


    //Users table (students, professors and university)
    public static final String STUDENTS_TABLE = "students";
    public static final String PROFESSORS_TABLE = "professors";
    public static final String UNI_TABLE = "university";
    public static final String ID = "id";
    public static final String STUDENT_ID = "studentID";
    public static final String PROF_ID = "professorID";
    public static final String UNIVERSITY_ID = "universityID";
    public static final String IMAGE = "image";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FACULTY = "faculty";
    public static final String ACADEMIC_YEAR = "academicyear";
    public static final String WEBSITE = "website";


    //Homework table
    public static final String HOMEWORK_TABLE = "homework";
    public static final String ID_HW = "homeworkID";
    public static final String SHORTNAME = "shortname";
    public static final String COURSE_NAME = "coursename";
    public static final String TITLE = "title";
    public static final String EXPIRATION = "expiration";
    public static final String INSTRUCTIONS = "instructions";
    public static final String POINTS = "points";

    //Course table
    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_ID = "courseID";
    //SHORT_NAME
    //COURSE_NAME
    public static final String YEAR = "year";
    public static final String CFU = "cfu";
    public static final String SESSION = "session";
    public static final String LINK = "link";


    //Homework and course id that tracks the professor
    public static final String TRACK_PROFESSOR = "trackprofessor";

    //Relation table between students and courses
    public static final String STUDENTS_COURSES = "studentscourses";
    //STUDENT_ID
    //COURSE_NAME

    //Communications table
    public static final String COMMUNICATION = "communication";
    public static final String UNI_COMMUNICATIONS = "universitycommunications";
    public static final String DATE = "date";
    public static final String PROF_COMMUNICATIONS = "professorcommunications";

    //Exams tables
    public static final String EXAMS = "exams";
    public static final String EXAM_GRADES = "examgrades";
    public static final String EXAM_NAME = "examname";
    public static final String BUILDING = "building";
    public static final String CLASS = "class";
    public static final String GRADE = "grade";




    public SQLiteFillSampleDB(@Nullable Context context) {
        super(context, "unispark.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Students table statement
        String createTableStatement = "CREATE TABLE " + STUDENTS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT, " + IMAGE + " INTEGER, " + FACULTY + " TEXT, " + ACADEMIC_YEAR + " TEXT, " + STUDENT_ID + " TEXT);";

        db.execSQL(createTableStatement);


        //Professors table statement
        createTableStatement = "CREATE TABLE " + PROFESSORS_TABLE + " (" + PROF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT, " + WEBSITE + " TEXT, " + IMAGE + " INTEGER, " + FACULTY + " TEXT);";

        db.execSQL(createTableStatement);


        //University table statement
        createTableStatement = "CREATE TABLE " + UNI_TABLE + " (" + UNIVERSITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT);";

        db.execSQL(createTableStatement);


        //Homework table statement
        createTableStatement = "CREATE TABLE " + HOMEWORK_TABLE + " (" + ID_HW + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHORTNAME + " TEXT, " + COURSE_NAME + " TEXT, " + TITLE + " TEXT, " + EXPIRATION + " TEXT, " +
                INSTRUCTIONS + " TEXT, " + POINTS + " INTEGER, " + TRACK_PROFESSOR + " INTEGER, FOREIGN KEY(" + TRACK_PROFESSOR + ") REFERENCES " + PROFESSORS_TABLE + "(" + PROF_ID + "));";

        db.execSQL(createTableStatement);

        //Courses table statement
        createTableStatement = "CREATE TABLE " + COURSE_TABLE + " (" + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHORTNAME + " TEXT UNIQUE, " + COURSE_NAME + " TEXT UNIQUE, " + YEAR + " TEXT, " + CFU + " TEXT, " +
                SESSION + " TEXT, " + LINK + " TEXT, " + TRACK_PROFESSOR + " INTEGER, " + FACULTY + " TEXT, " + "FOREIGN KEY(" + TRACK_PROFESSOR + ") REFERENCES " + PROFESSORS_TABLE + "(" + PROF_ID + "));";

        db.execSQL(createTableStatement);

        //Relation students and courses table
        createTableStatement = "CREATE TABLE " + STUDENTS_COURSES + " (" + STUDENT_ID + " TEXT, " +
                COURSE_NAME + " TEXT, FOREIGN KEY(" + STUDENT_ID + ") REFERENCES " + STUDENTS_TABLE +
                "(" + STUDENT_ID + "), FOREIGN KEY(" + COURSE_NAME + ") references courses(" + COURSE_NAME + "));";

        db.execSQL(createTableStatement);

        //University communications table
        createTableStatement = "CREATE TABLE " + UNI_COMMUNICATIONS + " (" + UNIVERSITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IMAGE + " INTEGER, "
                + TITLE + " TEXT, " + DATE + " TEXT, " + COMMUNICATION + " TEXT, " + FACULTY + " TEXT);";

        db.execSQL(createTableStatement);

        //Professor communications table
        createTableStatement = "CREATE TABLE " + PROF_COMMUNICATIONS + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SHORTNAME + " TEXT, "
                + DATE + " TEXT, " + TITLE + " TEXT, " + COMMUNICATION + " TEXT);";

        db.execSQL(createTableStatement);

        //Exams table
        createTableStatement = "CREATE TABLE " + EXAMS + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXAM_NAME + " TEXT, " + DATE + " TEXT, " + BUILDING + " TEXT, " + CLASS + " TEXT);";

        db.execSQL(createTableStatement);

        //Exams grades table
        createTableStatement = "CREATE TABLE  " + EXAM_GRADES + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXAM_NAME + " TEXT, " + STUDENT_ID + " TEXT, " + GRADE + " TEXT);";

        db.execSQL(createTableStatement);
    }

    //Initialize Database
    public void initDatabase() {
        this.fillDB();
    }

    //Sample Database
    public void fillDB() {
        //Sample University
        UniversityModel university = new UniversityModel("universita",
                getHash("password"),
                "Tor Vergata",
                "Via le Mani dal Naso",
                null,
                null);

        //Add University to DB
        this.addUniversity(university);


        //Sample Professor
        ProfessorModel falessi = new ProfessorModel("falessi",
                getHash("password"),
                -1,
                "Davide",
                "Falessi",
                "https://didatticaweb.uniroma2.it/it/docenti/curriculum/T_153658-Davide-Falessi/0",
                R.drawable.courses_falessi,
                null, "Ingegneria Informatica");

        ProfessorModel lopresti = new ProfessorModel("lopresti",
                getHash("password"),
                -2,
                "Francesco",
                "Lo Presti",
                "https://www.lopresti.com",
                R.drawable.courses_lo_presti,
                null,
                "Ingegneria Informatica");

        ProfessorModel martinelli = new ProfessorModel("martinelli",
                getHash("password"),
                -3,
                "Francesco",
                "Martinelli",
                "http://robot2.disp.uniroma2.it/~fmartine/",
                R.drawable.courses_martinelli,
                null,
                "Ingegneria Informatica");

        ProfessorModel digennaro = new ProfessorModel("digennaro",
                getHash("password"),
                -4,
                "Vincenzo",
                "Di Gennaro",
                "http://www.mat.uniroma2.it/~digennar/",
                R.drawable.courses_martinelli,
                null,
                "Ingegneria Infromatica");

        ProfessorModel carnevale = new ProfessorModel("carnevale",
                getHash("password"),
                -5,
                "Daniele",
                "Carnevale",
                "https://sites.google.com/view/caaa1920/home",
                R.drawable.courses_martinelli,
                null,
                "Ingegneria Infromatica");

        //Add professors to DB and set their id correctly
        this.addProfessor(falessi);
        this.addProfessor(lopresti);
        this.addProfessor(martinelli);
        this.addProfessor(digennaro);
        this.addProfessor(carnevale);
        this.setProfessorId(falessi);
        this.setProfessorId(lopresti);
        this.setProfessorId(martinelli);
        this.setProfessorId(digennaro);
        this.setProfessorId(carnevale);


        //Sample Student
        StudentModel valzano = new StudentModel(R.drawable.profile_photo,
                "Emanuele",
                "Valzano",
                "valzano",
                getHash("password"),
                "Ingegneria Informatica",
                "2021/2022",
                "0268609",
                null);

        StudentModel lapiana = new StudentModel(R.drawable.profile_photo,
                "Andrea",
                "Lapiana",
                "lapiana",
                getHash("password"),
                "Ingegneria Informatica",
                "2021/2022",
                "0279544",
                null);

        StudentModel fanfarillo = new StudentModel(R.drawable.profile_photo,
                "Matteo",
                "Fanfarillo",
                "fanfarillo",
                getHash("password"),
                "Ingegneria Informatica",
                "2021/2022",
                "0279544",
                null);

        //Add students to DB
        this.addStudent(valzano);
        this.addStudent(lapiana);
        this.addStudent(fanfarillo);



        //Add courses to DB
        //Falessi
        CourseModel ISPW = new CourseModel(String.valueOf(falessi.getId()),
                "ISPW",
                "ING. DEL SOFTWARE E PROG. WEB",
                "2021",
                "12.0",
                "Winter",
                "https://didatticaweb.uniroma2.it/informazioni/index/insegnamento/196122-Ingegneria-Del-Software-E-Progettazione-Web/0",
                "Ingegneria Informatica");
        CourseModel ISPW2 = new CourseModel(String.valueOf(falessi.getId()),
                "ISPW II",
                "ING. DEL SOFTWARE E PROG. WEB II",
                "2021/2022",
                "6.0",
                "Winter",
                "https://didatticaweb.uniroma2.it/informazioni/index/insegnamento/196122-Ingegneria-Del-Software-E-Progettazione-Web/0",
                "Ingegneria Informatica");

        //Lo Presti
        CourseModel CE = new CourseModel(String.valueOf(lopresti.getId()),
                "CE",
                "CALCOLATORI ELETTRONICI",
                "2021/2022",
                "9.0",
                "Winter",
                "https://binance.com",
                "Ingegneria Informatica");

        //Martinelli
        CourseModel FOC = new CourseModel(String.valueOf(martinelli.getId()),
                "FOC",
                "FONDAMENTI DI CONTROLLI",
                "2021/2022",
                "9.0",
                "Summer",
                "https://binance.com",
                "Ingegneria Informatica");
        CourseModel ARL = new CourseModel(String.valueOf(martinelli.getId()),
                "ARL",
                "AUTOMATICA E ROBOTICA LAB.",
                "2021/2022",
                "12.0",
                "Winter",
                "https://binance.com",
                "Ingegneria Informatica");

        //Di Gennaro
        CourseModel GEOM = new CourseModel(String.valueOf(digennaro.getId()),
                "GEOM",
                "GEOMETRIA",
                "2021/2022",
                "9.0",
                "Winter",
                "https://www.mat.uniroma2.it/~digennar/GeomIngInf.html",
                "Ingegneria Informatica");

        //Carnevale
        CourseModel CA = new CourseModel(String.valueOf(carnevale.getId()),
                "CA",
                "CONTROLLI AUTOMATICI",
                "2021/2022",
                "6.0",
                "Winter",
                "https://sites.google.com/view/caaa1920/home",
                "Ingegneria Informatica");

        this.addCourse(ISPW);
        this.addCourse(ISPW2);
        this.addCourse(CE);
        this.addCourse(FOC);
        this.addCourse(ARL);
        this.addCourse(GEOM);
        this.addCourse(CA);


        //Connecting Students to their Courses
        //ISPW
        joinCourse(ISPW, valzano);
        joinCourse(ISPW, lapiana);
        joinCourse(ISPW, fanfarillo);
        //ISPW2
        joinCourse(ISPW2, valzano);
        joinCourse(ISPW2, fanfarillo);
        //CE
        joinCourse(CE, fanfarillo);
        //FOC
        joinCourse(FOC, fanfarillo);
        //ARL
        joinCourse(ARL, lapiana);
        //GEOM
        joinCourse(GEOM, lapiana);
        //CA
        joinCourse(CA, lapiana);

        //Add homeworks
        //Falessi
        HomeworkModel homework1 = new HomeworkModel("ISPW",
                "ING. DEL SOFTWARE E PROG. WEB",
                "Sequence Diagram",
                "02/02/2022",
                INSTRUCTIONS,
                "0",
                falessi.getId());
        HomeworkModel homework2 = new HomeworkModel("ISPW II",
                "ING. DEL SOFTWARE E PROG. WEB II",
                "Threads di Java",
                "04/02/2022",
                INSTRUCTIONS,
                "0",
                falessi.getId());
        HomeworkModel homework3 = new HomeworkModel("ISPW II",
                "ING. DEL SOFTWARE E PROG. WEB II",
                "Documentazione",
                "05/02/2022",
                INSTRUCTIONS,
                "0",
                falessi.getId());

        //Lo Presti
        HomeworkModel homework4 = new HomeworkModel("CE",
                "CALCOLATORI ELETTROCINICI",
                "CPU",
                "02/02/2022",
                INSTRUCTIONS,
                "0",
                lopresti.getId());
        HomeworkModel homework5 = new HomeworkModel("CE",
                "CALCOLATORI ELETTROCINICI",
                "Registri della RAM",
                "04/02/2022",
                INSTRUCTIONS,
                "0",
                lopresti.getId());
        HomeworkModel homework6 = new HomeworkModel("CE",
                "CALCOLATORI ELETTRONICI",
                "Flip Flop",
                "05/02/2022",
                INSTRUCTIONS,
                "0",
                lopresti.getId());

        //Martinelli
        HomeworkModel homework7 = new HomeworkModel("FOC",
                "FONDAMENTI DI CONTROLLI",
                "Funzioni di Trasferimento",
                "02/02/2022",
                INSTRUCTIONS,
                "0",
                martinelli.getId());
        HomeworkModel homework8 = new HomeworkModel("ARL",
                "AUTOMATICA E ROBOTICA LAB.",
                "Scorbot",
                "04/02/2022",
                INSTRUCTIONS,
                "0",
                martinelli.getId());

        //Di Gennaro
        HomeworkModel homework9 = new HomeworkModel("GEOM",
                "GEOMETRIA",
                "Matrici",
                "04/02/2022",
                INSTRUCTIONS,
                "0",
                digennaro.getId());

        //Falessi
        this.addHomework(homework1);
        this.addHomework(homework2);
        this.addHomework(homework3);
        //Lo Presti
        this.addHomework(homework4);
        this.addHomework(homework5);
        this.addHomework(homework6);
        //Martinelli
        this.addHomework(homework7);
        this.addHomework(homework8);
        //Di Gennaro
        this.addHomework(homework9);


        //Professors Communications
        //Falessi
        ProfessorCommunicationModel pCom1 = new ProfessorCommunicationModel(R.drawable.courses_falessi,
                "ISPW",
                "ING. DEL SOFTWARE E PROG. WEB",
                "DAVIDE FALESSI",
                "10/12/2021",
                "Exam Result",
                "Risultati dell'esame del 3/02/2022: Tutti promossi con voti pieni :)");
        //Martinelli
        ProfessorCommunicationModel pCom2 = new ProfessorCommunicationModel(R.drawable.courses_martinelli,
                "ARL",
                "AUTOMATICA E ROBOTICA LAB.",
                "FRANCESCO MARTINELLI",
                "20/03/2021",
                "Date Esami",
                "Pre appello: 14 Gennaio" +
                        "\n" +
                        "Primo Appello: 29 Gennaio" +
                        "\n" +
                        "Secondo Appello: 4 Febbraio");
        //Carnevale
        ProfessorCommunicationModel pCom3 = new ProfessorCommunicationModel(R.drawable.courses_falessi,
                "CA",
                "CONTROLLI AUTOMATICI",
                "DANIELE CARNEVALE",
                "10/12/2021",
                "Mathlab",
                "Nuove funzioni Mathlab scaricabili presso il seguente link:");
        //Lo Presti
        ProfessorCommunicationModel pCom4 = new ProfessorCommunicationModel(R.drawable.courses_lo_presti,
                "CE",
                "CALCOLATORI ELETTROCINICI",
                "Francesco Lo Presti",
                "16/04/2020",
                "Esame Rinviato",
                "Esame rinviato a data da destinarsi.");
        //Di Gennaro
        ProfessorCommunicationModel pCom5 = new ProfessorCommunicationModel(R.drawable.courses_lo_presti,
                "GEOM",
                "GEOMETRIA",
                "Vincenzo Di Gennaro",
                "16/04/2020",
                "Esercizio a Tema Natalizio",
                "https://www.youtube.com/watch?v=oyEyMjdD2uk\n" +
                        "\n" +
                        "Esercizio a tema natalizio (facoltativo). Considerata la canzone Twelve Days Of Christmas, dimostrare che il numero complessivo di doni d(m) ottenuti fino al giorno (12-m)-esimo incluso è (14-m)(13-m)(12-m)/6.\n" +
                        "\n" +
                        "Sia N una matrice nilpotente con polinomio caratteristico t^364 e polinomio minimo t^12. Il rango di N^m è proprio d(m) per tutti gli m non negativi minori o uguali a 12. Dimostrare che la forma canonica di Jordan di N consta di 13-k blocchi di ordine k per ogni k compreso fra 1 e 12. ");
        ProfessorCommunicationModel pCom6 = new ProfessorCommunicationModel(R.drawable.courses_lo_presti,
                "GEOM",
                "GEOMETRIA",
                "Vincenzo Di Gennaro",
                "16/04/2020",
                "Buone Vacanze!!",
                "Carissimi studenti, Buone vacanze a tutti!");

        this.addProfessorCommunication(pCom1);
        this.addProfessorCommunication(pCom2);
        this.addProfessorCommunication(pCom3);
        this.addProfessorCommunication(pCom4);
        this.addProfessorCommunication(pCom5);
        this.addProfessorCommunication(pCom6);


        //University Communications
        UniversityCommunicationModel uCom1 = new UniversityCommunicationModel(R.drawable.rettorato, "Nuovo Edificio", DATE, COMMUNICATION, "Ingegneria Informatica");
        UniversityCommunicationModel uCom2 = new UniversityCommunicationModel(R.drawable.formula_uno, "Garage", DATE, COMMUNICATION, "Ingegneria Informatica");
        UniversityCommunicationModel uCom3 = new UniversityCommunicationModel(R.drawable.schedule, "Orari Scolastici", DATE, COMMUNICATION, "Ingegneria Informatica");
        UniversityCommunicationModel uCom4 = new UniversityCommunicationModel(R.drawable.green_pass, "Green Pass", DATE, COMMUNICATION, "All");
        UniversityCommunicationModel uCom5 = new UniversityCommunicationModel(R.drawable.drone, "Gara Droni", DATE, COMMUNICATION, "All");
        UniversityCommunicationModel uCom6 = new UniversityCommunicationModel(R.drawable.blank_img, "PROVA", DATE, COMMUNICATION, "Giurisprudenza");

        this.addUniversityCommunication(uCom1);
        this.addUniversityCommunication(uCom2);
        this.addUniversityCommunication(uCom3);
        this.addUniversityCommunication(uCom4);
        this.addUniversityCommunication(uCom5);
        this.addUniversityCommunication(uCom6);


        //Add exams
        BookingExamModel exam1 = new BookingExamModel(0,
                "ING. DEL SOFTWARE E PROG. WEB",
                "2021/2022",
                "20/01/2022",
                "12.0",
                "A4",
                "ING.INF");
        BookingExamModel exam2 = new BookingExamModel(0,
                "CALCOLATORI ELETTRONICI",
                "2021/2022",
                "27/01/2022",
                "12.0",
                "A7",
                "ING.INF");

        this.addExam(exam1);
        this.addExam(exam2);

        ExamGradeModel examGrade = new ExamGradeModel(0, "CALCOLATORI ELETTRONICI", "2021/2022", "27/01/2022", "12.0", "28");

        this.addExamGrade(examGrade, lapiana);
    }




    //Add Student to the Database
    public boolean addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, student.getFirstName());
        cv.put(LASTNAME, student.getLastName());
        cv.put(EMAIL, student.getEmail());
        cv.put(PASSWORD, student.getPassword());
        cv.put(IMAGE, student.getImageID());
        cv.put(FACULTY, student.getFaculty());
        cv.put(ACADEMIC_YEAR, student.getAcademicYear());
        cv.put(STUDENT_ID, student.getId());

        long insert = db.insert(STUDENTS_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add Professor to the Database
    public boolean addProfessor(ProfessorModel professor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, professor.getFirstName());
        cv.put(LASTNAME, professor.getLastName());
        cv.put(EMAIL, professor.getEmail());
        cv.put(PASSWORD, professor.getPassword());
        cv.put(WEBSITE, professor.getWebsite());
        cv.put(IMAGE, professor.getImage());
        cv.put(FACULTY, professor.getFaculty());

        long insert = db.insert(PROFESSORS_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Set professor Id
    public boolean setProfessorId(ProfessorModel professor) {
        //get id from the database
        Cursor cursor = null;
        boolean check = false;

        String queryString = "SELECT " + PROF_ID + " FROM " + PROFESSORS_TABLE + " WHERE email = '" + professor.getEmail() + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            professor.setId(cursor.getInt(0));
            check = true;
        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return check;
    }


    //Add University to the Database
    public boolean addUniversity(UniversityModel university) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMAIL, university.getEmail());
        cv.put(PASSWORD, university.getPassword());

        long insert = db.insert(UNI_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add Homework: Put Homeworks into the Database
    public boolean addHomework(HomeworkModel homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SHORTNAME, homework.getShortName());
        cv.put(COURSE_NAME, homework.getFullname());
        cv.put(TITLE, homework.getTitle());
        cv.put(EXPIRATION, homework.getExpiration());
        cv.put(INSTRUCTIONS, homework.getInstructions());
        cv.put(POINTS, homework.getPoints());
        cv.put(TRACK_PROFESSOR, homework.getTrackProfessor());

        //Insert into Database: Homework Table
        long insert = db.insert(HOMEWORK_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add course to Database
    public boolean addCourse(CourseModel course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SHORTNAME, course.getShortName());
        cv.put(COURSE_NAME, course.getFullName());
        cv.put(YEAR, course.getCourseYear());
        cv.put(CFU, course.getCfu());
        cv.put(SESSION, course.getSession());
        cv.put(LINK, course.getLink());
        cv.put(TRACK_PROFESSOR, course.getId());
        cv.put(FACULTY, course.getFaculty());

        //Insert into Database: Homework Table
        long insert = db.insert(COURSE_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //A student joins a new course
    public boolean joinCourse(CourseModel course, StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_ID, student.getId());
        cv.put(COURSE_NAME, course.getFullName());

        long insert = db.insert(STUDENTS_COURSES, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add University communication
    public boolean addUniversityCommunication(UniversityCommunicationModel communication){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IMAGE, communication.getBackground());
        cv.put(TITLE, communication.getTitle());
        cv.put(DATE, communication.getDate());
        cv.put(COMMUNICATION, communication.getCommunication());
        cv.put(FACULTY, communication.getFaculty());

        long insert = db.insert(UNI_COMMUNICATIONS, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add Professor communication
    public boolean addProfessorCommunication(ProfessorCommunicationModel communication){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SHORTNAME, communication.getShortCourseName());
        cv.put(DATE, communication.getDate());
        cv.put(TITLE, communication.getType());
        cv.put(COMMUNICATION, communication.getCommunication());

        long insert = db.insert(PROF_COMMUNICATIONS, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add booking exams
    public boolean addExam(BookingExamModel exam){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EXAM_NAME, exam.getName());
        cv.put(DATE, exam.getDate());
        cv.put(BUILDING, exam.getBuilding());
        cv.put(CLASS, exam.getClassroom());
        long insert = db.insert(EXAMS, null, cv);

        if (insert == -1) return false;
        else return true;
    }


    //Add exam grade
    public boolean addExamGrade(ExamGradeModel examGrade, StudentModel student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EXAM_NAME, examGrade.getName());
        cv.put(STUDENT_ID, student.getId());
        cv.put(GRADE, examGrade.getResult());
        long insert = db.insert(EXAM_GRADES, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

