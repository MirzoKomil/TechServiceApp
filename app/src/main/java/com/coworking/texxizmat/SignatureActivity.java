package com.coworking.texxizmat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.signature.SignatureView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignatureActivity extends AppCompatActivity {

    private SignatureView signatureView;
    private Button btnSave, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signatureView = findViewById(R.id.signature_view);
        btnSave = findViewById(R.id.btn_save);
        btnClear = findViewById(R.id.btn_clear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = getSignatureBitmap();

                if (bitmap != null) {
                    try {
                        File file = saveBitmapToFile(bitmap);
                        if (file != null) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("signature", Uri.fromFile(file).toString());
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        } else {
                            Toast.makeText(SignatureActivity.this, "Error saving image", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SignatureActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignatureActivity.this, "Error capturing image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Bitmap getSignatureBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(signatureView.getWidth(), signatureView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT); // Set the canvas background to transparent
        signatureView.draw(canvas);
        return bitmap;
    }

    private File saveBitmapToFile(Bitmap bitmap) {
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
