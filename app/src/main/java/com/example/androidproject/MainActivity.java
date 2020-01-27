package com.example.androidproject;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidproject.database.Task;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    public static final String EXTRA_EDIT_BOOK_TITLE = "";
    public static  int EXTRA_EDIT_BOOK_ID = 0;
    private Task task_id;
    //public static final int KEY_EXTRA_TASK_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Fragment createFragment() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        if(bundle.get("task_id")!= null)
        {
            task_id= (Task) bundle.get("ta");
            Log.d("xxxxxxxxxxxxxxxxxxxxxxxxx1", "."+ task_id.getId());

        }
        if (task_id!=null)
        return TaskFragment.newInstance(task_id.getId(),task_id);
        else
            return TaskFragment.newInstance(0,null);
    }


}