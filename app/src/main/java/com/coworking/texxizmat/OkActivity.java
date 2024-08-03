package com.coworking.texxizmat;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.coworking.tehhizmat.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OkActivity extends AppCompatActivity {
    TextView contNum, contName, contInfo, contServis, contPrice, contDate, contrInfoDate;
    ImageView contSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ok);

        contNum = findViewById(R.id.contractNum);
        contName = findViewById(R.id.contractName);
        contInfo = findViewById(R.id.contractInfo);
        //contServis = findViewById(R.id.contractServis);
        contPrice = findViewById(R.id.contractPrice);
        contDate = findViewById(R.id.contractDate);
        contSignature = findViewById(R.id.contractSignature);
        contrInfoDate = findViewById(R.id.contrInfoDate);



        try{

            //String num = contNum.getText().toString();
            String name = getIntent().getStringExtra("name");
            String date = getIntent().getStringExtra("date");
            String price = getIntent().getStringExtra("price");

            String signImage = getIntent().getStringExtra("sign");

            setImageViewFromUri(contSignature, signImage);
            setTextView(contName, name);
            setTextView(contInfo, name);
            setTextView(contDate, formatDateForDisplay(date));
            setTextView(contPrice, price);
            setTextView(contrInfoDate, formatDateForDisplay(date));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OkActivity", "Error retrieving data from intent: " + e.getMessage());
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

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
}