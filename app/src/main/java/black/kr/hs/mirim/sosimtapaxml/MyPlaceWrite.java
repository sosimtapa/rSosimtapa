package black.kr.hs.mirim.sosimtapaxml;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPlaceWrite extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private Button button;
    private Button writeAll;
    private EditText editText;
    FirebaseFirestore ff;

    //이미지 띄우기
    ImageView imageView;
    Button img_button;
    FirebaseStorage storageRef;
    StorageReference userPlaceImage;

    //위치
    String address;

    public static MyPlaceWrite newInstance() {
        MyPlaceWrite fragment = new MyPlaceWrite();
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_place_write);

        editText = (EditText) findViewById(R.id.placewrite_editText);
        button=(Button)findViewById(R.id.placewrite_button);
        writeAll = findViewById(R.id.placeWriteAll);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);         // 키보드 생성시 UI도 같이 올라가게
        Intent it = getIntent();
        final String userID = it.getStringExtra("userID");

        storageRef = FirebaseStorage.getInstance("gs://sosimtapaxml.appspot.com");
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        userPlaceImage = mStorageRef.child(userID+"/myPlaceImg.jpg");

        ff = FirebaseFirestore.getInstance();

        /*권한*/
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.write_map);
        mapFragment.getMapAsync(this);

        //이미지
        imageView = (ImageView)findViewById(R.id.image);

        img_button = (Button)findViewById(R.id.button);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        final EditText content = findViewById(R.id.write_contents_text);                //내용

        writeAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Map<String, Object> grade = new HashMap<>();
                grade.put("userID", userID);
                grade.put("userEditAddress",editText.getText().toString());
                grade.put("address", address);        // 장소
                grade.put("content",content.getText().toString());         // 내용

                ff.collection("myplace").document()
                        .set(grade)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("장소저장", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("장소저장실패", "Error writing document", e);
                            }
                        });

                Toast.makeText(MyPlaceWrite.this,"등록완료",Toast.LENGTH_SHORT);
                finish();
            }
        });

    }

    @Override       //이미지
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    imageView.setImageBitmap(img);                  //여기까지 선택한 이미지 화면에 띄우는 소스

                   // System.out.print(data.getData());
                   // System.out.println(getPath(data.getData()));
                   //Log.d("제발",getPath(data.getData()));

                    /*Uri file = Uri.fromFile(new File(getPath(data.getData())));
                    StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
                    UploadTask uploadTask = riversRef.putFile(file);

                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                        }
                    });*/


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

 /*   public String getPath(Uri uri){
       String [] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }*/

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);

        // 맵 터치 이벤트 구현 - 터치시 마커가 추가됨
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: 위도 경도 쌍을 나타냄
                mOptions.position(new LatLng(latitude, longitude));
                // 마커(핀) 추가
                googleMap.addMarker(mOptions);
            }
        });

        // 버튼 이벤트
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String str=editText.getText().toString();
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소!!!!
                            100); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title(editText+"의 검색 결과");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng mirim = new LatLng(37.466614,126.9310583);
        mMap.addMarker(new MarkerOptions().position(mirim).title("미림 IT쇼에 오신것을 환영합니다"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mirim));
        mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(mirim,15)));
    }
}
