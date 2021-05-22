package com.mit.tipcar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public class Frag_Call_Info1 extends androidx.fragment.app.Fragment {

    private android.view.View view;

    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_frag_call_info1, container, false);

        return view;
    }
}