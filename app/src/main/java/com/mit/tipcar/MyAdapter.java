package com.mit.tipcar;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList lst = new ArrayList();
    LayoutInflater inflater;

    public MyAdapter(Context context) {

        lst.add("수박");
        lst.add("귤");
        lst.add("포도");
        lst.add("키위");
        lst.add("바나나");
        lst.add("사과");
        lst.add("딸기");
        lst.add("토마토");
        lst.add("레몬");
        lst.add("망고");
        lst.add("파인애플");
        lst.add("방울토마토");
        lst.add("메론");

        //getSystemService은 Activity클래스의 메서드이다.
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    //아래의 재정의된 모든 메서드를 호출하는 주체는 ListView이다.
    //즉 ListView는 아래의 메서드를 통해 데이터 정보를 반영한다.
    //리스트뷰에게 리스트의 개수를 몇개로 구성해야하는지를 알게해준다.(호출자:ListView)
    public int getCount() {
        return lst.size();
    }

    //리스트뷰에게 지정한 postion 변수에 해당하는 순서의 데이터를 추출할 수 있도록 해준다.

    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }
    //리스트뷰는 getView 메서드를 getCount()반환값 만큼 호출하여, 각 item을 구성하게 된다.
    //이 예제의 경우 데이터가 13건이기 때문에 인플레이션 13번 일으켜 그 결과를 return해준다.

    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {

        //getView메서드의 특징
        //스마트폰에 아이템이 화면에 보여지는 시점에 화면구성을 위해 호출된다.
        //따라서 내가 보여줄 데이터가 100건이라도 화면에 보여질 리스트의 개수가 13건이면
        //getView호출은 12번을 먼저 호출한 후, 화면에 가려졌다가 다시 보이게 되는 아이템이 발생하면
        //다시 getView가 발생한다.
        //따라서 한 아이템당 한번만 인플레이션이 이루어지도록 조건문을 넣어줘야 한다.
        android.view.View view=null;
        if(convertView==null){
            view = inflater.inflate(R.layout.comment_list_item, parent, false);
        }else{
            view = convertView;
        }
        android.widget.ListView layout = (android.widget.ListView) view;

        //레이아웃 객체는 자신이 포함하고 있는 위젯들을 id로 검색할수 있다.
        android.widget.TextView txt_title = (android.widget.TextView)layout.findViewById(R.id.Content_user);
        android.widget.TextView txt_content = (android.widget.TextView)layout.findViewById(R.id.Content_title);
        ImageView img = (ImageView)layout.findViewById(R.id.Content_image);
        txt_title.setText((String)lst.get(position));
        txt_content.setText((String)lst.get(position)+"입니다.");
        img.setImageResource(R.drawable.carimage);
        android.util.Log.i(this.getClass().getName(), "View의 주소값:"+view);

        return layout;
    }
}



