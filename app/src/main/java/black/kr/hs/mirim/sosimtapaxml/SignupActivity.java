package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 정효은 on 2019-04-06.
 */

public class SignupActivity extends AppCompatActivity {

   // private ArrayAdapter adapter;
    private Spinner spinner;
    private FirebaseFirestore userDB;

    private EditText id;
    private EditText pw;
    private EditText email;
    private RadioGroup genderRG;
    private RadioButton genderRB;
    private String hobby;
    private String signUpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        userDB = FirebaseFirestore.getInstance();

        id = findViewById(R.id.idText);
        pw = findViewById(R.id.passwordText);
        email = findViewById(R.id.emailText);

        genderRG = findViewById(R.id.genderGroup);
        spinner = (Spinner) findViewById(R.id.hobbySpinner);

        hobby = spinner.getSelectedItem().toString();

        Button signUp = (Button) findViewById(R.id.signupButton);

        signUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpId = userDB.collection("signUp").document().getId();
                genderRB = (RadioButton)findViewById(genderRG.getCheckedRadioButtonId());

                Map<String,Object> post = new HashMap<>();

                post.put("signUpID", signUpId);
                post.put("userID",id.getText().toString());
                post.put("userPW",pw.getText().toString());
                post.put("userEmail",email.getText().toString());
                post.put("userGender", genderRB.getText().toString());
                post.put("userHobby",hobby);

                userDB.collection("signUp").document(signUpId)
                        .set(post)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignupActivity.this,"회원가입 성공!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        });

    }
}
