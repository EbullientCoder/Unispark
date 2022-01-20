package com.example.unispark.database.others;


import android.database.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnect {


    private static MySqlConnect instance=null;
    private Connection conn = null;

    protected MySqlConnect() {}

    public static synchronized MySqlConnect getInstance() {

        if(MySqlConnect.instance==null) {
            MySqlConnect.instance = new MySqlConnect();
        }

        return MySqlConnect.instance;
    }

    //Get a readable SQLiteDatabase ("unispark.db")
    public synchronized Connection getDBConnection() throws SQLiteException {

        if (this.conn == null){

            try{
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection("jdbc:mysql://unispark-db.cmh7hqdc7yex.us-east-1.rds.amazonaws.com:3306/Unispark-DB", "admin", "passwordUnispark");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return this.conn;

    }



}

