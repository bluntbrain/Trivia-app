package com.ishan.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    //Intialising Model
    private TriviaModel[] mTriviaModels = {
            new TriviaModel(R.string.question1, R.string.q1option1, R.string.q1option2, R.string.q1option3, R.string.q1option4),
            new TriviaModel(R.string.question2, R.string.q2option1, R.string.q2option2, R.string.q2option3, R.string.q2option4)
    };

    private Integer questionIndex;

    private TextView question;
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private CheckBox option4;
    private ImageView questionButton;

    private String ans1;
    private String ans2;

    private Intent mIntent;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mIntent=getIntent();
        data =mIntent.getStringArrayExtra("Data");

        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        questionButton = findViewById(R.id.questionbutton);
        questionIndex = 0;
        ans2="";

        //function to load questions and options
        loadQuestion();


        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fuction to store current selections using SQLite
                onButtonClicked();
            }
        });

    }

    private void onButtonClicked() {

        //Condition for Empty Field
        if(!option1.isChecked() && !option2.isChecked() && !option3.isChecked() && !option4.isChecked())
        {
            Toast.makeText(getApplicationContext(),"No Answer Selected",Toast.LENGTH_SHORT).show();
            return;
        }
        //index=0 implies question 1
        if (questionIndex == 0) {

            uploadAnswers(); //fuction to store answers
            option1.setChecked(false);
            option2.setChecked(false);
            option3.setChecked(false);
            option4.setChecked(false);
            questionIndex = 1;
            loadQuestion(); //Loading Questions for question 2

        } else if (questionIndex == 1) {

            uploadAnswers();
            questionIndex = 0;

            //After question 2
            //Intent to Summary Activity
            Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
            intent.putExtra("Data",data);
            startActivity(intent);
        } else {

        }
    }

    private void loadQuestion() {
        readyCheckBox(); //fuction to alter checkbox selection according to question

        //Loading Questions Now
        question.setText(mTriviaModels[questionIndex].getQuestion());
        option1.setText(mTriviaModels[questionIndex].getOption1());
        option2.setText(mTriviaModels[questionIndex].getOption2());
        option3.setText(mTriviaModels[questionIndex].getOption3());
        option4.setText(mTriviaModels[questionIndex].getOption4());

    }


    private void readyCheckBox() {

        //For Question 1
        //Seleting Listeners for unique selection
        if (questionIndex == 0) {
            option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        option2.setChecked(false);
                        option3.setChecked(false);
                        option4.setChecked(false);

                    }
                }
            });

            option2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        option1.setChecked(false);
                        option3.setChecked(false);
                        option4.setChecked(false);

                    }
                }
            });

            option3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        option1.setChecked(false);
                        option2.setChecked(false);
                        option4.setChecked(false);

                    }
                }
            });

            option4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        option1.setChecked(false);
                        option2.setChecked(false);
                        option3.setChecked(false);

                    }
                }
            });
        } else {

            //Disabling Listeners for multiple selections
            option1.setOnCheckedChangeListener(null);

            option2.setOnCheckedChangeListener(null);

            option3.setOnCheckedChangeListener(null);

            option4.setOnCheckedChangeListener(null);
        }
    }

    private void uploadAnswers() {


            if (questionIndex == 0) {

                //This Block will upload ans 1

                if (option1.isChecked()) {
                    ans1 = option1.getText().toString();
                } else if (option2.isChecked()) {
                    ans1 = option2.getText().toString();
                } else if (option3.isChecked()) {
                    ans1 = option3.getText().toString();
                } else if (option4.isChecked()) {
                    ans1 = option4.getText().toString();
                }
                data[2]=ans1;

            } else if (questionIndex == 1) {

                //This Block will upload ans 2

                if(option1.isChecked()){
                    ans2=ans2+option1.getText().toString()+",";
                }
                if(option2.isChecked()){
                    ans2=ans2+option2.getText().toString()+",";
                }
                if(option3.isChecked()){
                    ans2=ans2+option3.getText().toString()+",";
                }
                if(option4.isChecked()){
                    ans2=ans2+option4.getText().toString()+",";
                }

                ans2=ans2.substring(0,ans2.length()-1);

                data[3]=ans2;

            }



    }


    //Disabled Back Button
    @Override
    public void onBackPressed () {

    }
}
