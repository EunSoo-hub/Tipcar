package com.mit.tipcar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ContentFrag1 extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener{
    private android.view.View view;
    private android.widget.TextView researchmycar,tx_my_car_yes;
    private android.widget.TextView text1,text2,text3,text4,text5;
    private ImageView iv_notification, iv_chat;
    private FrameLayout FragContent;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private LinearLayout Ln_myinfo_item_1,Ln_myinfo_item_2,Ln_myinfo_item_3,Ln_myinfo_item_4,Ln_myinfo_item_5,Ln_myinfo_item_6,Ln_search_my_car,Ln_join_insure;


    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.contentfragment1,container,false);

        researchmycar = view.findViewById(R.id.tx_research_mycar);
        tx_my_car_yes = view.findViewById(R.id.tx_my_car_yes);
        Ln_join_insure = view.findViewById(R.id.Ln_join_insure);
        Ln_search_my_car = view.findViewById(R.id.Ln_search_my_car);
        Ln_myinfo_item_1 = view.findViewById(R.id.Ln_myinfo_item_1);
        Ln_myinfo_item_2 = view.findViewById(R.id.Ln_myinfo_item_2);
        Ln_myinfo_item_3 = view.findViewById(R.id.Ln_myinfo_item_3);
        Ln_myinfo_item_4 = view.findViewById(R.id.Ln_myinfo_item_4);
        Ln_myinfo_item_5 = view.findViewById(R.id.Ln_myinfo_item_5);
        Ln_myinfo_item_6 = view.findViewById(R.id.Ln_myinfo_item_6);
        iv_notification = view.findViewById(R.id.iv_notification);
        iv_chat = view.findViewById(R.id.iv_chat);

        researchmycar.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_join_insure.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_search_my_car.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_1.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_2.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_3.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_4.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_5.setOnClickListener((android.view.View.OnClickListener) this);
        Ln_myinfo_item_6.setOnClickListener((android.view.View.OnClickListener) this);
        iv_chat.setOnClickListener((android.view.View.OnClickListener) this);
        iv_notification.setOnClickListener((android.view.View.OnClickListener) this);

        return view;


    }
    public void onClick(android.view.View v){
        switch (v.getId()){
            case R.id.Ln_join_insure:
                //보험가입 창으로 화면전환
                Intent join_insure = new Intent(getActivity(),Join_Insure.class);
                startActivity(join_insure);


                break;

            case R.id.Ln_search_my_car:
                //내 차량 조회로 화면전환
                Intent searchmycar = new Intent(getActivity(),Search_My_Car.class);
                startActivity(searchmycar);
                break;

            case R.id.Ln_myinfo_item_1:
                //스크롤뷰 아이템 1 화면전환
                Intent Horizontalitem1= new Intent(getActivity(),HorizontalActivity1.class);
                startActivity(Horizontalitem1);

                break;

            case R.id.Ln_myinfo_item_2:
                //스크롤뷰 아이템 2 화면전환
                Intent Horizontalitem2= new Intent(getActivity(),HorizontalActivity2.class);
                startActivity(Horizontalitem2);


                break;
            case R.id.Ln_myinfo_item_3:
                //스크롤뷰 아이템 3 화면전환
                Intent Horizontalitem3= new Intent(getActivity(),HorizontalActivity3.class);
                startActivity(Horizontalitem3);

                break;
            case R.id.Ln_myinfo_item_4:
                //스크롤뷰 아이템 4 화면전환
                Intent Horizontalitem4= new Intent(getActivity(),HorizontalActivity4.class);
                startActivity(Horizontalitem4);

                break;
            case R.id.Ln_myinfo_item_5:
                //스크롤뷰 아이템 5 화면전환
                Intent Horizontalitem5= new Intent(getActivity(),HorizontalActivity5.class);
                startActivity(Horizontalitem5);

                break;
            case R.id.Ln_myinfo_item_6:
                //스크롤뷰 아이템 6 화면전환
                Intent Horizontalitem6= new Intent(getActivity(),HorizontalActivity6.class);
                startActivity(Horizontalitem6);

                break;

            case R.id.tx_research_mycar:
                Intent researchmycar = new Intent(getActivity(),Research_MyCar.class);
                startActivity(researchmycar);

                break;
            case R.id.tx_my_car_yes:

                break;
            case R.id.iv_chat:
                Intent chatintent = new Intent(getActivity(),Activity_Chat.class);
                startActivity(chatintent);
                break;

            case R.id.iv_notification:
                Intent notiintent  = new Intent(getActivity(),My_Notification.class);
                startActivity(notiintent);
                break;


        }
    }

}
