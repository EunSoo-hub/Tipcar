package com.mit.tipcar;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Frag5 extends androidx.fragment.app.Fragment {
    private android.view.View view;
    private HorizontalScrollView scrollview;
    private android.widget.TextView text1,text2,text3,text4,text5;
    private FrameLayout FragContent;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ContentFrag1 frag1;
    private ContentFrag2 frag2;
    private ContentFrag3 frag3;
    private ContentFrag4 frag4;
    private ContentFrag5 frag5;
    private LinearLayout Ln_join_safecar;

    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment5,container,false);

        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);
        text5 = view.findViewById(R.id.text5);
        scrollview = view.findViewById(R.id.scrollview);
        FragContent = view.findViewById(R.id.Frag_content);
        Ln_join_safecar = view.findViewById(R.id.Ln_join_safecar);


//        text1.setOnClickListener((View.OnClickListener) this);
//        text2.setOnClickListener((View.OnClickListener) this);
//        text3.setOnClickListener((View.OnClickListener) this);
//        text4.setOnClickListener((View.OnClickListener) this);
//        text5.setOnClickListener((View.OnClickListener) this);



        frag1 = new ContentFrag1();
        frag2 = new ContentFrag2();
        frag3 = new ContentFrag3();
        frag4 = new ContentFrag4();
        frag5 = new ContentFrag5();
        text1.setTextColor(android.graphics.Color.parseColor("#2196F3"));
        setFrag(0);

        text1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                text1.setTextColor(android.graphics.Color.parseColor("#2196F3"));
                text2.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text3.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text4.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text5.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text1.setTypeface(null, Typeface.BOLD);
                text2.setTypeface(null, Typeface.NORMAL);
                text3.setTypeface(null, Typeface.NORMAL);
                text4.setTypeface(null, Typeface.NORMAL);
                text5.setTypeface(null, Typeface.NORMAL);
                setFrag(0);
                scrollview.fullScroll(android.widget.ScrollView.FOCUS_LEFT);

            }
        });
        text2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                text1.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text2.setTextColor(android.graphics.Color.parseColor("#2196F3"));
                text3.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text4.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text5.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text1.setTypeface(null, Typeface.NORMAL);
                text2.setTypeface(null, Typeface.BOLD);
                text3.setTypeface(null, Typeface.NORMAL);
                text4.setTypeface(null, Typeface.NORMAL);
                text5.setTypeface(null, Typeface.NORMAL);
                setFrag(1);
                scrollview.smoothScrollTo(text2.FOCUSABLE_AUTO,0);

            }
        });
        text3.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                text1.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text2.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text3.setTextColor(android.graphics.Color.parseColor("#2196F3"));
                text4.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text5.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text1.setTypeface(null, Typeface.NORMAL);
                text2.setTypeface(null, Typeface.NORMAL);
                text3.setTypeface(null, Typeface.BOLD);
                text4.setTypeface(null, Typeface.NORMAL);
                text5.setTypeface(null, Typeface.NORMAL);
                setFrag(2);
                scrollview.smoothScrollTo(text3.FOCUSABLE_AUTO,0);

            }
        });
        text4.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                text1.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text2.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text3.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text4.setTextColor(android.graphics.Color.parseColor("#2196F3"));
                text5.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text1.setTypeface(null, Typeface.NORMAL);
                text2.setTypeface(null, Typeface.NORMAL);
                text3.setTypeface(null, Typeface.NORMAL);
                text4.setTypeface(null, Typeface.BOLD);
                text5.setTypeface(null, Typeface.NORMAL);
                setFrag(3);
                scrollview.smoothScrollTo(text4.FOCUSABLE_AUTO,0);

            }
        });
        text5.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                text1.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text2.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text3.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text4.setTextColor(android.graphics.Color.parseColor("#EAE7E7"));
                text5.setTextColor(android.graphics.Color.parseColor("#2196F3"));
                text1.setTypeface(null, Typeface.NORMAL);
                text2.setTypeface(null, Typeface.NORMAL);
                text3.setTypeface(null, Typeface.NORMAL);
                text4.setTypeface(null, Typeface.NORMAL);
                text5.setTypeface(null, Typeface.BOLD);
                setFrag(4);
           //     scrollview.smoothScrollTo(text5.FOCUSABLE_AUTO,0);
                scrollview.fullScroll(android.widget.ScrollView.FOCUS_RIGHT);

            }
        });
        User_Information.USER_ID ="smilekbear";
        User_Information.USER_COUNTRY = "KR";
            //User가 비회원 일 경우 회원가입 창을 띄워준다.
        if(TextUtils.equals("user_id",User_Information.USER_ID)||
        TextUtils.equals("user_country",User_Information.USER_COUNTRY)){
            Ln_join_safecar.setVisibility(android.view.View.VISIBLE);
            FragContent.setVisibility(android.view.View.GONE);
            scrollview.setVisibility(android.view.View.GONE);
        }else{
            Ln_join_safecar.setVisibility(android.view.View.GONE);
            FragContent.setVisibility(android.view.View.VISIBLE);
            scrollview.setVisibility(android.view.View.VISIBLE);
        }
        return view;
    }

    private void setFrag(int n){
        fm = getChildFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.Frag_content, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.Frag_content, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.Frag_content, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.Frag_content, frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.Frag_content, frag5);
                ft.commit();
                break;
        }
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.text1:
//                setFrag(0);
//                break;
//
//            case R.id.text2:
//                setFrag(1);
//                break;
//            case R.id.text3:
//                setFrag(2);
//                break;
//
//            case R.id.text4:
//                setFrag(3);
//                break;
//            case R.id.text5:
//                setFrag(4);
//                break;
//        }
//    }
}
