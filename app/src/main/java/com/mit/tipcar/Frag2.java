package com.mit.tipcar;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Frag2 extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener {

    private static final String CLOUD_VISION_API_KEY = "AIzaSyANDHOrdGgOGe5xLsE6saHMmkqfgLkXV9Q";
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_PHOTO = 2222;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUEST_IMAGE_CROP = 4444;
    private AppCompatDialog progressDialog;


    String mCurrentPhotoPath;
    Uri imageUri;
    Uri photoURI, albumURI;

    private File file;
    private android.view.View view;
    private ProgressBar progressbar;
    private android.view.animation.Animation anim;
    private RelativeLayout loading;
    private ImageView car_number_photo,iv_more;
    private Button call_yes, call_no;
    private LinearLayout Ln_car_number;
    private android.widget.TextView tx_sure_call;
    private android.widget.TextView tx_call_info;
    private LinearLayout Ln_call_bt;
    private android.view.View divider2;
    private ViewPager pager;
    private android.widget.TextView imageDetail;

    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment2, container, false);

        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard,"capture.pjg");

        Ln_call_bt = view.findViewById(R.id.Ln_call_bt);
        divider2 = view.findViewById(R.id.more_divider2);
        Ln_car_number = view.findViewById(R.id.Ln_car_number);
        tx_call_info = view.findViewById(R.id.tx_call_info);
        tx_sure_call = view.findViewById(R.id.tx_sure_call);
        imageDetail = view.findViewById(R.id.image_details);
        iv_more = view.findViewById(R.id.iv_more);

        Ln_car_number.setVisibility(android.view.View.GONE);
        Ln_call_bt.setVisibility(android.view.View.GONE);
        tx_call_info.setVisibility(android.view.View.GONE);
        tx_sure_call.setVisibility(android.view.View.GONE);
        divider2.setVisibility(android.view.View.GONE);

        progressbar = view.findViewById(R.id.pbcamera);
        loading = view.findViewById(R.id.relativePbCircle);
        car_number_photo = view.findViewById(R.id.car_number_photo);
        call_no = view.findViewById(R.id.call_no);
        call_yes = view.findViewById(R.id.call_yes);

        //loading.setVisibility(view.VISIBLE);
        anim = AnimationUtils.loadAnimation(getActivity().getBaseContext().getApplicationContext(), R.anim.loading);
        call_no = view.findViewById(R.id.call_no);
        call_yes = view.findViewById(R.id.call_yes);
        progressbar.setOnClickListener((android.view.View.OnClickListener) this);
        car_number_photo.setOnClickListener((android.view.View.OnClickListener) this);
        call_yes.setOnClickListener((android.view.View.OnClickListener) this);
        call_no.setOnClickListener((android.view.View.OnClickListener) this);
        iv_more.setOnClickListener((android.view.View.OnClickListener) this);


        //ViewPager를 이용하여 사용자들에게 안전번호를 이용한 전화하는 방법을 설명해주는 다이얼로그 생성
        pager = view.findViewById(R.id.call_info_dialog);
        pager.setVisibility(android.view.View.VISIBLE);
        pager.setOffscreenPageLimit(2); //3개까지 caching
        pager.setCurrentItem(2,true);
        MainPagerAdapter adapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(), 1);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Frag_Call_Info1 fragment1 = new Frag_Call_Info1();
        Frag_Call_Info2 fragment2 = new Frag_Call_Info2();

        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        checkPermission();
        return view;
    }

        //비전 API 메소드

    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(getActivity(), GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    public void startCamera() {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    public File getCameraFile() {
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
            pager.setVisibility(android.view.View.GONE);
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
            pager.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);
                car_number_photo.setScaleType(ImageView.ScaleType.FIT_XY);
             //   Glide.with(getActivity()).load(uri).into(car_number_photo);
                car_number_photo.setImageBitmap(bitmap);

            } catch (IOException e) {
                android.util.Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(getActivity(), R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            android.util.Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(getActivity(), R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getActivity().getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(getActivity().getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature textDetection = new Feature();
                textDetection.setType("TEXT_DETECTION");
                textDetection.setMaxResults(10);
                add(textDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        android.util.Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    private class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<Frag2> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(Frag2 frag2, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(frag2);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                android.util.Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();
                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                android.util.Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                android.util.Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
            Frag2 frag2 = mActivityWeakReference.get();
            if (frag2 != null && !getActivity().isFinishing()) {
                imageDetail.setText(result);
                    progressOFF();
                Ln_car_number.setVisibility(android.view.View.VISIBLE);
                Ln_call_bt.setVisibility(android.view.View.VISIBLE);
                tx_call_info.setVisibility(android.view.View.VISIBLE);
                tx_sure_call.setVisibility(android.view.View.VISIBLE);
                divider2.setVisibility(android.view.View.VISIBLE);
            }
        }
    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
 //       imageDetail.setText(R.string.loading_message);
        progressON(getActivity(),"Loading...");

  //      Frag1 frag1 = new Frag1();
        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask(Frag2.this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            android.util.Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private static String convertResponseToString(BatchAnnotateImagesResponse response) {

        String message = "I found these things:\n\n";
        java.util.List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            message  = labels.get(0).getDescription();
        } else {
            message  = "nothing";
        }
        return message;
    }




    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }



    //MARK 프로그레르 로딩바 On/Off/Set
    public void progressON(Activity activity, String message) {
        if (activity == null || activity.isFinishing()) {
            return;
        } if (progressDialog != null && progressDialog.isShowing()) {
            progressSET(message);
        } else {
            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_layout);
            progressDialog.show();
        } final ImageView img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });
        android.widget.TextView tv_progress_message = (android.widget.TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }
    public void progressSET(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        android.widget.TextView tv_progress_message = (android.widget.TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }
    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    //MARK클릭 메소드
    public void onClick(android.view.View v){
        switch (v.getId()){
            case R.id.call_yes:
                //유저에게 전화걸기코드
                Intent callhim = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "01038547605"));
                startActivity(callhim);
                break;

            case R.id.call_no:
                // 유저에게 전화를 안걸겠다는 코드
                pager.setVisibility(android.view.View.VISIBLE);
                Ln_car_number.setVisibility(android.view.View.GONE);
                Ln_call_bt.setVisibility(android.view.View.GONE);
                tx_call_info.setVisibility(android.view.View.GONE);
                tx_sure_call.setVisibility(android.view.View.GONE);
                divider2.setVisibility(android.view.View.GONE);
                car_number_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
                car_number_photo.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
                car_number_photo.setBackgroundResource(R.drawable.photo_edge);
                Toast.makeText(getActivity(), "전화걸기를 취소하셨습니다.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.car_number_photo: {
                //구글 비전 사진 탐색기 테스트 인텐트
//                Intent intent = new Intent(getActivity(),GoogleVision.class);
//                startActivity(intent);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder
                        .setMessage(R.string.dialog_select_prompt)
                        .setPositiveButton(R.string.dialog_select_gallery, (dialog, which) -> startGalleryChooser())
                        .setNegativeButton(R.string.dialog_select_camera, (dialog, which) -> startCamera());
                builder.create().show();
            }

//                progressbar.setAnimation(anim);
// 사진촬영 액션 들어가면됨
//                captureCamera();
//                new Handler().postDelayed(() -> {
//                    progressbar.setVisibility(View.GONE);
//                    pager.setVisibility(View.GONE);
//                    Ln_car_number.setVisibility(View.VISIBLE);
//                    Ln_call_bt.setVisibility(View.VISIBLE);
//                    tx_call_info.setVisibility(View.VISIBLE);
//                    tx_sure_call.setVisibility(View.VISIBLE);
//                    divider2.setVisibility(View.VISIBLE);}, 1000);
            break;
            case R.id.iv_more:
                Intent moreintent = new Intent(getActivity(),moreActivity.class);
                startActivity(moreintent);
        }
    }
}
