package com.example.androidproject;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.androidproject.database.Task;
import com.example.androidproject.database.TaskDao;
import com.example.androidproject.database.TaskDatabase;
import com.example.androidproject.database.TaskViewModel;

import java.util.UUID;

public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "";
    public static final String EXTRA_EDIT_BOOK_TITLE = "EDIT_BOOK_TITLE";
    public static final String EXTRA_EDIT_BOOK_AUTHOR = "EDIT_BOOK_AUTHOR";
    private EditText nameField;
    private Button dateButton;
    private Button btnConfirm;
    private CheckBox doneCheckBox;
    private static Task task;
    private ImageView imageView;
    private TaskViewModel taskViewModel;

    private static int task_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int taskId = (int) getArguments().getSerializable(ARG_TASK_ID);


      //  Log.d("FRAAAAAAAAAAAAA", ARG_TASK_ID + taskId);
        //task = new Task();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        nameField = view.findViewById(R.id.task_name);
        dateButton = view.findViewById(R.id.task_date);
        doneCheckBox = view.findViewById(R.id.task_done);
        btnConfirm = view.findViewById((R.id.btnConfirm));
        imageView = view.findViewById(R.id.imageView);



//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);
//            }
//        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ConfirmActivity.class);
//                boolean correctAnswer=questions[currentIndex].isTrueAnswer();
                //intent.putExtra("task",task);

                startActivity(intent);
            }
        });


        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameField.setText(task.getName());
        //dateButton.setText(task.getDate().toString());
        dateButton.setEnabled(false);
       // doneCheckBox.setChecked(task.isDone());
        doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setDone(isChecked);
            }
        });
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        return view;
    }

    public static TaskFragment newInstance(int taskId, Task x) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taskId);
        bundle.putSerializable("ta", taskId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        task_id = taskId;
        task=x;
       // Log.d("FRAAAAAAAAX", ARG_TASK_ID + taskId);
        return taskFragment;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        task= (Task)data.getExtras().get("task");
//        nameField.setText(task.getName());
//
//        Log.d("XD1", task.getName());
//    }
}