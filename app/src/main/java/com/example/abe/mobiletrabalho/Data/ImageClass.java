package com.example.abe.mobiletrabalho.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;

@Entity(tableName = "imageclass")
public class ImageClass {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public long id; //PK

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image; // A imagem em si

    private String description; //Saber qual é a descrição dela (Leão, Macaco, feliz, etc)
    private String type; //Saber de qual classe ela vem

    public ImageClass(byte[] image, String description, String type){
        this.image = image;
        this.description = description;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
