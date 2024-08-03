package com.coworking.texxizmat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.coworking.tehhizmat.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SuccesActivity extends AppCompatActivity {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Connection con;
    private static final int REQUEST_SMS_PERMISSION = 100;
    private static final int REQUEST_SIGNATURE = 101;
    private ImageView image1, image2, image3, signImage;
    private TextView address, accountNum, fullname, phoneNum, date, reestr;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    private TextView saveBtn, editBtn, signature, checkedText1, checkedText3, checkedText4, checkedText5, checkedText6, checkedText7;
    private EditText price;
    int contractNum;

    private static final String DB = "texxizma_tex_x_db";
    private static final String IP = "83.69.139.250";
    private static final String PORT = "3306";
    private static final String USER = "texxizma_tex_x_user";
    private static final String PASSWORD = "mrzohid90.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);

        address = findViewById(R.id.tv_address);
        //accountNum = findViewById(R.id.tv_accountNumber);
        fullname = findViewById(R.id.tv_fullName);
        phoneNum = findViewById(R.id.tv_phoneNum);
        date = findViewById(R.id.tvDate);

        image1 = findViewById(R.id.sa_foto_big);
        image2 = findViewById(R.id.sa_foto_big2);
        image3 = findViewById(R.id.sa_foto_big3);

        reestr = findViewById(R.id.tvReestr);

        checkBox1 = findViewById(R.id.sa_checkBox1);
        checkedText1 = findViewById(R.id.sa_checkedText1);

        checkBox2 = findViewById(R.id.sa_checkBox2);

        checkBox3 = findViewById(R.id.sa_checkBox3);
        checkedText3 = findViewById(R.id.sa_checkedText2);

        checkBox4 = findViewById(R.id.sa_checkBox4);

        checkBox5 = findViewById(R.id.sa_checkBox5);
        checkedText4 = findViewById(R.id.sa_checkedText3);

        checkBox6 = findViewById(R.id.sa_checkBox6);
        checkedText5 = findViewById(R.id.sa_checkedText4);

        checkBox7 = findViewById(R.id.sa_checkBox7);
        checkedText6 = findViewById(R.id.sa_checkedText5);

        checkBox8 = findViewById(R.id.sa_checkBox8);
        checkedText7 = findViewById(R.id.sa_checkedText6);

        checkBox9 = findViewById(R.id.sa_checkBox9);

        checkBox10 = findViewById(R.id.sa_checkBox10);

        price = findViewById(R.id.et_price);
        signImage = findViewById(R.id.signature_image_view);

        signature = findViewById(R.id.elektron_imzo);
        saveBtn = findViewById(R.id.sa_save_btn);
        editBtn = findViewById(R.id.sa_cancel_btn);

        // Retrieve the URI strings from the intent
        try {
            String uriString1 = getIntent().getStringExtra("image1_uri");
            String uriString2 = getIntent().getStringExtra("image2_uri");
            String uriString3 = getIntent().getStringExtra("image3_uri");

            setImageViewFromUri(image1, uriString1);
            setImageViewFromUri(image2, uriString2);
            setImageViewFromUri(image3, uriString3);

            // Retrieve other data from intent
            String addressText = getIntent().getStringExtra("address");
            //String accountNumText = getIntent().getStringExtra("accountNum");
            String fullnameText = getIntent().getStringExtra("fullname");
            String phoneNumText = getIntent().getStringExtra("phone");
            String dateText = getIntent().getStringExtra("date");
//            String reestrText = getIntent().getStringExtra("reestr");
            String editText1 = getIntent().getStringExtra("edittext1");
            String editText2 = getIntent().getStringExtra("edittext2");
            String editText3 = getIntent().getStringExtra("edittext3");
            String editText4 = getIntent().getStringExtra("edittext4");
            String editText5 = getIntent().getStringExtra("edittext5");
            String editText6 = getIntent().getStringExtra("edittext6");

            // Set text to TextViews
            setTextView(address, addressText);
            //setTextView(accountNum, accountNumText);
            setTextView(fullname, fullnameText);
            setTextView(phoneNum, phoneNumText);
            setTextView(date, dateText);
//            setTextView(reestr, reestrText);
            setTextView(checkedText1, editText1);
            setTextView(checkedText3, editText2);
            setTextView(checkedText4, editText3);
            setTextView(checkedText5, editText4);
            setTextView(checkedText6, editText5);
            setTextView(checkedText7, editText6);

            // Retrieve checkbox states
            checkBox1.setChecked(getIntent().getBooleanExtra("checkbox1", false));
            checkBox2.setChecked(getIntent().getBooleanExtra("checkbox2", false));
            checkBox3.setChecked(getIntent().getBooleanExtra("checkbox3", false));
            checkBox4.setChecked(getIntent().getBooleanExtra("checkbox4", false));
            checkBox5.setChecked(getIntent().getBooleanExtra("checkbox5", false));
            checkBox6.setChecked(getIntent().getBooleanExtra("checkbox6", false));
            checkBox7.setChecked(getIntent().getBooleanExtra("checkbox7", false));
            checkBox8.setChecked(getIntent().getBooleanExtra("checkbox8", false));
            checkBox9.setChecked(getIntent().getBooleanExtra("checkbox9", false));
            checkBox10.setChecked(getIntent().getBooleanExtra("checkbox10", false));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SuccesActivity", "Error retrieving data from intent: " + e.getMessage());
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
                sendSMS();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              onBackPressed();
            }
        });
        signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccesActivity.this, SignatureActivity.class);
                startActivityForResult(intent, REQUEST_SIGNATURE);
            }
        });

    }

    private void setImageViewFromUri(ImageView imageView, String uriString) {
        if (uriString != null) {
            Uri uri = Uri.parse(uriString);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("SuccesActivity", "Error loading image from URI: " + e.getMessage());
            }
        } else {
            Log.e("SuccesActivity", "Null URI string for image");
        }
    }

    private void setTextView(TextView textView, String text) {
        if (text != null) {
            textView.setText(text);
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private void registerUser(View view) {
        String address_act = address.getText().toString();
        //String accountNum_act = accountNum.getText().toString();
        String fullname_act = fullname.getText().toString();
        String phoneNum_act = phoneNum.getText().toString();
        String date_act = convertToDate(date.getText().toString());

        Bitmap bitmap1 = ((BitmapDrawable) image1.getDrawable()).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) image2.getDrawable()).getBitmap();
        Bitmap bitmap3 = ((BitmapDrawable) image3.getDrawable()).getBitmap();
        Bitmap signBitmap = ((BitmapDrawable) signImage.getDrawable()).getBitmap();

        byte[] image1_act = convertBitmapToByteArray(bitmap1);
        byte[] image2_act = convertBitmapToByteArray(bitmap2);
        byte[] image3_act = convertBitmapToByteArray(bitmap3);
        byte[] signImage_act = convertBitmapToByteArray(signBitmap);

        String checkedText1_act = checkedText1.getText().toString();
        String checkBox2_act = checkBox2.getText().toString();
        String checkedText3_act = checkedText3.getText().toString();
        String checkBox4_act = checkBox4.getText().toString();
        String checkedText4_act = checkedText4.getText().toString();
        String checkedText5_act = checkedText5.getText().toString();
        String checkedText6_act = checkedText6.getText().toString();
        String checkedText7_act = checkedText7.getText().toString();
        String checkBox9_act = checkBox9.getText().toString();
        String checkBox10_act = checkBox10.getText().toString();
        String price_act = price.getText().toString();

        if (address_act.isEmpty() || /*accountNum_act.isEmpty()*/ fullname_act.isEmpty() || phoneNum_act.isEmpty() ||
                date_act.isEmpty() || image1_act == null || image2_act == null || image3_act == null || checkedText1_act.isEmpty()
                || checkBox2_act.isEmpty() || checkedText3_act.isEmpty() || checkBox4_act.isEmpty() || checkedText4_act.isEmpty() || checkedText5_act.isEmpty()
                || checkedText6_act.isEmpty() || checkedText7_act.isEmpty() || checkBox9_act.isEmpty()
                || checkBox10_act.isEmpty() || price_act.isEmpty() || signImage_act == null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            try {
                con = CONN();
                String query = "INSERT INTO act_table (" +
                        "act_address, act_abonent_fulname, act_phone, act_date, act_img_big, act_img_before, act_img_after," +
                        "act_check_g_standart, act_check_x_doc_yes, act_check_g_doc_compatible, act_check_g_doc_changed, act_tech_passed, act_profi_service," +
                        "act_service, act_consalting, act_insurance, act_introduction, act_price, act_signature) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, address_act);
                //stmt.setInt(2, Integer.parseInt(accountNum_act));
                stmt.setString(2, fullname_act);
                stmt.setString(3, phoneNum_act);
                stmt.setString(4, date_act);
                stmt.setBytes(5, image1_act);
                stmt.setBytes(6, image2_act);
                stmt.setBytes(7, image3_act);
                stmt.setString(8, checkedText1_act);
                stmt.setString(9, checkBox2_act);
                stmt.setString(10, checkedText3_act);
                stmt.setString(11, checkBox4_act);
                stmt.setString(12, checkedText4_act);
                stmt.setString(13, checkedText5_act);
                stmt.setString(14, checkedText6_act);
                stmt.setString(15, checkedText7_act);
                stmt.setString(16, checkBox9_act);
                stmt.setString(17, checkBox10_act);
                stmt.setInt(18, Integer.parseInt(price_act));
                stmt.setBytes(19, signImage_act);

                int rowsInserted = stmt.executeUpdate();

                runOnUiThread(() -> {
                    if (rowsInserted > 0) {
                        Toast.makeText(SuccesActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SuccesActivity.this, OkActivity.class);
                        intent.putExtra("name", fullname_act);
                        intent.putExtra("date", date_act);
                        //intent.putExtra("phone", phoneNum_act);
                        intent.putExtra("price", price_act);
                        //intent.putExtra("id", fullname_act);

                        if (signBitmap != null){
                            File file = saveBitmapToFile(signBitmap);

                            intent.putExtra("sign", Uri.fromFile(file).toString());
                        }
                        //'intent.putExtra("name", fullname_act);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(SuccesActivity.this, "Failed to register user.", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (SQLException e) {
                runOnUiThread(() -> Toast.makeText(SuccesActivity.this, "Database error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SIGNATURE && resultCode == RESULT_OK) {
            String uriString = data.getStringExtra("signature");
            setImageViewFromUri(signImage, uriString);
        }
    }

    private void sendSMS() {
        String phone = phoneNum.getText().toString();
        String adminPhone = "999940048";
        String admin = "995304435";
        String admin2 = "971650048";
        String abonent = fullname.getText().toString();
        String payment = price.getText().toString();
        MainMenuActivity mainMenuActivity = new MainMenuActivity();
        String message = "Sizning xonadoningizda ko'rsatilgan xizmatlar uchun to'lov " + payment + " so'm tashkil etadi." +
                "Xizmat ko'rsatuvchi: " + mainMenuActivity.name;
        String mesSugurta = "O'zbek Invest sug'urta kompaniyasi tomonidan \"Xavfsiz Xonadon\" uy-joylarni yong'indan sug'urtalash" +
                "Sug'urta qoplamasi: 50 000 000 so'm";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
        } else {
            if (!phone.isEmpty() && !message.isEmpty()) {
                SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage(phone, null, message, null, null);
                smsManager.sendTextMessage(adminPhone, null, message, null, null);
                smsManager.sendTextMessage(admin, null, message, null, null);
                smsManager.sendTextMessage(admin2, null, message, null, null);

                if(checkBox1.isChecked()){
                    smsManager.sendTextMessage(phone, null, message + " Gaz jihozlari standartga javob beradi", null, null);
                } else {
                    smsManager.sendTextMessage(phone, null, message + " Gaz jihozlari standartga javob bermaydi", null, null);
                }

                if (checkBox7.isChecked()){
                    smsManager.sendTextMessage(phone, null, mesSugurta, null, null);
                } else {
                    smsManager.sendTextMessage(phone, null, "Xonadon sug'urtalanmagan", null, null);

                }
                Toast.makeText(this, "Muvaffaqiyatli sms jo'nadi", Toast.LENGTH_SHORT).show();
            } else if (phone.isEmpty()) {
                Toast.makeText(this, "Telefon raqamini kiriting! ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMSga Ruxsat berildi!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS Ruxsati taqiqlandi!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String convertToDate(String dateStr) {
        try {
            SimpleDateFormat fromUser = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fromUser.parse(dateStr);
            return myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to convert a date from YYYY-MM-DD to DD.MM.YYYY
    private String formatDateForDisplay(String dateStr) {
        try {
            SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = fromUser.parse(dateStr);
            return myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File saveBitmapToFile(Bitmap bitmap) {
        // Create a file to save the bitmap
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, "image_" + System.currentTimeMillis() + ".png");

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
