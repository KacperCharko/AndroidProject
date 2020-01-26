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

import java.util.List;

public class    TaskListFragment extends Fragment {


  //  public static final String KEY_EXTRA_TASK_ID = "Sadasdad";
    private TaskViewModel taskViewModel;
    //public static  int KEY_EXTRA_TASK_ID = 0;
    private static final String KEY_SHOW_SUBTITLE = "keyShowSubtitle";
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_ACTIVITY_REQUEST_CODE = 2;
    private RecyclerView recyclerView;
    //private TaskAdapter adapter;
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

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (subtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
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
                //Task task = new Task();
                //TaskStorage.getInstance().addTask(task);
                Intent intent = new Intent(getActivity(), MainActivity.class);
               // intent.putExtra(TaskListFragment.KEY_EXTRA_TASK_ID, task.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                subtitleVisible = !subtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
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

    public void updateSubtitle() {
      //  TaskStorage taskStorage = TaskStorage.getInstance();
        LiveData tasks = taskViewModel.findAll();

//        int toDoTasksCount = 0;
//        for (Da task: tasks) {
//            if (!task.getDone()) {
//                toDoTasksCount++;
//            }
//        }
//        String subtitle = getString(R.string.subtitle_format, toDoTasksCount);
//        if (!subtitleVisible) {
//            subtitle = null;
//        }
        //AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //appCompatActivity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateView() {
     //   TaskStorage taskStorage = TaskStorage.getInstance();
      //  List<Task> tasks = (List<Task>) taskViewModel.findAll();

//        if (adapter == null) {
//            adapter = new TaskAdapter(tasks);
//            recyclerView.setAdapter(adapter);
//        } else {
//            adapter.notifyDataSetChanged();
//        }
//        updateSubtitle();
      }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private TextView dateTextView;
        private Task task;
        private ImageView iconImageView;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_item_date);
            iconImageView = itemView.findViewById(R.id.checkbox);
        }

        public void bind(Task task) {
            this.task = task;
//            if (task.isDone()) {
//                iconImageView.setImageResource(R.drawable.ic_checked_box);
//            } else {
//                iconImageView.setImageResource(R.drawable.ic_unchecked_box);
//            }
            nameTextView.setText(task.getName());
          //  dateTextView.setText(task.getDate().toString().substring(0, 10));
        }

//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
//            startActivity(intent);
//        }
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
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;

//        public TaskAdapter(List<Task> tasks) {
//            this.tasks = tasks;
//        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
//            Task task = tasks.get(position);
//            holder.bind(task);
            if(tasks !=null){
                Task task= tasks.get(position);
                holder.bind(task);
            }else{
                Log.d("Main111111111", "no tasks");
            }
        }

        @Override
        public int getItemCount() {
//            return tasks.size();
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

//import android.content.Intent;
//import android.os.Bundle;
//
//import com.example.androidproject.database.TaskViewModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.List;
//
//public class TaskListFragment extends Fragment {
//
//    private TaskViewModel taskViewModel;
//    public static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;
//    public static final int EDIT_TASK_ACTIVITY_REQUEST_CODE = 2;
//    private Task editTask = null;
//    private boolean subtitleVisible;
//    public static final String KEY_EXTRA_TASK_ID = "keyExtraTaskId";
//    private static final String KEY_SHOW_SUBTITLE = "keyShowSubtitle";
//    private RecyclerView recyclerView;
//    //private TaskAdapter adapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        //super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_main);
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//
//        if (savedInstanceState != null) {
//            subtitleVisible = savedInstanceState.getBoolean(KEY_SHOW_SUBTITLE);
//        }
//
//
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        final TaskAdapter adapter = new TaskAdapter();
//        recyclerView.setAdapter(adapter);
//
//
//        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
//        taskViewModel.findAll().observe(this, new Observer<List<Task>>() {
//            @Override
//            public void onChanged(@Nullable final List<Task> tasks) {
//                adapter.setTasks(tasks);
//            }
//
//        });
//
//        FloatingActionButton fab = findViewById(R.id.add_button);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
//                startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
//            }
//        });
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode, data);
//
//        if(requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Task task = new Task(data.getStringExtra(EditTaskActivity.EXTRA_EDIT_TASK_TITLE),
//                    data.getStringExtra(EditTaskActivity.EXTRA_EDIT_TASK_AUTHOR));
//            taskViewModel.insert(task);
//            Snackbar.make(findViewById(R.id.coordinator_layout), getString(R.string.task_added),
//                    Snackbar.LENGTH_LONG).show();
//        } else if (requestCode == TASK && resultCode == RESULT_OK) {
//            editTask.setName(data.getStringExtra(EditTaskActivity.EXTRA_EDIT_BOOK_TITLE));
//            taskViewModel.update(editTask);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
//
//        private TextView bookTitleTextView;
//        private TextView bookAuthorTextView;
//        private Book book;
//
//        public BookHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.task_list_item, parent, false));
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
//
//            bookAuthorTextView = itemView.findViewById(R.id.task_author);
//            bookTitleTextView = itemView.findViewById(R.id.task_title);
//        }
//
//        public void bind(Task task) {
//            this.task = task;
//            taskTitleTextView.setText(task.getTitle());
//            taskAuthorTextView.setText(task.getAuthor());
//        }
//
//        @Override
//        public void onClick(View v) {
//            editTask = task;
//            Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
//            intent.putExtra(EditTaskActivity.EXTRA_EDIT_BOOK_TITLE, book.getTitle());
//            intent.putExtra(EditTaskActivity.EXTRA_EDIT_BOOK_AUTHOR, book.getAuthor());
//            startActivityForResult(intent, EDIT_Task_ACTIVITY_REQUEST_CODE);
//        }
//
//        @Override
//        public boolean onLongClick(View v) {
//            bookViewModel.delete(book);
//            return true;
//        }
//    }
//
//    private class BookAdapter extends RecyclerView.Adapter<BookHolder> {
//        private List<Book> books;
//
//        @NonNull
//        @Override
//        public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return new BookHolder(getLayoutInflater(), parent);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull BookHolder holder, int position) {
//            if(books != null) {
//                Book book = books.get(position);
//                holder.bind(book);
//            } else {
//                Log.d("MainActivity", "No books");
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            if(books != null) return books.size();
//            else return 0;
//        }
//
//        void setBooks(List<Book> books) {
//            this.books = books;
//            notifyDataSetChanged();
//        }
//    }
//}
