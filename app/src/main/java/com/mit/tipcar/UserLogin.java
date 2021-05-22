package com.mit.tipcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

public class UserLogin extends AppCompatActivity {

    private Button bt_login,bt_join;
    private LinearLayout ln_login_kakao;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        ln_login_kakao = findViewById(R.id.ln_login_kakao);

        bt_join = findViewById(R.id.bt_join);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener((android.view.View.OnClickListener) this);
        bt_join.setOnClickListener((android.view.View.OnClickListener) this);
        ln_login_kakao.setOnClickListener((android.view.View.OnClickListener) this);




    }
    public void onClick(android.view.View v){
        switch (v.getId()){
            case R.id.bt_join:

                Intent intent = new Intent(this,join.class);
                startActivity(intent);

                break;
            case R.id.bt_login:
                Intent success = new Intent(this,MainActivity.class);
                //User정보 DB와 비교, 일치하면 유저 데이터 저장 불일치면 로그인 실패 출


                startActivity(success);
                break;

            case R.id.ln_login_kakao:

                break;
        }
    }
}