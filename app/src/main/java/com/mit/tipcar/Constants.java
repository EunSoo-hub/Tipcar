package com.mit.tipcar;

import java.util.HashMap;

public class Constants {

    //FavoriteCount == 1 -> 좋아요상태  FavoirteCount == 0 --> 싫어요 상태
    public static HashMap<String, Integer> FavoriteCountRemember = new HashMap<>();
    //Frag2 <----->kakaoapitest.class간의 gps정보 공유
    public static final Integer NAVIGATION_RESULT = 1000;

    public static boolean IsLogin = false;




}
