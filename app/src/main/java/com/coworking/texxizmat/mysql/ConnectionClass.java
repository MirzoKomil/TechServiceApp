package com.coworking.texxizmat.mysql;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class ConnectionClass {
    private static final String DB = "texxizma_tex_x_db";
    private static final String IP = "83.69.139.250";
    private static final String PORT = "3306";
    private static final String USER = "texxizma_tex_x_user";
    private static final String PASSWORD = "mrzohid90.";

    public Connection CONN() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + IP + ":" + PORT + "/" + DB;
            conn = DriverManager.getConnection(connectionString, USER, PASSWORD);
        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }


}
