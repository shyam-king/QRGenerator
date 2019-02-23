package com.github.shyamking.qrgenerator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    TextView inputText;
    Button submitButton, shareButton;
    ImageView qrImage;
    QRGEncoder qrEncoder;
    Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        submitButton = findViewById(R.id.submitButton);
        shareButton = findViewById(R.id.shareButton);
        qrImage = findViewById(R.id.qrImage);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        qrEncoder = new QRGEncoder(inputText.getText().toString(), null, QRGContents.Type.TEXT, 200);
                        try {
                            image = qrEncoder.encodeAsBitmap();
                            qrImage.setImageBitmap(image);
                        } catch (WriterException e)
                        {
                            Log.v("QR Generator", e.toString());
                        }
                    }
                });
            }
        });
    }
}
