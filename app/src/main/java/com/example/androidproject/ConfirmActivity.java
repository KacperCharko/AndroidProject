package com.example.androidproject;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.database.Task;

public class ConfirmActivity extends AppCompatActivity {

    private Task task;
    private Button btnNav;
    private Button btnCamera;
    private ImageView imageView;
    public static final String KEY_EXTRA_ANSWER_SHOWN="answerShown";


    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent=new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_activity);
        // correctAnswer= getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);
        // showCorrectAnswerButton=findViewById(R.id.show_prompt);
        btnNav=findViewById(R.id.btnNav);
        imageView = findViewById(R.id.imageView2);
        btnCamera=findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle.get("task") !=null) {

             task = (Task)bundle.get("task");
            Log.d("XD", task.getName());
        }



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
