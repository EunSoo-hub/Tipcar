package com.mit.tipcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class write_content extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_iMAGE = 2;
    private Uri mImageCaptureUri;
    private ImageView iv_UserPhoto;
    private int id_view;
    private String absoultePath;
    private android.app.Dialog add_photo_dialog;

    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_PHOTO = 2222;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUEST_IMAGE_CROP = 4444;
    private Uri imageUri;


    private String mCurrentPhotoPath;



    private android.widget.TextView tv_write_content, tv_write_title, tv_write_sucess, tv_write_cancel;
    private ImageView iv_select_photo;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_content);
        add_photo_dialog = new android.app.Dialog(write_content.this);;
        add_photo_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        add_photo_dialog.setContentView(R.layout.add_photo_way_custom_dialog);
        tv_write_cancel = findViewById(R.id.tv_write_cancel);
        tv_write_content = findViewById(R.id.tv_write_content);
        tv_write_title = findViewById(R.id.tv_write_title);
        tv_write_sucess = findViewById(R.id.tv_write_success);
        iv_select_photo = findViewById(R.id.iv_select_photo);

        tv_write_cancel.setOnClickListener(this::onClick);
        tv_write_sucess.setOnClickListener(this::onClick);
        iv_select_photo.setOnClickListener(this::onClick);


        tv_write_title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.equals("",s)||s.length() == 0){
                    tv_write_sucess.setVisibility(android.view.View.INVISIBLE);
                }else{
                    tv_write_sucess.setVisibility(android.view.View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.tv_write_cancel:
                //글쓰기 취소 시 뒤로가기
                onBackPressed();
                break;

            case R.id.tv_write_success:

                break;
            case R.id.iv_select_photo:
                showDialog01();
                break;
        }
    }

    public void showDialog01(){
        add_photo_dialog.show(); // 다이얼로그 띄우기

        // 아니오 버튼
        Button phototocamera = add_photo_dialog.findViewById(R.id.phototocamera);
        Button phototoalbum = add_photo_dialog.findViewById(R.id.phototoalbum);
        Button phototocancel = add_photo_dialog.findViewById(R.id.phototocancel);

     //카메라 이용하여 이미지 가져오기
       phototocamera.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                // 원하는 기능 구현
                doTakePhotoAction();
            }
        });
        // 갤러리에서 이미지가져오기
        phototoalbum.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                // 원하는 기능 구현
                doTakeAlbumAction();
            }
        });
        //사진가져오기 취소
        phototocancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                // 원하는 기능 구현
                add_photo_dialog.dismiss(); // 다이얼로그 닫기
            }
        });
    }

    public void doTakePhotoAction(){
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // 임시로 사용할 파일의 경로를 생성
//        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//        startActivityForResult(intent, PICK_FROM_CAMERA);

        String state = Environment.getExternalStorageState();
        // 외장 메모리 검사
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    android.util.Log.e("captureCamera Error", ex.toString());
                }
                if (photoFile != null) {
                    // getUriForFile의 두 번째 인자는 Manifest provier의 authorites와 일치해야 함

                    Uri providerURI = FileProvider.getUriForFile(this, this.getPackageName(), photoFile);
                    imageUri = providerURI;

                    // 인텐트에 전달할 때는 FileProvier의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        } else {
            Toast.makeText(this, "저장공간이 접근 불가능한 기기입니다", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void doTakeAlbumAction(){
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "gyeom");

        if (!storageDir.exists()) {
            android.util.Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }
}