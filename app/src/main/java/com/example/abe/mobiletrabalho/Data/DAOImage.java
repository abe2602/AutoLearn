package com.example.abe.mobiletrabalho.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAOImage {
    //Insere no banco
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addImage(ImageClass image);

    //Lista todas as imagens
    @Query("select * from imageclass")
    public List<ImageClass> getAllImages();

    //Lista todas as imagens a partir do tipo
    @Query("select * from imageclass where type = :type")
    public List<ImageClass> getAllImagesByType(String type);

    //Atualiza uma imagem o banco
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateImage(ImageClass image);

    //Deleta o banco todo
    @Query("delete from imageclass")
    void deleteAll();
}


