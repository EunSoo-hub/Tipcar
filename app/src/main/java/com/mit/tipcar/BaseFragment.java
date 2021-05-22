package com.mit.tipcar;

public class BaseFragment extends androidx.fragment.app.Fragment {
    public void progressON() {
        BaseApplication.getInstance().progressON(getActivity(), "로딩중...");
    }
    public void progressON(String message) {
        BaseApplication.getInstance().progressON(getActivity(), message);
    }
    public void progressOFF() {
        BaseApplication.getInstance().progressOFF();
    }
}

