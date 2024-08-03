package com.coworking.texxizmat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.coworking.SqlActivity;
import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.login.RegActivity;
import com.coworking.texxizmat.mysql.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainMenuActivity extends AppCompatActivity {
    TextView name;
    TextView surname, middlename;
    TextView phoneNumber, toolbar;

    ConnectionClass connectionClass;
    Connection con;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        // TextView'larni topish
        name = findViewById(R.id.tv_name);
        surname = findViewById(R.id.tv_surname);
        middlename = findViewById(R.id.tv_fatherName);
        phoneNumber = findViewById(R.id.tv_tel_number);
        toolbar = findViewById(R.id.toolbar_user);

        Button button = findViewById(R.id.btn_next);
        Button contract = findViewById(R.id.btn_contract);
        Button pincode = findViewById(R.id.btn_pincode);
        Button version = findViewById(R.id.btn_version);
        Button el_imzo = findViewById(R.id.btn_el_imzo);
        Button addEmployee = findViewById(R.id.btn_admin);
        ImageView back_btn = findViewById(R.id.left_icon);



        Intent intent = getIntent();
        String surname_ = intent.getStringExtra("surname");
        String name_ = intent.getStringExtra("name");
        String middleName = intent.getStringExtra("middle_name");
        String phone = intent.getStringExtra("phone");
        String username = intent.getStringExtra("user_name");

        if ("Admin".equals(username) || "admin".equals(username) || "rahimov89".equals(username)){
            version.setVisibility(View.VISIBLE);
            addEmployee.setVisibility(View.VISIBLE);

        } else {
            version.setVisibility(View.GONE);
            addEmployee.setVisibility(View.GONE);
        }

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainMenuActivity.this, RegActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        surname.setText(surname_);
        name.setText(name_);
        middlename.setText(middleName);
        phoneNumber.setText(phone);
        toolbar.setText(username);


        connectionClass = new ConnectionClass();
        connect();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ActsActivity.class);
                startActivity(intent);
            }
        });

        contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });

        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SqlActivity.class);
                startActivity(intent);
            }
        });

        el_imzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SignatureActivity.class);
                startActivity(intent);
            }
        });

        // Fayl ochilganda database ma'lumotlarini olish uchun createDatabase chaqiramiz
        //getDataRegTable();
    }

    private void connect() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                if (con == null) {
                    str = "Error in connection";
                } else {
                    str = "Connected";
                }
            } catch (Exception e) {
                str = "Connection error: " + e.getMessage();
            }
            runOnUiThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });

    }

    private void setTextView(TextView textView, String text) {
        if (text != null) {
            textView.setText(text);
        }
    }



       public void getDataRegTable() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                String query = "SELECT id_reg_table, name,surname, middle_name, phone, email FROM texxizma_tex_x_db.reg_table LIMIT 1;";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                // Getting the name, family, and phone column values for the first row only
                if (rs.next()) {
                    String id = rs.getString("id_reg_table");
                    String name = rs.getString("name");
                    String family = rs.getString("surname");
                    String middle = rs.getString("middle_name");
                    String phone = rs.getString("phone");

                    // Updating the TextViews with the results
                    runOnUiThread(() -> {
                        this.name.setText(name);
                        this.surname.setText(family);
                        this.middlename.setText(middle);
                        this.phoneNumber.setText(phone);
                    });
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
