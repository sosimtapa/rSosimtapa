package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class testResult extends AppCompatActivity {

    String TGrade = "";
    String userID="";
    String signUpID="";

    int count=0;
    FirebaseFirestore fb;

    //추가할 것 ; 소심도 테스트 갱신 가능하게 기능으로 따로 빼기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        TextView tv_name = findViewById(R.id.test_userId);
        TextView textView = (TextView) findViewById(R.id.testCount);
        Button b = (Button)findViewById(R.id.btn_testFinish) ;
        Button matchF = findViewById(R.id.btn_matchFuc);


        Intent gi = getIntent();
        count = gi.getIntExtra("hap",0);
        userID = gi.getStringExtra("userID");
        signUpID = gi.getStringExtra("signUpID");

        SpannableString content = new SpannableString(userID+"님");
        content.setSpan(new UnderlineSpan(), 0, content.length(),0);
        tv_name.setText(content);

        //많이 체크하라 수록 더 소심한 사람
        if(count<3){
            TGrade = "다람쥐";
        }else if(count < 5){
            TGrade = "사슴";
        }else if(count <7){
            TGrade = "토끼";
        }else if (count < 9){
            TGrade = "고양이";
        }
        textView.setText(TGrade + "입니다");

        matchF.setOnClickListener(new Button.OnClickListener() {        //사용자 맞춤 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                GradeDB();
                //등급에따라 다른 기능 번튼 보여주기
                if(TGrade.equals("다람쥐") || TGrade.equals("사슴")){
                    Log.d("tGrade1",TGrade);
                    Intent intent = new Intent(getApplicationContext(), scriptFlipImage.class);
                    startActivity(intent);

                }else{
                    Log.d("tGrade2",TGrade);
                    Intent intent = new Intent(getApplicationContext(), Main3Audio.class);
                    startActivity(intent);
                }
            }
        });

        //버튼안누르고 화면을 터치했을 경우
        LinearLayout first = (LinearLayout)findViewById(R.id.testResult);
        first.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            GradeDB();
            Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
            intent.putExtra("userID",userID);
            startActivity(intent);
            return true;
        }
    });
}
    public void GradeDB(){
        final Map<String, Object> grade = new HashMap<>();
        grade.put("userID", userID);
        grade.put("TGrade", TGrade);
        grade.put("checkSu",count);

        fb = FirebaseFirestore.getInstance();

        if(signUpID == null) {
            fb.collection("signUp")
                    .whereEqualTo("userID", userID)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //테스트화면에서 출력하도록 바꾸기
                            String signUpID2 = document.getString("signUpID");

                            fb.collection("personalInfo").document(signUpID2)
                                    .set(grade)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("데이터넣음", "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("데이터실패", "Error writing document", e);
                                        }
                                    });


                        }
                    } else {
                        Toast.makeText(testResult.this, "로그인 실패" + task.getException(), Toast.LENGTH_SHORT).show(); // 왜 안뜨는 지 모름
                    }
                }

            });
        }
        else if(signUpID == null && userID == null) {
            Toast.makeText(testResult.this, "테스트를 한 지 얼마 지나지 않았어요.. 다음에 또 해주세요!" , Toast.LENGTH_SHORT).show();
        }
        else {
            fb.collection("personalInfo").document(signUpID)
                    .set(grade)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("데이터넣음", "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("데이터실패", "Error writing document", e);
                        }
                    });
        }


    }

}
