package com.mit.tipcar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class ContentFrag2 extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener{
    private android.view.View view;
    private android.widget.ListView listView;
    private ImageView none_noticeboard_list_image, write_notice_board;
    private android.widget.TextView none_noticeboard_list_text;
    private EditText search_notice_item;
    private byte[] byteArray;
    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.contentfragment2,container,false);
        listView = view.findViewById(R.id.list);
        //게시판 글쓰기 버튼 캐스트작업
        write_notice_board = view.findViewById(R.id.write_notice_board);

        none_noticeboard_list_text = view.findViewById(R.id.none_noticeboard_list_text);
        none_noticeboard_list_image = view.findViewById(R.id.none_noticeboard_list_image);
        search_notice_item = view.findViewById(R.id.search_notice_item);

        write_notice_board.setOnClickListener((android.view.View.OnClickListener) this);

        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        //  ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.noticeboard_list_item,data);
//        List<String> data = new ArrayList<>();
        //관리자-> 2~3라인까지는 이 앱의 게시판 설명내용 및 앱의 규정사항에 대한 이야기를 아이템으로 넣어주기.
//        data.add(R.drawable.carimage,"관리자입니다.");
//        data.add("나만믿어! 어플리케이션");
//        data.add("40% 개발 진행!!");


        //TODO        서버와 통신을 하게 될 경우 리스트뷰의 데이터는 서버에서 받아오는 데이터를 사진/내용/작성자를 리스트에 추가하게 될 것이다.
        //TODO        만약 게시판 리스트가 비어있을 경우, 리스트뷰가 비어있다는 텍스트뷰 + 나만믿어 공식 이미지를 보여줘야 합니다.
        adapter.addItem("야 이건 주제야!","야! 여긴 내용이다?fbcsajkbasjkasjkcbsajkbcjskabcjkascjkasbjcbaskbcsabcsabkcbkasbckjasbckjasbcjksabjckbsajkcbajkbcjsakbcjksabcjksabcjkbsajkcbasjkcbakjb","김동현",
                ContextCompat.getDrawable(getContext(), R.drawable.carimage),
                "50","10","2021.04.09 18:53"
        ) ;
        // 두 번째 아이템 추가.
        adapter.addItem("회원 여러분 이건 주제에요","나만믿어! 어플리케이션 많이 사랑해주세요!","김동현",
                null,
                "5150","100","2021.04.09 18:57"
        ) ;
        // 세 번째 아이템 추가.
        adapter.addItem("후.. 힘들다","이정도면 많이하지않았냐...","김동현",
                ContextCompat.getDrawable(getContext(), R.drawable.carimage),
                "75","166","2021.04.09 19:53"
        ) ;
        // 두 번째 아이템 추가.
        adapter.addItem("회원 여러분 이건 주제에요","나만믿어! 어플리케이션 많이 사랑해주세요!","김동현",
                null,
                "5150","100","2021.04.09 18:57"
        ) ;
        // 두 번째 아이템 추가.
        adapter.addItem("회원 여러분 이건 주제에요","나만믿어! 어플리케이션 많이 사랑해주세요!","김동현",
                null,
                "5150","100","2021.04.09 18:57"
        ) ;
        // 두 번째 아이템 추가.
        adapter.addItem("회원 여러분 이건 주제에요","나만믿어! 어플리케이션 많이 사랑해주세요!","김동현",
                null,
                "5150","100","2021.04.09 18:57"
        ) ;

        adapter.notifyDataSetChanged();

        //게시판에 글이 하나도 없다면 등록된 글이 없다는 것을 알려줌
        if(listView ==null){
            listView.setVisibility(android.view.View.GONE);
            none_noticeboard_list_image.setVisibility(android.view.View.VISIBLE);
            none_noticeboard_list_text.setVisibility(android.view.View.VISIBLE);
        }

//MARK 게시판 리스트 클릭 했을 시, 이미지, 사진, 제목을 함께 게시판 상세보기 화면으로 정보를 전송한다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, android.view.View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String ContentStr = item.getContent();
                String descStr = item.getDesc() ;
                String visibilityStr = item.getTx_visible_count();
                String favoriteStr = item.getTx_favorite_count();
                String writedayStr = item.getTx_write_date();


                Intent intent = new Intent(getActivity(),notice_board_content.class);

                intent.putExtra("position",position);
                intent.putExtra("title",titleStr);
                intent.putExtra("title_user",descStr);
                intent.putExtra("Content",ContentStr);
                intent.putExtra("visibility_count",visibilityStr);
                intent.putExtra("favorite_count",favoriteStr);
                intent.putExtra("writeday",writedayStr);

                if(item.getIcon() != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap = ((BitmapDrawable) item.getIcon()).getBitmap();
                    float scale = (float) (1024 / (float) bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byteArray = stream.toByteArray();
                }
                intent.putExtra("image_content", byteArray);
                byteArray = null;
                startActivity(intent);
                // TODO : use item data.
            }
        }) ;

//        search_notice_item.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // input창에 문자를 입력할때마다 호출된다.
//                // search 메소드를 호출한다.
//                String text = search_notice_item.getText().toString();
//                search(text); //search 메소드에서 정의
//            }
//        });

        return view;
    }


    public void onClick(android.view.View v){
        switch (v.getId()){
            case R.id.write_notice_board:
                //게시판 글쓰기 버튼 클릭시 일어나는 이벤트 코드
                Intent intent = new Intent(getContext(),write_content.class);
                startActivity(intent);
                break;




        }
    }
}
