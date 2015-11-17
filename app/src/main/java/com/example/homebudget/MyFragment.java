package com.example.homebudget;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Андрей on 17.11.2015.
 */
public class MyFragment extends Fragment {

    public MyFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment, viewGroup, false);

    }

}
