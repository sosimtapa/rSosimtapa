package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 정효은 on 2019-04-08.
 */

public class TestActivity extends AppCompatActivity {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Button b = (Button)findViewById(R.id.btn_testFinish);

        Intent getSumIntent = getIntent();
        count = getSumIntent.getIntExtra("hap",0);

        final CheckBox cb5 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.checkBox8);

        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb5.isChecked()){
                    count+=1;
                }
                if(cb6.isChecked()){
                    count+=1;
                }
                if(cb7.isChecked()){
                    count+=1;
                }
                if(cb8.isChecked()){
                    count+=1;
                }
                Intent intent = new Intent(getApplicationContext(), testResult.class);
                intent.putExtra("hap",count);
                startActivity(intent);
            }
        });

    }
}
