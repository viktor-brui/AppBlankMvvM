package com.example.appblank.tasklist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.appblank.R;
import com.example.appblank.database.TaskModel;
import com.example.appblank.taskadd.TaskAddActivity;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity implements View.OnLongClickListener {

    private TaskViewModel taskViewModel;
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        FloatingActionButton fabAddTask = findViewById(R.id.fab_add);

        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTaskIntent = new Intent(TaskActivity.this, TaskAddActivity.class);
                startActivity(addTaskIntent);
            }
        });

        taskRecyclerView = findViewById(R.id.rv_list);
        taskAdapter = new TaskAdapter(new ArrayList<TaskModel>(),this);
        layoutManager = new LinearLayoutManager(this);
        taskRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration deviderItemDecoration = new DividerItemDecoration(taskRecyclerView.getContext(),
                layoutManager.getOrientation());
        taskRecyclerView.setAdapter(taskAdapter);

        taskViewModel = ViewModelProvider.of(this).get(TaskViewModel.class);
        taskViewModel.getTaskList().observe(TaskActivity.this, (taskModels) -> {
            taskAdapter.addTask(taskModels);});
    }

    @Override
    public boolean onLongClick(View v) {
        TaskModel taskModel = (TaskModel) taskRecyclerView.getTag();
        taskViewModel.deleteTask(taskModel);
        Toast.makeText(this, taskModel.getTaskTitle()+" - deleted!", Toast.LENGTH_SHORT)
                .show();
        return true;
    }
}
