package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private int task_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Bundle bundle = getIntent().getExtras();

//        if(bundle.get("task_id")!= null)
//        {
//            task_id= (int) bundle.get("task_id");
//            Log.d("xxxxxxxxxxxxxxxxxxxxxxxxx", ""+task_id);
//            //TODO here get the string stored in the string variable and do
//            // setText() on userName
//        }
//       // bundle.putSerializable("task_id",task_id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    protected abstract Fragment createFragment();
}