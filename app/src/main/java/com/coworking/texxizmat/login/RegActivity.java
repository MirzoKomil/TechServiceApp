package com.coworking.texxizmat.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.MainMenuActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegActivity extends AppCompatActivity {

    private TextInputEditText usernameEditText, nameEditText, surnameEditText, middleNameEditText, phoneNumEditText, emailEditText, passwordEditText;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Connection con;

    // Define your database connection details
    private static final String DB = "texxizma_tex_x_db";
    private static final String IP = "83.69.139.250";
    private static final String PORT = "3306";
    private static final String USER = "texxizma_tex_x_user";
    private static final String PASSWORD = "mrzohid90.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        usernameEditText = findViewById(R.id.username);
        nameEditText = findViewById(R.id.name);
        surnameEditText = findViewById(R.id.surname);
        middleNameEditText = findViewById(R.id.middleName);
        phoneNumEditText = findViewById(R.id.phone_num_reg);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        TextView  reg_btn = findViewById(R.id.btn_login);

        Button btnRegistration = findViewById(R.id.btn_registration);
        btnRegistration.setOnClickListener(this::registerUser);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(View view) {
        String username = usernameEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String middleName = middleNameEditText.getText().toString();
        String phoneNum = phoneNumEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || name.isEmpty() || surname.isEmpty() || middleName.isEmpty() || phoneNum.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            try {
                con = CONN();
                String query = "INSERT INTO texxizma_tex_x_db.reg_table (username, name, surname, middle_name, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, name);
                stmt.setString(3, surname);
                stmt.setString(4, middleName);
                stmt.setString(5, phoneNum);
                stmt.setString(6, email);
                stmt.setString(7, password);

                int rowsInserted = stmt.executeUpdate();

                runOnUiThread(() -> {
                    if (rowsInserted > 0) {
                        Toast.makeText(RegActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(RegActivity.this, "Failed to register user.", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (SQLException e) {
                runOnUiThread(() -> Toast.makeText(RegActivity.this, "Database error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
            }
        });
    }

    private void clearFields() {
        usernameEditText.setText("");
        nameEditText.setText("");
        surnameEditText.setText("");
        middleNameEditText.setText("");
        phoneNumEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
    }

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
