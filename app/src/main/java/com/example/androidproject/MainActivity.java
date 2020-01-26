package com.example.androidproject;



import androidx.fragment.app.Fragment;

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
        Bundle bundle = getIntent().getExtras();

        if(bundle.get("task_id")!= null)
        {
         //   task_id= (int) bundle.get("task_id");
          //  Log.d("xxxxxxxxxxxxxxxxxxxxxxxxx1", ""+EXTRA_EDIT_BOOK_TITLE+"sadas");

            //TODO here get the string stored in the string variable and do
            // setText() on userName
        }
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

        return TaskFragment.newInstance(task_id.getId(),task_id);
    }
}