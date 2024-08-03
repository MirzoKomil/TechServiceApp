package com.coworking.texxizmat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coworking.tehhizmat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class ActsActivity extends AppCompatActivity {

    public CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    public TextInputLayout textInputLayout, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5, textInputLayout6;
    public EditText textInputEditText, textInputEditText2, textInputEditText3, textInputEditText4, textInputEditText5, textInputEditText6, phone, accountNum, fullname, address;
    public TextView savebtn, cancelbtn;
    public TextInputEditText etDate, reestr;
    public FloatingActionButton button, button2, button3;
    public ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acts);

        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        phone = findViewById(R.id.et_phoneNum);
        address = findViewById(R.id.et_address);
        //accountNum = findViewById(R.id.et_accountNumber);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageView1 = findViewById(R.id.foto_big);
        imageView2 = findViewById(R.id.foto_big2);
        imageView3 = findViewById(R.id.foto_big3);
        etDate = findViewById(R.id.etDate);
        reestr = findViewById(R.id.etReestr);
        textInputLayout = findViewById(R.id.textInputLayout5);
        textInputLayout2 = findViewById(R.id.textInputLayout6);
        textInputLayout3 = findViewById(R.id.textInputLayout7);
        textInputLayout4 = findViewById(R.id.textInputLayout8);
        textInputLayout5 = findViewById(R.id.textInputLayout9);
        textInputLayout6 = findViewById(R.id.textInputLayout10);
        textInputEditText = findViewById(R.id.editText);
        textInputEditText2 = findViewById(R.id.editText2);
        textInputEditText3 = findViewById(R.id.editText3);
        textInputEditText4 = findViewById(R.id.editText4);
        textInputEditText5 = findViewById(R.id.editText5);
        textInputEditText6 = findViewById(R.id.editText6);
        fullname = findViewById(R.id.fullName);
        savebtn = findViewById(R.id.save_btn);
        cancelbtn = findViewById(R.id.bekor_btn);

        textInputLayout.setVisibility(View.GONE);
        textInputLayout2.setVisibility(View.GONE);
        textInputLayout3.setVisibility(View.GONE);
        textInputLayout4.setVisibility(View.GONE);
        textInputLayout5.setVisibility(View.GONE);
        textInputLayout6.setVisibility(View.GONE);
    }

    private void setListeners() {
        etDate.setOnClickListener(v -> showDatePickerDialog());

        savebtn.setOnClickListener(v -> {
            if (validateInputs()) {
                prepareAndSendIntent();
            }
        });

        cancelbtn.setOnClickListener(v -> {
            Intent sendIntent = new Intent(ActsActivity.this, MainMenuActivity.class);
            startActivity(sendIntent);
        });

        setCheckboxListeners();
        setCameraButtonsListeners();
    }

    private void setCameraButtonsListeners() {
        button.setOnClickListener(v -> openCamera(22));
        button2.setOnClickListener(v -> openCamera(23));
        button3.setOnClickListener(v -> openCamera(24));
    }

    private void setCheckboxListeners() {
        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE));
        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout2.setVisibility(isChecked ? View.VISIBLE : View.GONE));
        checkBox5.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout3.setVisibility(isChecked ? View.VISIBLE : View.GONE));
        checkBox6.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout4.setVisibility(isChecked ? View.VISIBLE : View.GONE));
        checkBox7.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout5.setVisibility(isChecked ? View.VISIBLE : View.GONE));
        checkBox8.setOnCheckedChangeListener((buttonView, isChecked) -> textInputLayout6.setVisibility(isChecked ? View.VISIBLE : View.GONE));
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ActsActivity.this,
                (view, year1, month1, dayOfMonth) -> {
                    String dayStr = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                    String monthStr = (month1 < 9) ? "0" + (month1 + 1) : String.valueOf(month1 + 1);
                    etDate.setText(dayStr + "." + monthStr + "." + year1);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void openCamera(int requestCode) {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if (requestCode == 22 && resultCode == RESULT_OK && photo != null) {
                imageView1.setImageBitmap(photo);
            } else if (requestCode == 23 && resultCode == RESULT_OK && photo != null) {
                imageView2.setImageBitmap(photo);
            } else if (requestCode == 24 && resultCode == RESULT_OK && photo != null) {
                imageView3.setImageBitmap(photo);
            } else {
                Toast.makeText(this, "No image captured", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No image captured", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs() {
        if (imageView1.getDrawable() == null || imageView2.getDrawable() == null || imageView3.getDrawable() == null) {
            Toast.makeText(this, "All images must be captured", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void prepareAndSendIntent() {
        // Ensure drawing cache is enabled for all image views
        imageView1.setDrawingCacheEnabled(true);
        imageView2.setDrawingCacheEnabled(true);
        imageView3.setDrawingCacheEnabled(true);

        Bitmap bitmap1 = imageView1.getDrawingCache();
        Bitmap bitmap2 = imageView2.getDrawingCache();
        Bitmap bitmap3 = imageView3.getDrawingCache();

        try {
            if (bitmap1 != null && bitmap2 != null && bitmap3 != null) {
                // Save bitmaps to files
                File file1 = saveBitmapToFile(bitmap1);
                File file2 = saveBitmapToFile(bitmap2);
                File file3 = saveBitmapToFile(bitmap3);

                // Pass file URIs to the next activity
                Intent intent = new Intent(ActsActivity.this, SuccesActivity.class);
                intent.putExtra("image1_uri", Uri.fromFile(file1).toString());
                intent.putExtra("image2_uri", Uri.fromFile(file2).toString());
                intent.putExtra("image3_uri", Uri.fromFile(file3).toString());

                // Pass other data via intent extras
                intent.putExtra("address", address.getText().toString());
                //intent.putExtra("accountNum", accountNum.getText().toString());
                intent.putExtra("fullname", fullname.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("date", etDate.getText().toString());
                intent.putExtra("reestr", reestr.getText().toString());
                intent.putExtra("checkbox1", checkBox1.isChecked());
                intent.putExtra("checkbox2", checkBox2.isChecked());
                intent.putExtra("checkbox3", checkBox3.isChecked());
                intent.putExtra("checkbox4", checkBox4.isChecked());
                intent.putExtra("checkbox5", checkBox5.isChecked());
                intent.putExtra("checkbox6", checkBox6.isChecked());
                intent.putExtra("checkbox7", checkBox7.isChecked());
                intent.putExtra("checkbox8", checkBox8.isChecked());
                intent.putExtra("checkbox9", checkBox9.isChecked());
                intent.putExtra("checkbox10", checkBox10.isChecked());
                intent.putExtra("edittext1", textInputEditText.getText().toString());
                intent.putExtra("edittext2", textInputEditText2.getText().toString());
                intent.putExtra("edittext3", textInputEditText3.getText().toString());
                intent.putExtra("edittext4", textInputEditText4.getText().toString());
                intent.putExtra("edittext5", textInputEditText5.getText().toString());
                intent.putExtra("edittext6", textInputEditText6.getText().toString());

                startActivity(intent);

                // Clear drawing caches
                imageView1.setDrawingCacheEnabled(false);
                imageView2.setDrawingCacheEnabled(false);
                imageView3.setDrawingCacheEnabled(false);
            } else {
                Toast.makeText(this, "Error capturing images", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
