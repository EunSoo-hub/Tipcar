package com.mit.tipcar;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<androidx.fragment.app.Fragment> items = new ArrayList<androidx.fragment.app.Fragment>();

    public MainPagerAdapter(@androidx.annotation.NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addItem(androidx.fragment.app.Fragment item) {
        items.add(item);
    }

    @androidx.annotation.NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
//        switch (position){
//            case 0 :
//                return Frag_Call_Info1;
//        }
        return items.get(position);
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public android.os.Parcelable saveState() {
        return null;

    }

}

