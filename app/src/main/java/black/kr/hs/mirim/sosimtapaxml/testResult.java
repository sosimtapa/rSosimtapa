package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class testResult extends AppCompatActivity {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        TextView textView = (TextView) findViewById(R.id.testCount);
        Button b = (Button)findViewById(R.id.btn_testFinish) ;

        final CheckBox cb1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.checkBox4);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.checkBox8);

        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() == true) count +=1;
                if(cb2.isChecked() == true) count +=1;
                if(cb3.isChecked() == true) count +=1;
                if(cb4.isChecked() == true) count +=1;
                if(cb5.isChecked() == true) count +=1;
                if(cb6.isChecked()== true) count +=1;
                if(cb7.isChecked()==true) count +=1;
                if(cb8.isChecked()==true) count +=1;
            }
        });

        textView.setText(count);

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
