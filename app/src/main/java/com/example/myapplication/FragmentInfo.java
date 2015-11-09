package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Андрей on 09.11.2015.
 */
public class FragmentInfo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        return view;
    }

    // ���������� ���������� ����
    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.textViewBalance);
        view.setText(item);
    }

}
