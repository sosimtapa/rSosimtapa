package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Created by 정효은 on 2019-04-06.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText id;
    private EditText pw;
    boolean secondLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //회원가입으로 이동하는 버튼
        TextView signupButton = (TextView) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                LoginActivity.this.startActivity(signupIntent);
            }
        });

        Button first_btn = (Button) findViewById(R.id.btn_login);

        first_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }

        });

    }

    private void loginCheck() {

        secondLogin = false;
        id = findViewById(R.id.idText);
        pw = findViewById(R.id.passwordText);

        FirebaseFirestore userDB = FirebaseFirestore.getInstance();
        final FirebaseFirestore fb = FirebaseFirestore.getInstance();

        userDB.collection("signUp")
                .whereEqualTo("userID", id.getText().toString()).whereEqualTo("userPW", pw.getText().toString())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //테스트화면에서 출력하도록 바꾸기
                        Toast.makeText(LoginActivity.this, "로그인 성공" + document.getString("userID") + "님 환영합니다.", Toast.LENGTH_SHORT).show();

                        fb.collection("personalInfo")
                                .whereEqualTo("userID", document.getString("userID"))
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                                                intent.putExtra("userID",id.getText().toString());
                                                startActivity(intent);
                                            }
                                            secondLogin=true;
                                        } else {
                                            Log.w("else문","들으옴");
                                        }
                                    }
                                });
                        if(secondLogin==false){         //소심도 테스트 결과가 db에 없을 경우
                            Intent intent = new Intent(getApplicationContext(), testStart.class);
                            intent.putExtra("userID",id.getText().toString());
                            startActivity(intent);
                        }
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패" + task.getException(), Toast.LENGTH_SHORT).show(); // 왜 안뜨는 지 모름
                }
            }

        });


    }
}
