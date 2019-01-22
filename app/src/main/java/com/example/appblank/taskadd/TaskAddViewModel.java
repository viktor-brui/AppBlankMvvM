package com.example.appblank.taskadd;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.appblank.database.TaskDatabase;
import com.example.appblank.database.TaskModel;

public class TaskAddViewModel extends AndroidViewModel {

    private TaskDatabase taskDatabase;

    public TaskAddViewModel(@NonNull Application application) {
        super(application);
        taskDatabase = TaskDatabase.getDatabase(this.getApplication());
    }

    public void addTask(final TaskModel taskModel){
        new addAsyncTask(taskDatabase).execute(taskModel);
    }

    private static class addAsyncTask extends AsyncTask<TaskModel, Void, Void>{
        private TaskDatabase db;

        addAsyncTask(TaskDatabase taskDatabase){db = taskDatabase;}

        @Override
        protected Void doInBackground(TaskModel... taskModels) {
            db.taskModelDao().insertTask(taskModels[0]);
            return null;
        }
    }
}
