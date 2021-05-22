package com.mit.tipcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private String ContentSize = "";

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.noticeboard_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView Content_image = (ImageView) convertView.findViewById(R.id.Content_image) ;
        android.widget.TextView Content_title = (android.widget.TextView) convertView.findViewById(R.id.Content_title) ;
        android.widget.TextView Content_user = (android.widget.TextView) convertView.findViewById(R.id.Content_user) ;
        android.widget.TextView Content = convertView.findViewById(R.id.Content);
        android.widget.TextView tx_visible_count = convertView.findViewById(R.id.tx_visible_count);
        android.widget.TextView tx_favorite_count = convertView.findViewById(R.id.tx_favorite_count);
        android.widget.TextView tx_write_date = convertView.findViewById(R.id.tx_write_date);


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영 (아이템 순서 -> 제목/내용/작성자/사진/읽은사람/관심수/작성날짜
        Content_title.setText(listViewItem.getTitle());
        if(listViewItem.getContent().length() >= 70){

                for (int i = 0; i < 70; i++) {
                    ContentSize += listViewItem.getContent().charAt(i);
                }
                Content.setText(ContentSize + " ...더 보기");
                    ContentSize = "";
        }else{
            Content.setText(listViewItem.getContent());
        }
        Content_user.setText(listViewItem.getDesc());
        Content_image.setImageDrawable(listViewItem.getIcon());
        tx_visible_count.setText(listViewItem.getTx_visible_count());
        tx_favorite_count.setText(listViewItem.getTx_favorite_count());
        tx_write_date.setText(listViewItem.getTx_write_date());

        if(listViewItem.getIcon() == null){
            Content_image.setVisibility(android.view.View.GONE);
        }
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String Content, String desc, android.graphics.drawable.Drawable icon, String visible_count, String favorite_count, String write_date) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setContent(Content);
        item.setDesc(desc);
        item.setIcon(icon);
        item.setTx_visible_count(visible_count);
        item.setTx_favorite_count(favorite_count);
        item.setTx_write_date(write_date);

        listViewItemList.add(item);
    }
}