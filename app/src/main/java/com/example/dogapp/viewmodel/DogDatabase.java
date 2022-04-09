package com.example.dogapp.viewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dogapp.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1)
public abstract class DogDatabase extends RoomDatabase {
    public abstract DogDao dogDao();
    private static DogDatabase instance;

    public static DogDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    DogDatabase.class, "dogs").allowMainThreadQueries().build();
        }
        return instance;
    }
}
