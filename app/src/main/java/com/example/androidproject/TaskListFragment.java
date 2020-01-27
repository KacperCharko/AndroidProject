package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidproject.database.Task;
import com.example.androidproject.database.TaskViewModel;

import java.sql.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class    TaskListFragment extends Fragment {



    private TaskViewModel taskViewModel;
    private static final String KEY_SHOW_SUBTITLE = "keyShowSubtitle";
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_ACTIVITY_REQUEST_CODE = 2;
    private RecyclerView recyclerView;

    private boolean subtitleVisible;
    private Task editTask = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



        if (savedInstanceState != null) {
            subtitleVisible = savedInstanceState.getBoolean(KEY_SHOW_SUBTITLE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        final TaskAdapter adapter = new TaskAdapter();
        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.findAll().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                adapter.setTasks(tasks);
            }

        });

        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        updateView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task_menu, menu);


    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_task:
                Task task= new Task("");
                taskViewModel.insert(task);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SHOW_SUBTITLE, subtitleVisible);
    }

    private void updateView() {

      }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView nameTextView;
        private TextView dateTextView;
        private Task task;
        private ImageView iconImageView;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_item_date);
            iconImageView = itemView.findViewById(R.id.checkbox);
        }

        public void bind(Task task) {
            this.task = task;

            nameTextView.setText(task.getName());

        }

        @Override
        public void onClick(View v) {
            editTask = task;
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_EDIT_BOOK_TITLE, task.getId());
            Log.d("1111111111111111111", String.valueOf(task.getId()));
            intent.putExtra("task_id", task.getId());
            intent.putExtra("ta", task);

            startActivityForResult(intent, EDIT_BOOK_ACTIVITY_REQUEST_CODE);
        }
        @Override
        public boolean onLongClick(View v) {
            taskViewModel.delete(task);
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);

        if(resultCode == -1) {
           editTask= (Task) data.getSerializableExtra("task");
           if(editTask.getLatitude()!=0)
           taskViewModel.update(editTask);

        } else {

        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;



        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

            if(tasks !=null){
                Task task= tasks.get(position);
                holder.bind(task);
            }else{
                Log.d("Main111111111", "no tasks");
            }
        }

        @Override
        public int getItemCount() {

            if (tasks !=null){
                return tasks.size();
            }else{
                return 0;
            }
        }
        void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }
    }
}

