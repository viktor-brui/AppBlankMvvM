package com.example.appblank.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskModelDao {

    @Query("SELECT * FROM TaskModel")
    LiveData<List<TaskModel>> getAllTasks();

    @Insert(onConflict = REPLACE)
    void insertTask(TaskModel taskModel);

    @Delete
    void deleteTask(TaskModel taskModel);
}
