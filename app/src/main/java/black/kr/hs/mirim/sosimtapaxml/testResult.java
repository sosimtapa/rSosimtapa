package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class testResult extends AppCompatActivity {

    String TGrade = "";
    private String signUpID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        Intent intent = getIntent();
        signUpID = intent.getStringExtra("signUpID");

        TextView textView = (TextView) findViewById(R.id.testCount);
        Button b = (Button)findViewById(R.id.btn_testFinish) ;

        Intent testHap = getIntent();
        final int count = testHap.getIntExtra("hap",0);

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
                intent.putExtra("signUpID", signUpID);
                startActivity(intent);
                return true;
            }
        });

    }
}
