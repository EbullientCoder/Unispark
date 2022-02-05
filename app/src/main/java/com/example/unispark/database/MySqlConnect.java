package com.example.unispark.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnect {
    private static MySqlConnect instance = null;
    private Connection conn = null;

    protected MySqlConnect() {}

    public static MySqlConnect getInstance() {
        if(MySqlConnect.instance==null) {
            MySqlConnect.instance = new MySqlConnect();
        }

        return MySqlConnect.instance;
    }


    public Connection getDBConnection() throws SQLException {

        if (this.conn == null){
            this.conn = DriverManager.getConnection("jdbc:mysql://unispark-db.cmh7hqdc7yex.us-east-1.rds.amazonaws.com:3306/Unispark-DB", "admin", "password");
        }
        return this.conn;
    }



}

