package com.example.abe.mobiletrabalho.danger;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;
import com.example.abe.mobiletrabalho.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DangerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DangerAdapter dangerAdapter;
    private List<ImageClass> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_danger);
        imageDatabase databaseDang = imageDatabase.getDatabase(getApplicationContext());
        imageList = databaseDang.imageDao().getAllImagesByType("danger");

        if(imageList.size() == 0){
            this.populateDataBase();
            imageList = databaseDang.imageDao().getAllImagesByType("danger");
        }

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dangerAdapter = new DangerAdapter(imageList.size(), imageList, getApplicationContext(), getResources());
        recyclerView.setAdapter(dangerAdapter);
    }


    public void populateDataBase (){
        imageDatabase databaseMic = imageDatabase.getDatabase(getApplicationContext());
        databaseMic.imageDao().deleteAll();
        ImageClass insertedImage = new ImageClass("","","");

        insertedImage.setType("danger");
        insertedImage.setDescription("bola");
        insertedImage.setName("danger_image0");
        databaseMic.imageDao().addImage(insertedImage);

        insertedImage.setType("danger");
        insertedImage.setDescription("aranha");
        insertedImage.setName("danger_image1");
        databaseMic.imageDao().addImage(insertedImage);

        insertedImage.setType("danger");
        insertedImage.setDescription("flor");
        insertedImage.setName("danger_image2");
        databaseMic.imageDao().addImage(insertedImage);

        insertedImage.setType("danger");
        insertedImage.setDescription("escorpiao");
        insertedImage.setName("danger_image3");
        databaseMic.imageDao().addImage(insertedImage);

        insertedImage.setType("danger");
        insertedImage.setDescription("copo");
        insertedImage.setName("danger_image4");
        databaseMic.imageDao().addImage(insertedImage);

        insertedImage.setType("danger");
        insertedImage.setDescription("faca");
        insertedImage.setName("danger_image5");
        databaseMic.imageDao().addImage(insertedImage);
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
