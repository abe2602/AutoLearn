package com.example.abe.mobiletrabalho.map;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.Data.imageDatabase;
import com.example.abe.mobiletrabalho.MainActivity;
import com.example.abe.mobiletrabalho.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapActivity extends AppCompatActivity{
    private ImageView img0, img1, img2;
    private ConstraintLayout dragLayout, dropLayout0, dropLayout1, dropLayout2;
    boolean [] block;
    private int correctCont, curImage0, curImage1, curImage2, maxIndice;
    private Button nextButton;
    private List<ImageClass> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        imageDatabase database = imageDatabase.getDatabase(getApplicationContext());
        imageList = database.imageDao().getAllImagesByType("sequencia");

        if(imageList.size() == 0){
            this.populateDataBase();
            imageList = database.imageDao().getAllImagesByType("emotion");
        }

        maxIndice = imageList.size();
        curImage0 = 0;
        curImage1 = 1;
        curImage2 = 2;
        block = new boolean[3];


        /*Imagens*/
        nextButton = (Button) findViewById(R.id.nextButtonSeq);
        nextButton.setEnabled(false);
        correctCont = 0;

        final String str0 = "img_seq" + String.valueOf(curImage0);
        img0 = (ImageView) findViewById(R.id.imgMap0);
        img0.setTag(0);
        img0.setOnTouchListener(new TouchListener());
        img0.setImageDrawable(
                getResources().getDrawable(getResourceID(str0, "drawable", getApplicationContext())));
        block[0] = false;

        final String str1 = "img_seq" + String.valueOf(curImage1);
        img1 = (ImageView) findViewById(R.id.imgMap1);
        img1.setTag(1);
        img1.setOnTouchListener(new TouchListener());
        block[1] = false;
        img1.setImageDrawable(
                getResources().getDrawable(getResourceID(str1, "drawable", getApplicationContext())));

        final String str2 = "img_seq" + String.valueOf(curImage2);
        img2 = (ImageView) findViewById(R.id.imgMap2);
        img2.setTag(2);
        img2.setOnTouchListener(new TouchListener());
        block[2] = false;
        img2.setImageDrawable(
                getResources().getDrawable(getResourceID(str2, "drawable", getApplicationContext())));

        /*Layouts*/
        dragLayout = (ConstraintLayout ) findViewById(R.id.dragLayout);

        dropLayout0 = (ConstraintLayout ) findViewById(R.id.drop0);
        dropLayout0.setTag(0);
        dropLayout0.setOnDragListener(new DragListener());

        dropLayout1 = (ConstraintLayout ) findViewById(R.id.drop1);
        dropLayout1.setTag(1);
        dropLayout1.setOnDragListener(new DragListener());

        dropLayout2 = (ConstraintLayout ) findViewById(R.id.drop2);
        dropLayout2.setTag(2);
        dropLayout2.setOnDragListener(new DragListener());
    }

    public void nextOnClickSeq(View view) {
        if(curImage2 < maxIndice) {
            curImage2 += 3;
            curImage1 = curImage2 - 1;
            curImage0 = curImage1 - 1;
            nextButton.setEnabled(false);

            final String str0 = "img_seq" + String.valueOf(curImage0);
            img0.setImageDrawable(
                    getResources().getDrawable(getResourceID(str0, "drawable", getApplicationContext())));
            block[0] = false;
            dropLayout0.removeView(img0);
            dragLayout.addView(img0);

            final String str1 = "img_seq" + String.valueOf(curImage1);
            img1.setImageDrawable(
                    getResources().getDrawable(getResourceID(str1, "drawable", getApplicationContext())));
            block[2] = false;
            dropLayout1.removeView(img1);
            dragLayout.addView(img1);

            final String str2 = "img_seq" + String.valueOf(curImage2);
            img2.setImageDrawable(
                    getResources().getDrawable(getResourceID(str2, "drawable", getApplicationContext())));
            block[1] = false;
            dropLayout2.removeView(img2);
            dragLayout.addView(img2);
            correctCont = 0;

        }else{
            Toast.makeText(MapActivity.this, "Parabéns, você fez tudo certo!",
                    Toast.LENGTH_SHORT).show();

            Intent backToMenu = new Intent(this, MainActivity.class);
            backToMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(backToMenu);
        }
    }

    private class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder dsb = new View.DragShadowBuilder(view);
                view.startDrag(clipData, dsb, view, 0);
               // view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }
    private class DragListener implements View.OnDragListener {

        public boolean onDrag(View view, DragEvent dragEvent) {
            int dragAction = dragEvent.getAction();
            ImageView img = (ImageView) dragEvent.getLocalState();
            View dropaux = (View) dragEvent.getLocalState();
            View drop1 = dropaux;

            switch (dragAction) {
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                    break;
                case DragEvent.ACTION_DROP:
                    ConstraintLayout dropView = (ConstraintLayout) view;
                    Log.d("droppou", String.valueOf(img.getTag()) + " em  " + String.valueOf(dropView.getTag()));

                    if(img.getTag() == dropView.getTag() && !block[(int) img.getTag()]){
                        drop1.setVisibility(View.VISIBLE);

                        dragLayout.removeView(drop1);
                        ViewGroup droppedArea = ((ViewGroup)view);
                        droppedArea.addView(drop1);
                        block[(int) img.getTag()] = true;
                        correctCont++;

                        if(correctCont == 3){
                            nextButton.setEnabled(true);
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public void populateDataBase(){
        imageDatabase database = imageDatabase.getDatabase(getApplicationContext());

        database.imageDao().deleteAll();
        ImageClass insertedImage = new ImageClass("","","");

        insertedImage.setType("sequencia");
        insertedImage.setDescription("seq0");
        insertedImage.setName("img_seq0");
        database.imageDao().addImage(insertedImage);

        insertedImage.setType("sequencia");
        insertedImage.setDescription("seq0");
        insertedImage.setName("img_seq1");
        database.imageDao().addImage(insertedImage);

        insertedImage.setType("sequencia");
        insertedImage.setDescription("seq0");
        insertedImage.setName("img_seq2");
        database.imageDao().addImage(insertedImage);
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
