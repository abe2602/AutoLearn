package com.example.abe.mobiletrabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.danger.DangerActivity;
import com.example.abe.mobiletrabalho.Emotion.EmotionActivity;
import com.example.abe.mobiletrabalho.order.OrderActivity;
import com.example.abe.mobiletrabalho.mic.MicActivity;
import com.example.abe.mobiletrabalho.vibra.VibraActivity;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, NotifyService.class));

        TextView userNameTextView = (TextView)findViewById(R.id.textViewUser);
        userNameTextView.setText("Usuário: " + ParseUser.getCurrentUser().getUsername());
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
        intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void emotionOnClick(View view) {
        intent = new Intent(MainActivity.this, EmotionActivity.class);
        startActivity(intent);
    }

    public void dangerOnClick(View view) {
        intent = new Intent(MainActivity.this, DangerActivity.class);
        startActivity(intent);
    }

}
