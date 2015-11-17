package com.example.homebudget;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MyFragment();
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.container, fragment);
        fragmentManager.commit();
    }

    public void onDestroy() {
        super.onDestroy();
        moveTaskToBack(true);
        System.exit(0);
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
}
