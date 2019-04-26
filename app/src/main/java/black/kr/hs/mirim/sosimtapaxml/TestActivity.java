package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by 정효은 on 2019-04-08.
 */

public class TestActivity extends AppCompatActivity {

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.checkBox4);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.checkBox8);

        Button b = (Button)findViewById(R.id.btn_testFinish);
/*
        final TextView tv = (TextView)findViewById(R.id.textView2);
*/

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String result = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
                if(cb1.isChecked() == true) count += 1;
                if(cb2.isChecked() == true) count += 1;
                if(cb3.isChecked() == true) count += 1;
                if(cb4.isChecked() == true) count += 1;
                if(cb5.isChecked() == true) count += 1;
                if(cb6.isChecked() == true) count += 1;
                if(cb7.isChecked() == true) count += 1;
                if(cb8.isChecked() == true) count += 1;

               /* tv.setText("선택결과 : " + count + "개");*/

            } // end onClick
        }); // end setOnClickListener

        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                startActivity(intent);
            }
        });

    }
}
