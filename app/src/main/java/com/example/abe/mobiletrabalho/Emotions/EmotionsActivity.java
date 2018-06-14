package com.example.abe.mobiletrabalho.Emotions;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;
import com.example.abe.mobiletrabalho.R;

import java.util.List;
import java.util.Random;

/*A ideia aqui é usar as emoções de Divertida mente,
dentro dos botões.!!!*/
public class EmotionsActivity extends AppCompatActivity {
    private imageDatabase database;
    private ImageView imageViewEmotions;
    private String rightEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);
        imageViewEmotions = (ImageView)findViewById(R.id.emotion_image_view);
        database = imageDatabase.getDatabase(getApplicationContext());

        Random imagePosition = new Random();
        int position = imagePosition.nextInt(15);
        List<ImageClass> imageList = database.imageDao().getAllImagesByType("emotion");

        final String str = "emotion_image"+position;

        Log.d("Emotion", imageList.get(position).getDescription());

        imageViewEmotions.setImageDrawable(
                        getResources().getDrawable(getResourceID(str, "drawable", getApplicationContext())));

    }

    public void checkRaiva(View view) {
    }

    public void checkTristeza(View view) {
    }

    public void checkFelicidade(View view) {
    }

    public void checkNojo(View view) {
    }

    public void checkMedo(View view) {
    }

    /*Gero o ID das imagens a partir do número randômico.*/
    protected final static int getResourceID
            (final String resName, final String resType, final Context ctx)
    {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }
}
