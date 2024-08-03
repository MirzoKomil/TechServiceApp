package com.coworking;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayInputStream;


import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.mysql.ConnectionClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SqlActivity extends AppCompatActivity {

     ConnectionClass connectionClass;
     Connection con;
     ResultSet rs;
     String name, str;
     TextView textView;
    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sql);

        tableLayout = findViewById(R.id.tableLayout);
        connectionClass = new ConnectionClass();
        connect();
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
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void createDatabase(View view) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                String query = "SELECT * FROM texxizma_tex_x_db.act_table;";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                // Clear any existing rows in the TableLayout
                runOnUiThread(() -> tableLayout.removeAllViews());

                // Adding table headers dynamically
                runOnUiThread(() -> {
                    TableRow headerRow = new TableRow(SqlActivity.this);
                    headerRow.addView(createTextView("Shartnoma №"));
                    headerRow.addView(createTextView("Manzil"));
                    headerRow.addView(createTextView("F.I.SH"));
                    headerRow.addView(createTextView("Telefon"));
                    headerRow.addView(createTextView("Sana"));
                    headerRow.addView(createTextView("Rasm"));
                    headerRow.addView(createTextView("Oldingi rasm"));
                    headerRow.addView(createTextView("Keyingi rasm"));
                    headerRow.addView(createTextView("Gaz standart"));
                    headerRow.addView(createTextView("Xujjatlar"));
                    headerRow.addView(createTextView("Moslik"));
                    headerRow.addView(createTextView("O'zgartirish"));
                    headerRow.addView(createTextView("Texnik ko'rik"));
                    headerRow.addView(createTextView("Prof xizmat"));
                    headerRow.addView(createTextView("Ser xizmat"));
                    headerRow.addView(createTextView("Konsalting"));
                    headerRow.addView(createTextView("Sug'urta"));
                    headerRow.addView(createTextView("Qoida"));
                    headerRow.addView(createTextView("Summa"));
                    headerRow.addView(createTextView("Imzo"));
                    tableLayout.addView(headerRow);
                });

                // Adding table rows dynamically
                while (rs.next()) {
                    String idAct = rs.getString("id_act");
                    String actAddress = rs.getString("act_address");
                    String actAbonentFulname = rs.getString("act_abonent_fulname");
                    String actPhone = rs.getString("act_phone");
                    String actDate = rs.getString("act_date");

                    byte[] actImgBig = rs.getBytes("act_img_big");
                    byte[] actImgBefore = rs.getBytes("act_img_before");
                    byte[] actImgAfter = rs.getBytes("act_img_after");
                    byte[] actSignature = rs.getBytes("act_signature");

                    String actCheckGStandart = rs.getString("act_check_g_standart");
                    String actCheckXDocYes = rs.getString("act_check_x_doc_yes");
                    String actCheckGDocCompatible = rs.getString("act_check_g_doc_compatible");
                    String actCheckGDocChanged = rs.getString("act_check_g_doc_changed");
                    String actTechPassed = rs.getString("act_tech_passed");
                    String actTechPassed1 = rs.getString("act_tech_passed");
                    String actService = rs.getString("act_service");
                    String actConsalting = rs.getString("act_consalting");
                    String actInsurance = rs.getString("act_insurance");
                    String actIntroduction = rs.getString("act_introduction");
                    String actPrice = rs.getString("act_price");

                    runOnUiThread(() -> {
                        TableRow row = new TableRow(SqlActivity.this);
                        row.addView(createTextView(idAct));
                        row.addView(createTextView(actAddress));
                        row.addView(createTextView(actAbonentFulname));
                        row.addView(createTextView(actPhone));
                        row.addView(createTextView(actDate));
                        row.addView(createImageTextButton(actImgBig, "View Image"));
                        row.addView(createImageTextButton(actImgBefore, "View Before"));
                        row.addView(createImageTextButton(actImgAfter, "View After"));
                        row.addView(createTextView(actCheckGStandart));
                        row.addView(createTextView(actCheckXDocYes));
                        row.addView(createTextView(actCheckGDocCompatible));
                        row.addView(createTextView(actCheckGDocChanged));
                        row.addView(createTextView(actTechPassed));
                        row.addView(createTextView(actTechPassed1));
                        row.addView(createTextView(actService));
                        row.addView(createTextView(actConsalting));
                        row.addView(createTextView(actInsurance));
                        row.addView(createTextView(actIntroduction));
                        row.addView(createTextView(actPrice));
                        row.addView(createImageTextButton(actSignature, "View Signature"));
                        tableLayout.addView(row);
                    });
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }




    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(5, 5, 5, 5);
        textView.setBackgroundResource(R.drawable.cell_border); // Optional: add a border to each cell
        return textView;
    }
    private TextView createImageTextButton(byte[] imageBytes, String buttonText) {
        TextView textButton = new TextView(this);
        textButton.setText(buttonText);
        textButton.setPadding(5, 5, 5, 5);
        textButton.setBackgroundResource(R.drawable.cell_border); // Optional: add a border to each cell

        if (imageBytes != null && imageBytes.length > 0) {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

            textButton.setOnClickListener(view -> showFullImage(bitmap));
        }

        return textButton;
    }

    private void showFullImage(Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Full Image");

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        builder.setView(imageView);

        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}