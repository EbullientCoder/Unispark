package com.example.unispark.database;



import static com.example.unispark.controller.student.Links.createCourses;
import static com.example.unispark.database.Password.checkPassword;
import static com.example.unispark.database.Password.getHash;

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

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Users table (students, professors and university)
    public static final String STUDENTS_TABLE = "students";
    public static final String PROFESSORS_TABLE = "professors";
    public static final String UNI_TABLE = "university";
    public static final String STUDENT_ID = "studentID";
    public static final String PROF_ID = "professorID";
    public static final String UNI_ID = "universityID";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String IMAGE = "image";
    public static final String WEBSITE = "website";

    //Homework table
    public static final String HOMEWORK_TABLE = "homework";
    public static final String ID_HW = "homeworkID";
    public static final String SHORTNAME = "shortname";
    public static final String FULLNAME = "fullname";
    public static final String TITLE = "title";
    public static final String EXPIRATION = "expiration";
    public static final String INSTRUCTIONS = "instructions";
    public static final String POINTS = "points";

    //Course table
    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_ID = "courseID";
    public static final String YEAR = "year";
    public static final String CFU = "cfu";
    public static final String SESSION = "session";
    public static final String LINK = "link";

    //Homework and course id that tracks the professor
    public static final String TRACKPROFESSOR = "trackprofessor";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "unispark.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Students table statement
        String createTableStatement = "CREATE TABLE " + STUDENTS_TABLE + " (" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT, " + IMAGE + " INTEGER);";

        db.execSQL(createTableStatement);


        //Professors table statement
        createTableStatement = "CREATE TABLE " + PROFESSORS_TABLE + " (" + PROF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT, " + WEBSITE + " TEXT, " + IMAGE + " INTEGER);";

        db.execSQL(createTableStatement);


        //University table statement
        createTableStatement = "CREATE TABLE " + UNI_TABLE + " (" + UNI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT UNIQUE, " + PASSWORD + " TEXT);";

        db.execSQL(createTableStatement);


        //Homework table statement
        createTableStatement = "CREATE TABLE " + HOMEWORK_TABLE + " (" + ID_HW + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHORTNAME + " TEXT UNIQUE, " + FULLNAME + " TEXT UNIQUE, " + TITLE + " TEXT, " + EXPIRATION + " TEXT, " +
                INSTRUCTIONS + " TEXT, " + POINTS + " INTEGER, " + TRACKPROFESSOR + " INTEGER, FOREIGN KEY(" + TRACKPROFESSOR + ") REFERENCES " + PROFESSORS_TABLE + "(" + PROF_ID + "));";

        db.execSQL(createTableStatement);

        //Courses table statement
        createTableStatement = "CREATE TABLE " + COURSE_TABLE + " (" + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHORTNAME + " TEXT UNIQUE, " + FULLNAME + " TEXT UNIQUE, " + YEAR + " TEXT, " + CFU + " TEXT, " +
                SESSION + " TEXT, " + LINK + " TEXT, " + TRACKPROFESSOR + " INTEGER, FOREIGN KEY(" + TRACKPROFESSOR + ") REFERENCES " + PROFESSORS_TABLE + "(" + PROF_ID + "));";

        db.execSQL(createTableStatement);


    }

    //Initialize Database
    public void initDatabase(){
        this.fillDB();
        this.fillHomeworkDB();
    }

    //Add Student to the Database
    public boolean addStudent(StudentModel student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, student.getFirstName());
        cv.put(LASTNAME, student.getLastName());
        cv.put(EMAIL, student.getEmail());
        cv.put(PASSWORD, student.getPassword());
        cv.put(IMAGE, student.getImageID());

        long insert = db.insert(STUDENTS_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Add Professor to the Database
    public boolean addProfessor(ProfessorModel professor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, professor.getFirstName());
        cv.put(LASTNAME, professor.getLastName());
        cv.put(EMAIL, professor.getEmail());
        cv.put(PASSWORD, professor.getPassword());
        cv.put(WEBSITE, professor.getWebsite());
        cv.put(IMAGE, professor.getImage());

        long insert = db.insert(PROFESSORS_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Set professor Id
    public boolean setProfessorId(ProfessorModel professor){
        //get id from the database
        boolean check = false;
        String queryString = "SELECT " + PROF_ID + " FROM " + PROFESSORS_TABLE + " WHERE email = '" + professor.getEmail() + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            professor.setId(cursor.getInt(0));
            check = true;
        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return check;
    }

    //Get a ProfessorModel instance using the email
    public ProfessorModel getProfessor(String email){
        //Select all professor fields marked by the email
        String queryString = "SELECT * FROM " + PROFESSORS_TABLE + " WHERE email = '" + email + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        //Professor attributes
        ProfessorModel professor = null;
        int professorId = -1;
        String firtName = null;
        String lastName = null;
        String emailProfessor = null;
        String passwordProfessor = null;
        String website = null;
        int image = -1;

        if (cursor.moveToFirst()){
            professorId = cursor.getInt(0);
            firtName = cursor.getString(1);
            lastName = cursor.getString(2);
            emailProfessor = cursor.getString(3);
            passwordProfessor = cursor.getString(4);
            website = cursor.getString(5);
            image = cursor.getInt(6);
        }

        //List of courses kept by professor and their attributes
        List<CourseModel> coursesList = new ArrayList<>();
        CourseModel course;
        String courseId;
        String shortName;
        String fullName;
        String courseYear;
        String cfu;
        String session;
        String link;

        //Select all courses marked by professor id
        queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE trackprofessor = " + professorId + ";";
        cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){

            do{
                courseId = String.valueOf(cursor.getInt(7));
                shortName = cursor.getString(1);
                fullName = cursor.getString(2);
                courseYear = cursor.getString(3);
                cfu = cursor.getString(4);
                session = cursor.getString(5);
                link = cursor.getString(6);

                //Create a new course and add it to the professor's course list
                course = new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link);
                coursesList.add(course);

            } while (cursor.moveToNext());
        }

        //Create the professor instance
        professor = new ProfessorModel(emailProfessor,
                passwordProfessor,
                professorId,
                firtName,
                lastName,
                website,
                image,
                coursesList);

        //close both the cursor and the db when done.
        cursor.close();
        db.close();

        return professor;
    }

    //Add University to the Database
    public boolean addUniversity(UniversityModel university){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMAIL, university.getEmail());
        cv.put(PASSWORD, university.getPassword());

        long insert = db.insert(UNI_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Sample Database
    public void fillDB(){
        //Sample Student
        StudentModel student = new StudentModel(R.drawable.profile_photo,
                "Emanuele",
                "Valzano",
                "emanuele@gmail.com",
                getHash("emanuele99"),
                null);
        StudentModel student1 = new StudentModel(R.drawable.profile_photo,
                "Emanuele",
                "Valzano",
                "a",
                getHash("a"),
                null);

        //Add students to DB
        this.addStudent(student);
        this.addStudent(student1);

        //Sample Professor
        ProfessorModel professor1 = new ProfessorModel("falessi",
                getHash("falessi"),
                -1,
                "Davide",
                "Falessi",
                "https://www.falessi.com",
                R.drawable.courses_falessi,
                null);

        ProfessorModel professor2 = new ProfessorModel("lopresti",
                getHash("lo"),
                -1,
                "Boh",
                "Lo Presti",
                "https://www.lopresti.com",
                R.drawable.courses_lo_presti,
                null);

        //Add professors to DB and set their id correctly
        this.addProfessor(professor1);
        this.setProfessorId(professor1);

        this.addProfessor(professor2);
        this.setProfessorId(professor2);

        //Add homeworks
        HomeworkModel homework1 = new HomeworkModel("ISPW",
                "ING. DEL SOFTWARE E PROG. WEB",
                "Esame",
                "DUE OCT 28",
                INSTRUCTIONS,
                "1 Points",
                professor1.getId());

        this.addHomework(homework1);

        HomeworkModel homework2 = new HomeworkModel("CALC",
                "CALCOLATORI ELETTRONICI",
                "PIPELINE",
                "DUE OCT 28",
                INSTRUCTIONS,
                "1 Points",
                professor2.getId());

        this.addHomework(homework2);

        //Add courses to DB
        CourseModel course = new CourseModel(String.valueOf(professor1.getId()),
                "ISPW",
                "ING. DEL SOFTWARE E PROG. WEB",
                "2021",
                "12.0",
                "2",
                "https://google.com");

        this.addCourse(course);

        //Sample University
        UniversityModel university = new UniversityModel("universita@gmail.com",
                getHash("SUCA"),
                "Tor Vergogna",
                "Via le Mani dal Naso",
                null,
                null);

        //Add University to DB
        this.addUniversity(university);
    }

    //Add Homework: Put Homeworks into the Database
    public boolean addHomework(HomeworkModel homework){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SHORTNAME, homework.getShortName());
        cv.put(FULLNAME, homework.getFullname());
        cv.put(TITLE, homework.getTitle());
        cv.put(EXPIRATION, homework.getExpiration());
        cv.put(INSTRUCTIONS, homework.getInstructions());
        cv.put(POINTS, homework.getPoints());
        cv.put(TRACKPROFESSOR, homework.getTrackProfessor());

        //Insert into Database: Homework Table
        long insert = db.insert(HOMEWORK_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Get Homework: Return an Homework List to Student Home show into RecyclerView
    public List<HomeworkModel> getHomework(){
        List<HomeworkModel> homeworkList = new ArrayList<>();

        //get data from the database
        String queryString = "SELECT * FROM " + HOMEWORK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                String shortName = cursor.getString(1);
                String fullName = cursor.getString(2);
                String title = cursor.getString(3);
                String expiration = cursor.getString(4);
                String instructions = cursor.getString(5);
                String points = cursor.getString(6);
                int trackProfessor = cursor.getInt(7);

                HomeworkModel newHomework = new HomeworkModel(shortName, fullName, title, expiration, instructions, points, trackProfessor);
                homeworkList.add(newHomework);
            } while (cursor.moveToNext());
        }

        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return homeworkList;
    }

    //Get Homework: Return an Homework List to Professor Home show into RecyclerView
    public List<HomeworkModel> getAssignedHomework(ProfessorModel professor){
        List<HomeworkModel> homeworkList = new ArrayList<>();

        //get data from the database
        String queryString;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        //Select homework created by professor marked by trackprofessor id
        queryString = "SELECT * FROM " + HOMEWORK_TABLE + " WHERE " + TRACKPROFESSOR + " = " + professor.getId() + ";";
        cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                String shortName = cursor.getString(1);
                String fullName = cursor.getString(2);
                String title = cursor.getString(3);
                String expiration = cursor.getString(4);
                String instructions = cursor.getString(5);
                String points = cursor.getString(6);
                int trackProfessor = cursor.getInt(7);

                HomeworkModel newHomework = new HomeworkModel(shortName, fullName, title, expiration, instructions, points, trackProfessor);
                homeworkList.add(newHomework);
            } while (cursor.moveToNext());
        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return homeworkList;

    }

    //Sample Database: Homework(deprecated, not used any more)
    public void fillHomeworkDB(){
        List<HomeworkModel> homeworksItem = new ArrayList<>();

        HomeworkModel hom1 = new HomeworkModel("ARL", "AUTOMATICA E ROBOTICA LAB.", "Scorbot","DUE DEC 27", INSTRUCTIONS, "0 Points", 10);
        HomeworkModel hom2 = new HomeworkModel("CA","CONTROLLI AUTOMATICI", "Nyquist","DUE NOV 1", INSTRUCTIONS, "2 Points", 10);
        HomeworkModel hom3 = new HomeworkModel("GEOM", "GEOMETRIA", "Matrici","DUE NOV 2", INSTRUCTIONS, "3 Points",10);
        HomeworkModel hom4 = new HomeworkModel("RO", "RICERCA OPERATIVA","Esame", "DUE NOV 4", INSTRUCTIONS, "4 Points", 10);
        HomeworkModel hom5 = new HomeworkModel("XXX", "CORSO UNIVERSITARIO", "YYY", "DUE XX YY", INSTRUCTIONS, "5 Points", 10);

        homeworksItem.add(hom1);
        homeworksItem.add(hom2);
        homeworksItem.add(hom3);
        homeworksItem.add(hom4);
        homeworksItem.add(hom5);

        //For each ExamItem into HomeworksList "addHomework()" will be called and every ExamItem will be put into the DB
        for (int i = 0; i < homeworksItem.size(); i++) this.addHomework(homeworksItem.get(i));
    }

    //Add course to Database
    public boolean addCourse(CourseModel course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SHORTNAME, course.getShortName());
        cv.put(FULLNAME, course.getFullName());
        cv.put(YEAR, course.getCourseYear());
        cv.put(CFU, course.getCfu());
        cv.put(SESSION, course.getSession());
        cv.put(LINK, course.getLink());
        cv.put(TRACKPROFESSOR, course.getId());

        //Insert into Database: Homework Table
        long insert = db.insert(COURSE_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Login: Verify that user credentials are into the DB
    public boolean login(String user, String emailText, String passwordText) {
        String queryString = null;

        if (user == "STUDENT"){
            queryString = "SELECT email, password FROM " + STUDENTS_TABLE + " where email = '" + emailText + "';";
        }
        if (user == "PROFESSOR"){
            queryString = "SELECT email, password FROM " + PROFESSORS_TABLE + " where email = '" + emailText + "';";
        }
        if (user == "UNIVERSITY"){
            queryString = "SELECT email, password FROM " + UNI_TABLE + " where email = '" + emailText + "';";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            String storedPassword = cursor.getString(1);

            if (checkPassword(passwordText, storedPassword)) return true;
            else return false;
        }
        else return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

