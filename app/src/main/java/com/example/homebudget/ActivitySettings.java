package com.example.homebudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Андрей on 17.11.2015.
 */
public class ActivitySettings extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
        }
    }

}
