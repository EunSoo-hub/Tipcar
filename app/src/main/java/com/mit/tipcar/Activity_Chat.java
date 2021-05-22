package com.mit.tipcar;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Chat extends AppCompatActivity {

    private android.widget.ListView Lv_chat_list;
    private LinearLayout Ln_no_notification;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chat);
        Lv_chat_list = findViewById(R.id.Lv_chat_list);
        Ln_no_notification = findViewById(R.id.Ln_no_notification);

        if(Lv_chat_list.getCount() == 0){
            Lv_chat_list.setVisibility(android.view.View.GONE);
            Ln_no_notification.setVisibility(android.view.View.VISIBLE);
        }else{
            Lv_chat_list.setVisibility(android.view.View.VISIBLE);
            Ln_no_notification.setVisibility(android.view.View.GONE);
        }
    }
}