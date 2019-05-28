package black.kr.hs.mirim.sosimtapaxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class testResult extends AppCompatActivity {

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.checkBox4);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.checkBox8);

        final TextView tv = (TextView)findViewById(R.id.textView2);

        String result = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
        if(cb1.isChecked() == true) count += 1;
        if(cb2.isChecked() == true) count += 1;
        if(cb3.isChecked() == true) count += 1;
        if(cb4.isChecked() == true) count += 1;
        if(cb5.isChecked() == true) count += 1;
        if(cb6.isChecked() == true) count += 1;
        if(cb7.isChecked() == true) count += 1;
        if(cb8.isChecked() == true) count += 1;

        tv.setText("선택결과 : " + count + "개");

    }
}
