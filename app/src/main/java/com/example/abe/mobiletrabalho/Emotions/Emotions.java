package com.example.abe.mobiletrabalho.Emotions;

import android.widget.ImageView;

import com.example.abe.mobiletrabalho.Data.imageDatabase;

public class Emotions {
    private ImageView imageFeeling;
    private String correctFeeling;
    private boolean correct;
    private imageDatabase database;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Emotions(ImageView imageFeeling, String correctFeeling){
        this.imageFeeling = imageFeeling;
        this.correctFeeling = correctFeeling;
    }

    public ImageView getImageFeeling() {
        return imageFeeling;
    }

    public void setImageFeeling(ImageView imageFeeling) {
        this.imageFeeling = imageFeeling;
    }

    public String getCorrectFeeling() {
        return correctFeeling;
    }

    public void setCorrectFeeling(String correctFeeling) {
        this.correctFeeling = correctFeeling;
    }
}
