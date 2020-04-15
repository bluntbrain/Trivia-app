package com.ishan.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EnterName extends AppCompatActivity {

    private ImageView nextButton;
    private EditText userName;
    private String time;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        nextButton=findViewById(R.id.welcomenext);
        userName=findViewById(R.id.username);

        //Using Calendar to get date and Time when user joined
        mCalendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM hh:mm a");
        time=dateFormat.format(mCalendar.getTime());

        //On clicking next Button
        //Name and date/time will is storing using SQLite
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Condition for Empty Field
                String name =userName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Field Empty",Toast.LENGTH_SHORT).show();
                    return;
                }


                String[] data={userName.getText().toString(),time," "," "};
                //After Uploading intent to Next Activity
                Intent intent=new Intent(getBaseContext(),QuestionActivity.class);
                intent.putExtra("Data",data);
                startActivity(intent);


            }
        });
    }


    //Function to Hide Keyboard by tapping on view.
    public void rootlayouttap(View view)
    {
        try {
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e)
        {

        }
    }

    //On Back Press
    // User will exit from app
    public void onBackPressed(){
        finishAffinity();
        finish();

    }
}
