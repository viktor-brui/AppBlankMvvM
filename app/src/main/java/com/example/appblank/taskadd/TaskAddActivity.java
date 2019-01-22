package com.example.appblank.taskadd;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appblank.R;
import com.example.appblank.database.TaskModel;

public class TaskAddActivity extends AppCompatActivity {

    private EditText inputTaskTitle;
    private EditText inputTaskDescription;
    private TaskAddViewModel taskAddViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        inputTaskTitle = findViewById(R.id.task_title);
        inputTaskDescription = findViewById(R.id.task_desk);

        taskAddViewModel = new ViewModelProvider.of(this).get(TaskAddViewModel.class);

        FloatingActionButton fabDoneTask = findViewById(R.id.fab_done);
        fabDoneTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String taskTitle = inputTaskTitle.getText().toString();
                String taskDescription = inputTaskDescription.getText().toString();

                if (taskTitle.isEmpty() || taskDescription.isEmpty()) {
                    Toast.makeText(TaskAddActivity.this, R.string.input_error, Toast.LENGTH_LONG).show();
                } else {
                    taskAddViewModel.addTask(new TaskModel(inputTaskTitle.getText().toString(),
                            inputTaskDescription.getText().toString()
                    ));
                    finish();
                }
            }
        });
    }
}