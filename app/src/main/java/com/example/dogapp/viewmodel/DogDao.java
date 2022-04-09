package com.example.dogapp.viewmodel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dogapp.model.DogBreed;

import java.util.List;

@Dao
public interface DogDao {
    @Query("select * from dogbreed")
    List<DogBreed> getAllDog();

    @Query("select * from dogbreed where name like :s order by name")
    List<DogBreed> findByName(String s);

    @Insert
    void insertAll(DogBreed... dogBreeds);

    @Delete
    void delete(DogBreed dog);
}
