package com.ishan.trivia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {


    private ImageView finish;
    private RecyclerView mRecyclerView;
    private HistoryCustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AddingItemsHistoryModel> data;

    private SQLiteHelper sqLiteHelper;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<String> NAME_Array;
    private ArrayList<String> Ans1_Array;
    private ArrayList<String> Ans2_Array;
    private ArrayList<String> Time_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        finish=findViewById(R.id.history_finish);

        data=new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(this);
        NAME_Array = new ArrayList<String>();
        Ans1_Array = new ArrayList<String>();
        Ans2_Array = new ArrayList<String>();
        Time_Array = new ArrayList<String>();

        //Configuring Recycler View
        mRecyclerView=findViewById(R.id.history_container);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //function to add items in recycler View
        addHistory();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EnterName.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addHistory(){

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        NAME_Array.clear();
        Ans1_Array.clear();
        Ans2_Array.clear();
        Time_Array.clear();

        //adding data to respective Arrays - Name,Ans1,Ans2,Time
        if (cursor.moveToFirst()) {
            do {
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                Ans1_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Ans1)));
                Ans2_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Ans2)));
                Time_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Time)));
            } while (cursor.moveToNext());
        }

        //Adding data to Model
        for(int i=NAME_Array.size()-1;i>=0;i--){

            data.add(new AddingItemsHistoryModel("GAME "+(i+1),Time_Array.get(i),"Name : "+NAME_Array.get(i),
                    "Answer: "+Ans1_Array.get(i),"Answer: "+Ans2_Array.get(i)));

        }

        //Addind items Now
        mAdapter= new HistoryCustomAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        cursor.close();

    }
}
