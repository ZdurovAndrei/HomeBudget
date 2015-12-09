package com.example.homebudget;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutMain;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Cursor c = db.query("incomes_and_costs", null, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                String str = "";
                str = c.getString(c.getColumnIndex("date")) + " " + c.getString(c.getColumnIndex("summ")) + " " + c.getString(c.getColumnIndex("description"));
                TextView textView = new TextView(this);
                textView.setText(str);
                textView.setLayoutParams(lp);
                linearLayoutMain.addView(textView);
            }
            while(c.moveToNext());
        }
    }

    public void onClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.buttonActivityIncomes:
                intent = new Intent(this, ActivityIncomes.class);
                startActivity(intent);
                break;

            case R.id.buttonActivityCosts:
                intent = new Intent(this, ActivityCosts.class);
                startActivity(intent);
                break;

            case R.id.buttonActivitySettings:
                intent = new Intent(this, ActivitySettings.class);
                startActivity(intent);
                break;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        moveTaskToBack(true);

        System.exit(0);
    }
}
