package com.mit.tipcar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ContentFrag3 extends androidx.fragment.app.Fragment {
    private android.view.View view;
    private android.widget.TextView text1,text2,text3,text4,text5;
    private FrameLayout FragContent;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.contentfragment3,container,false);


        return view;
    }


}
