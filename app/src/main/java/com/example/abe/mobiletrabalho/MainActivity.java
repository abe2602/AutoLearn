package com.example.abe.mobiletrabalho;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.Camera.CameraActivity;
import com.example.abe.mobiletrabalho.Danger.DangerActivity;
import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;
import com.example.abe.mobiletrabalho.Emotions.EmotionsActivity;
import com.example.abe.mobiletrabalho.Map.MapActivity;
import com.example.abe.mobiletrabalho.Mic.MicActivity;
import com.example.abe.mobiletrabalho.Vibra.VibraActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private imageDatabase database;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = imageDatabase.getDatabase(getApplicationContext());
        database.imageDao().deleteAll();

        String rightEmotion = null;
        int size = 15;
        database.imageDao().deleteAll();
        for(int i = 0; i < size; i++){

            if(i <= 2){
                rightEmotion = "alegria";
            }else if(i <= 5){
                rightEmotion = "medo";
            }else if(i <= 8){
                rightEmotion = "nojo";
            }else if(i <= 11){
                rightEmotion = "raiva";
            }else if(i <= 14){
                rightEmotion = "tristeza";
            }
            ImageClass imageClass = new ImageClass("emotion_image"+ String.valueOf(i), rightEmotion, "emotion");
            database.imageDao().addImage(imageClass);
        }

        //

        //database.imageDao().addImage(imageAdd);

    }

    public void vibraOnClick(View view) {
        intent = new Intent(MainActivity.this, VibraActivity.class);
        startActivity(intent);
    }

    public void micOnClick(View view) {
        intent = new Intent(MainActivity.this, MicActivity.class);
        startActivity(intent);
    }

    public void mapOnClick(View view) {
        intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void emotionOnClick(View view) {
        intent = new Intent(MainActivity.this, EmotionsActivity.class);
        startActivity(intent);
    }

    public void dangerOnClick(View view) {
        intent = new Intent(MainActivity.this, DangerActivity.class);
        startActivity(intent);
    }

    public void cameraOnClick(View view) {
        intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }
}
