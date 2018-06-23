package com.example.abe.mobiletrabalho.emotions;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;
import com.example.abe.mobiletrabalho.R;

import java.util.List;
import java.util.Random;

/*A ideia aqui é usar as emoções de Divertida mente,
dentro dos botões.!!!*/
public class EmotionsActivity extends AppCompatActivity {
    private List<ImageClass> imageList;
    private ImageView imageViewEmotions;
    private int position;
    private Button happy, sad, nojo, raiva, medo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);

        happy = (Button) findViewById(R.id.happyButton);
        sad = (Button) findViewById(R.id.sadButton);
        nojo = (Button) findViewById(R.id.disgustingButton);
        raiva = (Button) findViewById(R.id.angryButton);
        medo = (Button) findViewById(R.id.afraidButton);

        imageDatabase database = imageDatabase.getDatabase(getApplicationContext());
        imageViewEmotions = (ImageView) findViewById(R.id.emotion_image_view);
        imageList = database.imageDao().getAllImagesByType("emotion");

        if(imageList.size() == 0){
            this.populateDataBase();
            imageList = database.imageDao().getAllImagesByType("emotion");
        }

        Random imagePosition = new Random();
        position = imagePosition.nextInt(imageList.size());
        final String str = "emotion_image" + String.valueOf(position);

        imageViewEmotions.setImageDrawable(
                getResources().getDrawable(getResourceID(str, "drawable", getApplicationContext())));
    }

    public void populateDataBase(){
       imageDatabase database = imageDatabase.getDatabase(getApplicationContext());

       database.imageDao().deleteAll();
       ImageClass insertedImage = new ImageClass("","","");

       insertedImage.setType("emotion");
       insertedImage.setDescription("alegria");
       insertedImage.setName("emotion_image0");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("alegria");
       insertedImage.setName("emotion_image1");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("alegria");
       insertedImage.setName("emotion_image2");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("medo");
       insertedImage.setName("emotion_image3");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("medo");
       insertedImage.setName("emotion_image4");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("medo");
       insertedImage.setName("emotion_image5");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("nojo");
       insertedImage.setName("emotion_image6");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("nojo");
       insertedImage.setName("emotion_image7");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("nojo");
       insertedImage.setName("emotion_image8");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("raiva");
       insertedImage.setName("emotion_image9");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("raiva");
       insertedImage.setName("emotion_image10");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("raiva");
       insertedImage.setName("emotion_image11");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("tristeza");
       insertedImage.setName("emotion_image12");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("tristeza");
       insertedImage.setName("emotion_image13");
       database.imageDao().addImage(insertedImage);

       insertedImage.setType("emotion");
       insertedImage.setDescription("tristeza");
       insertedImage.setName("emotion_image14");
       database.imageDao().addImage(insertedImage);
    }

    public void changeImage(){
        Random imagePosition = new Random();
        position = imagePosition.nextInt(imageList.size());
        final String str = "emotion_image" + String.valueOf(position);

        imageViewEmotions.setImageDrawable(
                getResources().getDrawable(getResourceID(str, "drawable", getApplicationContext())));

        happy.setEnabled(true);
        sad.setEnabled(true);
        nojo.setEnabled(true);
        medo.setEnabled(true);
        raiva.setEnabled(true);
    }

    public void checkRaiva(View view) {
        if(imageList.get(position).getDescription().equals("raiva")){
            Log.d("Acertou", "raiva");
            changeImage();
        }else{
            Toast.makeText(EmotionsActivity.this, "Você errou, tente novamente!",
                    Toast.LENGTH_SHORT).show();

            raiva.setEnabled(false);
        }
    }

    public void checkTristeza(View view) {
        if(imageList.get(position).getDescription().equals("tristeza")){
         Log.d("Acertou", "tristeza");
         changeImage();
        }else{
            Toast.makeText(EmotionsActivity.this, "Você errou, tente novamente!",
                    Toast.LENGTH_SHORT).show();

            sad.setEnabled(false);
        }
    }

    public void checkFelicidade(View view) {
        if(imageList.get(position).getDescription().equals("alegria")){
            Log.d("Acertou", "felicidade");
            changeImage();
        }else{
            Toast.makeText(EmotionsActivity.this, "Você errou, tente novamente!",
                    Toast.LENGTH_SHORT).show();

            happy.setEnabled(false);
        }
    }

    public void checkNojo(View view) {
        if(imageList.get(position).getDescription().equals("nojo")){
            Log.d("Acertou", "nojo");
            changeImage();
        }else{
            Toast.makeText(EmotionsActivity.this, "Você errou, tente novamente!",
                    Toast.LENGTH_SHORT).show();

            nojo.setEnabled(false);
        }
    }

    public void checkMedo(View view) {
        if(imageList.get(position).getDescription().equals("medo")){
            Log.d("Acertou", "medo");
            changeImage();
        }else{
            Toast.makeText(EmotionsActivity.this, "Você errou, tente novamente!",
                    Toast.LENGTH_SHORT).show();

            medo.setEnabled(false);
        }
    }

    /*Gero o ID das imagens a partir do número randômico.*/
    protected final static int getResourceID (final String resName, final String resType, final Context ctx)
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
