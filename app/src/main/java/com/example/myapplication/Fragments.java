package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Андрей on 09.11.2015.
 */
public class Fragments extends Fragment {
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);
        Button buttonCosts = (Button) view.findViewById(R.id.btn);
        // ������ ���������� ������
        buttonCosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        return view;
    }

    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " ������ ������������� ��������� OnFragmentInteractionListener");
        }
    }
    public void updateDetail() {
        // ���������� ��������� ������
        String newTime = String.valueOf(System.currentTimeMillis());
        // �������� ������ Activity
        mListener.onFragmentInteraction(newTime);
    }
}