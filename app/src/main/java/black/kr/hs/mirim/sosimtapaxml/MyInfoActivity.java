package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MyInfoActivity extends AppCompatActivity {

    private TextView idTxt;
    private TextView emailTxt;
    private TextView gradeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_information);

        Intent it = getIntent();
        String userID = it.getStringExtra("usrID");
        idTxt = (TextView) findViewById(R.id.user_name);
        emailTxt = (TextView) findViewById(R.id.user_email);
        gradeTxt = (TextView) findViewById(R.id.user_grade);

        idTxt.setText(userID);

        FirebaseFirestore userDB = FirebaseFirestore.getInstance();

        userDB.collection("personalInfo")
                .whereEqualTo("userID", userID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        gradeTxt.setText("등급 : " + document.getString("TGrade"));
                    }
                }
            }
        });

        userDB.collection("signUp")
                .whereEqualTo("userID", userID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        emailTxt.setText("이메일 : " + document.getString("userEmail"));
                    }
                }
            }
        });
    }
}
