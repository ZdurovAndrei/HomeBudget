package com.example.homebudget;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Андрей on 17.11.2015.
 */
public class ActivitySettings extends AppCompatActivity {
       final String FILENAME = "file";

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
            case R.id.btnjson:
                SQLiteDatabase db;
                DBHelper dbHelper = new DBHelper(this);
                db = dbHelper.getWritableDatabase();
                Cursor c = db.query("incomes_and_costs", null, null, null, null, null, null);

                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(FILENAME, MODE_PRIVATE)));
                    JSONObject object = new JSONObject();
                    if (c.moveToFirst()) {
                        do {
                            object.put("date", c.getColumnIndex("date"));
                            object.put("summ", c.getColumnIndex("summ"));
                            object.put("description", c.getColumnIndex("description"));
                        }
                        while (c.moveToNext());
                    }
                    bw.write(object.toString());
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                    File file = new File("file.json");
//                    file.createNewFile();
//                    FileOutputStream fOut = openFileOutput(file.getAbsolutePath(), MODE_WORLD_READABLE);
//

//                    OutputStreamWriter writer = new OutputStreamWriter(fOut);
//                    writer.write(object.toString());
//                    writer.flush();
//                    writer.close();


//                String FILENAME = "e:\\matfuck\\AndroidStudioProjects\\HomeBudget\\file.json";
//                FileWriter writer = new FileWriter(FILENAME);
//                writer.write(object.toString());
//                writer.flush();
//                writer.close();
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
