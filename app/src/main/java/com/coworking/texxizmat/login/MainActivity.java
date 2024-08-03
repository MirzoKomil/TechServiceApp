package com.coworking.texxizmat.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.ActsActivity;
import com.coworking.texxizmat.MainMenuActivity;
import com.coworking.texxizmat.SuccesActivity;
import com.coworking.texxizmat.mysql.ConnectionClass;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    Button button;

    ProgressBar progressBar;
    TextInputEditText editTextEmail, editTextPassword;
    ConnectionClass connectionClass;
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionClass = new ConnectionClass();
        button = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBarMain);
        editTextEmail = findViewById(R.id.txt_username);
        editTextPassword = findViewById(R.id.txt_password);
        TextView text_welcome = findViewById(R.id.text_welcome);
        ImageView img_welcome = findViewById(R.id.image_logo);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeIn_img = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        text_welcome.startAnimation(fadeIn);
        img_welcome.startAnimation(fadeIn_img);

        // Set up button listeners


        button.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String username = String.valueOf(editTextEmail.getText());
            String password = String.valueOf(editTextPassword.getText());

            if (TextUtils.isEmpty(username)) {
                showError(getString(R.string.enter_username));
                return;
            }

            if (TextUtils.isEmpty(password)) {
                showError(getString(R.string.enter_password));
                return;
            }

            // Perform authentication
            authenticateUser(username, password);
        });
    }

    private void authenticateUser(String username, String password) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                if (con == null) {
                    showError(getString(R.string.connection_error));
                } else {
                    String query = "SELECT * FROM reg_table WHERE username = ? AND password = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Retrieve all necessary data before closing the ResultSet
                        String surname = rs.getString("surname");
                        String name = rs.getString("name");
                        String middleName = rs.getString("middle_name");
                        String phone = rs.getString("phone");



                        rs.close();
                        stmt.close();
                        con.close();

                        // Pass the data to the UI thread
                        runOnUiThread(() -> {
                            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                            intent.putExtra("surname", surname);
                            intent.putExtra("name", name);
                            intent.putExtra("middle_name", middleName);
                            intent.putExtra("phone", phone);
                            intent.putExtra("user_name", username);
                            startActivity(intent);
                            finish();
                        });
                    } else {
                        rs.close();
                        stmt.close();
                        con.close();
                        showError(getString(R.string.invalid_credentials));
                    }
                }
            } catch (Exception e) {
                showError(getString(R.string.connection_error) + ": " + e.getMessage());
            }
        });
    }


    private void showError(String message) {
        runOnUiThread(() -> {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        });
    }
}
