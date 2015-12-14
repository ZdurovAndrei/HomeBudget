package com.example.homebudget;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutMain;
    SQLiteDatabase db;
    TextView textViewBalance;

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
        textViewBalance = (TextView)findViewById(R.id.textViewBalance);
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
    private void Balance() {
        int sum = 0;
        Cursor c = db.query("incomes_and_costs", null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            Toast.makeText(getApplicationContext(),c.getString(0), Toast.LENGTH_SHORT).show();
            if(c.getString(0).equals("is_income = 1"))
                sum = sum + c.getInt(2);
            else
                sum = sum - c.getInt(2);
        }
        buffer.append(sum);
        textViewBalance.setText(buffer);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Balance();
    }

    public void onDestroy() {
        super.onDestroy();
        moveTaskToBack(true);

        System.exit(0);
    }
}
