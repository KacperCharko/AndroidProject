package com.example.androidproject;
import android.app.Activity;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;

public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "";
    public static final String EXTRA_EDIT_BOOK_TITLE = "EDIT_BOOK_TITLE";
    public static final String EXTRA_EDIT_BOOK_AUTHOR = "EDIT_BOOK_AUTHOR";
    private EditText nameField;
    private Button dateButton;
    private Button btnNavi;
    private Button btnConfirm;
    private static Task task;
    private ImageView imageView;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        nameField = view.findViewById(R.id.task_name);
        dateButton = view.findViewById(R.id.task_date);
        btnConfirm = view.findViewById((R.id.btnConfirm));
        imageView = view.findViewById(R.id.imageView);
        btnNavi = view.findViewById(R.id.btnNavi);



        btnNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ConfirmActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent replyIntent = new Intent();
                    getActivity().setResult(RESULT_OK, replyIntent);
                    replyIntent.putExtra("task", task);

                    getActivity().finish();



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
                task.setName(s.toString());
            }
        });
        nameField.setText(task.getName());
        dateButton.setEnabled(true);
        if(task.getLatitude()!=0 && task.getLongitude()!=0) {
            dateButton.setText("" + task.getLatitude() + task.getLongitude());

            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(),MapActivity.class);

                    intent.putExtra("loc", new LatLng(task.getLatitude(),task.getLongitude()));

                    startActivity(intent);
                }

            });
        }


        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        return view;
    }

    public static TaskFragment newInstance(int taskId, @Nullable Task x) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taskId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        task=x;
        return taskFragment;
    }

@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            LatLng latLng= (LatLng) data.getExtras().get("loc");
            dateButton.setText(""+latLng);
            task.setLatitude(latLng.latitude);
            task.setLongitude(latLng.longitude);
        }
    }
}