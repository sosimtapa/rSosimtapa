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

    String userID = "";
    String TGrade = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        TextView textView = (TextView) findViewById(R.id.testCount);
        Button b = (Button)findViewById(R.id.btn_testFinish) ;

        Intent testHap = getIntent();
        final int count = testHap.getIntExtra("hap",0);

    /*  Intent intent = getIntent();
      userID = intent.getStringExtra("userID");
      Log.d("userIDValue",userID);*/

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

        //등급에따라 다른 기능 번튼 보여주기
        if(TGrade.equals("고양이") || TGrade.equals("사슴")){
            Log.d("trade1",TGrade);
            LinearLayout ll = new LinearLayout(this);
            Button tv = new Button(this);
            tv.setText("Hello World");
            ll.addView(tv);
            setContentView(ll);

        }else{
            Log.d("trade2",TGrade);

            LinearLayout ll = new LinearLayout(this);
           Button tv = new Button(this);
            tv.setText("Hello World");
            ll.addView(tv);
            setContentView(ll);

        }

    /*    LinearLayout first = (LinearLayout)findViewById(R.id.testResult);
        first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                startActivity(intent);
                return true;
            }
        });
*/
    }

    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Main3Audio.class);
            startActivity(intent);
        }
    }

    class BtnOnClickListener2 implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), scriptFlipImage.class);
            startActivity(intent);
        }
    }
}
