package black.kr.hs.mirim.sosimtapaxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class scriptPic1 extends AppCompatActivity implements View.OnClickListener{
//수정
    int scriptList[] = {R.string.script1_1,R.string.script1_2, R.string.script1_3, R.string.script1_4, R.string.script1_5, R.string.script1_6};
    int listCount = 0;
    TextView tvScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_pic1);

        ImageButton next = (ImageButton)findViewById(R.id.next1);
        ImageButton prev = (ImageButton)findViewById(R.id.prev1);

        tvScript = (TextView)findViewById(R.id.textViewScript);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.next1 && listCount < scriptList.length - 1) {
            listCount++;
        } else if (id == R.id.prev1 && listCount > 0) {
            listCount--;
        }

        tvScript.setText(scriptList[listCount]);
    }
}
