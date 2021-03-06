package com.example.homebudget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Андрей on 17.11.2015.
 */
public class ActivityIncomes extends AppCompatActivity {

    private LinearLayout linearLayoutContent;

    private EditText editTextCostsDate;
    private EditText editTextCosts;
    private EditText editTextCostsInfo;

    SQLiteDatabase db;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        linearLayoutContent = (LinearLayout) findViewById(R.id.linearLayoutContent);

        editTextCostsDate = (EditText) findViewById(R.id.editTextIncomesDate);
        editTextCosts = (EditText) findViewById(R.id.editTextIncomes);
        editTextCostsInfo = (EditText) findViewById(R.id.editTextIncomesInfo);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        editTextCostsDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutContent.removeAllViews();
        Cursor c = db.query("incomes_and_costs", null, "is_income = 1", null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String str = "";
                str = c.getString(c.getColumnIndex("date")) + " " + c.getString(c.getColumnIndex("summ")) + " " + c.getString(c.getColumnIndex("description"));
                TextView textView = new TextView(this);
                textView.setText(str);
                textView.setLayoutParams(lp);
                linearLayoutContent.addView(textView);
            }
            while (c.moveToNext());
        }
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonActivityCosts:
                intent = new Intent(this, ActivityCosts.class);
                startActivity(intent);
                break;

            case R.id.buttonActivitySettings:
                intent = new Intent(this, ActivitySettings.class);
                startActivity(intent);
                break;

            case R.id.btnIncomesSave:
                boolean check = true;
                if (editTextCosts.getText().toString().length() == 0){
                    editTextCosts.setError("Пустое поле!");
                    check = false;
                }
                if (editTextCostsInfo.getText().toString().length() == 0){
                    editTextCostsInfo.setError("Пустое поле!");
                    check = false;
                }
                if (check){
                    ContentValues cv = new ContentValues();
                    cv.put("is_income", "1");
                    cv.put("date", editTextCostsDate.getText().toString());
                    cv.put("summ", editTextCosts.getText().toString());
                    cv.put("description", editTextCostsInfo.getText().toString());
                    db.insert("incomes_and_costs", null, cv);
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutContent.removeAllViews();
                Cursor c = db.query("incomes_and_costs", null, "is_income = 1", null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        String str = "";
                        str = c.getString(c.getColumnIndex("date")) + " " + c.getString(c.getColumnIndex("summ")) + " " + c.getString(c.getColumnIndex("description"));
                        TextView textView = new TextView(this);
                        textView.setText(str);
                        textView.setLayoutParams(lp);
                        linearLayoutContent.addView(textView);
                    }
                    while (c.moveToNext());
                }
                break;

            case R.id.btnIncomesDeleteAll:
                db.delete("incomes_and_costs", null, null);
                linearLayoutContent.removeAllViews();
                break;
        }
    }

    public Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            editTextCostsDate.setText(day_x + "-" + month_x + "-" + year_x);
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent;
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
