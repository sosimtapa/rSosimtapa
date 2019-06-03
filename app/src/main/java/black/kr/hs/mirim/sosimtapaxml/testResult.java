package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

import org.w3c.dom.Text;

import java.util.Map;

public class testResult extends AppCompatActivity {

    String TGrade = "";
    String NID;

    FirebaseFirestore gradeDb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        TextView textView = (TextView) findViewById(R.id.testCount);
        Button b = (Button)findViewById(R.id.btn_testFinish) ;

        Intent intent = getIntent();
        final int count = intent.getIntExtra("hap",0);

        if(count<3){
            TGrade = "고양이";
        }else if(count < 5){
            TGrade = "사슴";
        }else if(count <7){
            TGrade = "토끼";
        }else if (count < 9){
            TGrade = "다람쥐";
        }

        textView.setText(TGrade + "입니다");

        LinearLayout first = (LinearLayout)findViewById(R.id.testResult);
        first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                startActivity(intent);
                return true;
            }
        });



    }
}
