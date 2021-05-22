package com.mit.tipcar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ContentFrag4 extends androidx.fragment.app.Fragment implements View.OnClickListener{
    private android.view.View view;
    private android.widget.TextView text1,text2,text3,text4,text5;
    private FrameLayout FragContent;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private LinearLayout Ln_check_my_insure_price,Ln_check_my_insure_price_driver,Ln_compare_insure_tab,Ln_Today_Cars_Price,Ln_Today_Price_Mycar;
    private LinearLayout Ln_use_sell, Ln_use_TipCar;
    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.contentfragment4,container,false);

        Ln_check_my_insure_price = view.findViewById(R.id.Ln_check_my_insure_price);
        Ln_check_my_insure_price_driver = view.findViewById(R.id.Ln_check_my_insure_price_driver);
        Ln_compare_insure_tab = view.findViewById(R.id.Ln_compare_insure_tab);
        Ln_Today_Cars_Price = view.findViewById(R.id.Ln_Today_Cars_Price);
        Ln_Today_Price_Mycar = view.findViewById(R.id.Ln_Today_Price_Mycar);
        Ln_use_sell = view.findViewById(R.id.Ln_use_sell);
        Ln_use_TipCar = view.findViewById(R.id.Ln_use_TipCar);

        Ln_check_my_insure_price.setOnClickListener(this);
        Ln_check_my_insure_price_driver.setOnClickListener(this);
        Ln_compare_insure_tab.setOnClickListener(this);
        Ln_Today_Cars_Price.setOnClickListener(this);
        Ln_Today_Price_Mycar.setOnClickListener(this);
        Ln_use_sell.setOnClickListener(this);
        Ln_use_TipCar.setOnClickListener(this);




        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Ln_check_my_insure_price:

                break;
            case R.id.Ln_check_my_insure_price_driver:

                break;
            case R.id.Ln_compare_insure_tab:

                break;
            case R.id.Ln_Today_Cars_Price:

                break;
            case R.id.Ln_Today_Price_Mycar:

                break;
            case R.id.Ln_use_sell:

                break;
            case R.id.Ln_use_TipCar:

                break;
        }
    }
}
