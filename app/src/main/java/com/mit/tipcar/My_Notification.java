package com.mit.tipcar;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class My_Notification extends AppCompatActivity implements android.view.View.OnClickListener {

    private LinearLayout Ln_no_information;
    private android.widget.ListView Lv_my_info_list;
    private ImageView iv_back;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__notification);

        Lv_my_info_list = findViewById(R.id.Lv_my_info_list);
        Ln_no_information = findViewById(R.id.Ln_no_notification);
        iv_back = findViewById(R.id.iv_back);


        iv_back.setOnClickListener((android.view.View.OnClickListener) this);

        if(Lv_my_info_list.getCount() == 0){
            Lv_my_info_list.setVisibility(android.view.View.GONE);
            Ln_no_information.setVisibility(android.view.View.VISIBLE);
        }else{
            Lv_my_info_list.setVisibility(android.view.View.VISIBLE);
            Ln_no_information.setVisibility(android.view.View.GONE);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClick(android.view.View view){
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

}