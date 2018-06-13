package com.example.abe.mobiletrabalho;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageClass image;
    private imageDatabase database;
    TextView auxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = imageDatabase.getDatabase(getApplicationContext());
        auxt = (TextView) findViewById(R.id.aux);
    }
}
