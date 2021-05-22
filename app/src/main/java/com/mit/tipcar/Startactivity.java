package com.mit.tipcar;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.util.maps.helper.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Startactivity extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        getHashKey();
    Constants.IsLogin = true;
    }

    private void getHashKey(){


        PackageInfo packageInfo = Utility.getPackageInfo(this, PackageManager.GET_SIGNATURES);
        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

    }

}
