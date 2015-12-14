package com.example.homebudget;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Андрей on 17.11.2015.
 */
public class ActivitySettings extends AppCompatActivity {
       final String FILENAME = "file.json";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClick(View v) throws IOException, JSONException {
        Intent intent;
        switch(v.getId()) {
            case R.id.buttonActivityIncomes:
                intent = new Intent(this, ActivityIncomes.class);
                startActivity(intent);
                break;

            case R.id.buttonActivityCosts:
                intent = new Intent(this, ActivityCosts.class);
                startActivity(intent);
                break;

            case R.id.btnjsonexport:
                SQLiteDatabase db;
                DBHelper dbHelper = new DBHelper(this);
                db = dbHelper.getWritableDatabase();
                Cursor c = db.query("incomes_and_costs", null, null, null, null, null, null);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        openFileOutput(FILENAME, MODE_PRIVATE)));
                JSONObject object = new JSONObject();
                if (c.moveToFirst()) {
                    do {
                            object.put("date", c.getColumnIndex("date"));
                            object.put("summ", c.getColumnIndex("summ"));
                            object.put("description", c.getColumnIndex("description"));
                            bw.write(object.toString());
                    }
                    while (c.moveToNext());
                }
                bw.close();
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent;
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}
